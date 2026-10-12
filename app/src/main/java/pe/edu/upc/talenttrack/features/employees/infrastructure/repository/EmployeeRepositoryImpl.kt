package pe.edu.upc.talenttrack.features.employees.infrastructure.repository

import pe.edu.upc.talenttrack.core.data.local.SalaryPreferences
import pe.edu.upc.talenttrack.features.employees.domain.Employee
import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import pe.edu.upc.talenttrack.features.employees.infrastructure.local.EmployeeDao
import pe.edu.upc.talenttrack.features.employees.infrastructure.mapper.toDomain
import pe.edu.upc.talenttrack.features.employees.infrastructure.mapper.toEntity
import pe.edu.upc.talenttrack.features.employees.infrastructure.remote.EmployeeService
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

/**
 * Single source of truth for employees: remote search (Retrofit) + local saved list (Room).
 * Every write to Room recalculates the total salary and stores it in SharedPreferences.
 */
class EmployeeRepositoryImpl @Inject constructor(
    private val service: EmployeeService,
    private val dao: EmployeeDao,
    private val salaryPreferences: SalaryPreferences
) : EmployeeRepository {

    override suspend fun searchEmployees(profession: String): Result<List<Employee>> =
        try {
            val response = service.searchEmployees(profession.trim())
            if (response.isSuccessful) {
                val employees = response.body().orEmpty()
                    .filter { !it.id.isNullOrBlank() }   // a row without id cannot be saved
                    .map { it.toDomain() }
                Result.success(employees)
            } else {
                Result.failure(Exception("Request failed with code ${response.code()}"))
            }
        } catch (exception: CancellationException) {
            throw exception                              // never swallow coroutine cancellation
        } catch (exception: Exception) {
            Result.failure(exception)                    // no internet, timeout, bad JSON...
        }

    override suspend fun getSavedEmployees(): List<Employee> =
        dao.fetchAll().map { it.toDomain() }

    override suspend fun getSavedEmployeeIds(): Set<String> =
        dao.fetchAllIds().toSet()

    override suspend fun saveEmployee(employee: Employee) {
        dao.upsert(employee.toEntity())
        refreshTotalSalary()
    }

    override suspend fun updateEmployee(employee: Employee) {
        dao.upsert(employee.toEntity())
        refreshTotalSalary()
    }

    override suspend fun deleteEmployee(id: String) {
        dao.deleteById(id)
        refreshTotalSalary()
    }

    override fun getTotalSalary(): Double = salaryPreferences.getTotalSalary()

    /** Keeps SharedPreferences in sync with the database after any change. */
    private suspend fun refreshTotalSalary() {
        salaryPreferences.saveTotalSalary(dao.sumSalaries())
    }
}