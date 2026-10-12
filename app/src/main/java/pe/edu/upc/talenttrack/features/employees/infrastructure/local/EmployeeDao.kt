package pe.edu.upc.talenttrack.features.employees.infrastructure.local

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees ORDER BY last_name")
    suspend fun fetchAll(): List<EmployeeEntity>

    @Query("SELECT id FROM employees")
    suspend fun fetchAllIds(): List<String>

    @Query("SELECT COALESCE(SUM(salary), 0) FROM employees")
    suspend fun sumSalaries(): Double

    /** Inserts a new row or replaces the existing one with the same id (used for save and edit). */
    @Upsert
    suspend fun upsert(entity: EmployeeEntity)

    @Query("DELETE FROM employees WHERE id = :id")
    suspend fun deleteById(id: String)
}