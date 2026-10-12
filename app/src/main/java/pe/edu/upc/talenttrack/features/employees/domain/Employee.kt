package pe.edu.upc.talenttrack.features.employees.domain

data class Employee (
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val profession: String,
    val position: String,
    val experience: Int,
    val email: String,
    val phone: String,
    val address: String,
    val district: String,
    val city: String,
    val country: String,
    val maritalStatus: String,
    val gender: String,
    val salary: Double,
    val department: String,
    val photoUrl: String
) {
    val fullName: String get() = "$firstName $lastName"
}