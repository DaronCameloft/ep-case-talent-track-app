// File: features/employees/application/GetTotalSalaryUseCase.kt
package pe.edu.upc.talenttrack.features.employees.application

import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import javax.inject.Inject

/** Reads the total salary of the saved employees from SharedPreferences. */
class GetTotalSalaryUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    operator fun invoke(): Double = repository.getTotalSalary()
}