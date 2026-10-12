package pe.edu.upc.talenttrack.features.employees.infrastructure.remote
import com.google.gson.annotations.SerializedName

// tal cual el api que nos den

data class EmployeeDto(
    @SerializedName("id") val id: String?,
    @SerializedName("nombre") val firstName: String?,
    @SerializedName("apellidos") val lastName: String?,
    @SerializedName("edad") val age: Int?,
    @SerializedName("profesion") val profession: String?,
    @SerializedName("cargo") val position: String?,
    @SerializedName("experiencia") val experience: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("telefono") val phone: String?,
    @SerializedName("direccion") val address: String?,
    @SerializedName("distrito") val district: String?,
    @SerializedName("ciudad") val city: String?,
    @SerializedName("pais") val country: String?,
    @SerializedName("estado_civil") val maritalStatus: String?,
    @SerializedName("genero") val gender: String?,
    @SerializedName("salario") val salary: Double?,
    @SerializedName("departamento") val department: String?,
    @SerializedName("foto") val photoUrl: String?
)
