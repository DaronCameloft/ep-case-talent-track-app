package pe.edu.upc.talenttrack.features.employees.infrastructure.local

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "employees")
data class EmployeeEntity (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
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
    @ColumnInfo(name = "marital_status") val maritalStatus: String,
    val gender: String,
    val salary: Double,
    val department: String,
    @ColumnInfo(name = "photo_url") val photoUrl: String
    
)