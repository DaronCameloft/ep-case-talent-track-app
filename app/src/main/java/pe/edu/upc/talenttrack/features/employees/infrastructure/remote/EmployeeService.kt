package pe.edu.upc.talenttrack.features.employees.infrastructure.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/** Retrofit contract for the employees endpoint. */
interface EmployeeService {

    /** GET https://dev.codemcy.com/employees.php?profesion={profession} → JSON array. */
    @GET("employees.php")
    suspend fun searchEmployees(
        @Query("profesion") profession: String
    ): Response<List<EmployeeDto>>
}