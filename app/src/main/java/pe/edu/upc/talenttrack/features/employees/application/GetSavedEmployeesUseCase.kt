// File: features/employees/application/GetSavedEmployeesUseCase.kt
package pe.edu.upc.talenttrack.features.employees.application

import pe.edu.upc.talenttrack.features.employees.domain.Employee
import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import javax.inject.Inject

/** Returns the employees saved in the local database. */
class GetSavedEmployeesUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    suspend operator fun invoke(): List<Employee> = repository.getSavedEmployees()
}