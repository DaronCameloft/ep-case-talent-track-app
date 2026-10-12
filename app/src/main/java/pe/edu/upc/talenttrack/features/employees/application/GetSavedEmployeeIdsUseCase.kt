// File: features/employees/application/GetSavedEmployeeIdsUseCase.kt
package pe.edu.upc.talenttrack.features.employees.application

import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import javax.inject.Inject

/** Returns only the ids of the saved employees, to mark them in the search results. */
class GetSavedEmployeeIdsUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    suspend operator fun invoke(): Set<String> = repository.getSavedEmployeeIds()
}