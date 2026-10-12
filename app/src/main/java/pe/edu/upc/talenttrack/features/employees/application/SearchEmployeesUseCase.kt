// File: features/employees/application/SearchEmployeesUseCase.kt
package pe.edu.upc.talenttrack.features.employees.application

import pe.edu.upc.talenttrack.features.employees.domain.Employee
import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import javax.inject.Inject

/** Searches employees by profession in the remote API. */
class SearchEmployeesUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    suspend operator fun invoke(profession: String): Result<List<Employee>> =
        repository.searchEmployees(profession)
}