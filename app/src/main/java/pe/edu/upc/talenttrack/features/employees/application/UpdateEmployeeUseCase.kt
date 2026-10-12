// File: features/employees/application/UpdateEmployeeUseCase.kt
package pe.edu.upc.talenttrack.features.employees.application

import pe.edu.upc.talenttrack.features.employees.domain.Employee
import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import javax.inject.Inject

/** Updates a saved employee; the repository refreshes the total salary afterwards. */
class UpdateEmployeeUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    suspend operator fun invoke(employee: Employee) = repository.updateEmployee(employee)
}