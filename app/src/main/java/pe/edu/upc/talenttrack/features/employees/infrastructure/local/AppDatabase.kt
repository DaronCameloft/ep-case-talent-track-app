package pe.edu.upc.talenttrack.features.employees.infrastructure.local

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [EmployeeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}