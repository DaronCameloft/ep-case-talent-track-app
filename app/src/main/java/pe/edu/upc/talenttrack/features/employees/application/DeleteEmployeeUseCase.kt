// File: features/employees/application/DeleteEmployeeUseCase.kt
package pe.edu.upc.talenttrack.features.employees.application

import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import javax.inject.Inject

/** Removes a saved employee by id. */
class DeleteEmployeeUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteEmployee(id)
}