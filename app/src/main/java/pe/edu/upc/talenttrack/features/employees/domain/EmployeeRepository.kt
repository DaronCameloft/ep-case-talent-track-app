package pe.edu.upc.talenttrack.features.employees.domain


interface EmployeeRepository {
    suspend fun searchEmployees(profession: String): Result<List<Employee>>
    suspend fun getSavedEmployees(): List<Employee>
    suspend fun getSavedEmployeeIds(): Set<String>
    suspend fun saveEmployee(employee: Employee)
    suspend fun updateEmployee(employee: Employee)
    suspend fun deleteEmployee(id: String)
    fun getTotalSalary(): Double
}