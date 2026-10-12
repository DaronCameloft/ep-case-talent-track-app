// File: features/employees/application/SaveEmployeeUseCase.kt
package pe.edu.upc.talenttrack.features.employees.application

import pe.edu.upc.talenttrack.features.employees.domain.Employee
import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import javax.inject.Inject

/** Saves an employee in the local database. */
class SaveEmployeeUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    suspend operator fun invoke(employee: Employee) = repository.saveEmployee(employee)
}