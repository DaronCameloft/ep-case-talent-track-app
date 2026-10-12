package pe.edu.upc.talenttrack.core.data.local

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SalaryPreferences @Inject constructor(
    @ApplicationContext context: Context
){
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveTotalSalary(total: Double) {
        preferences.edit { putString(KEY_TOTAL_SALARY, total.toString()) }
    }
    fun getTotalSalary(): Double =
        preferences.getString(KEY_TOTAL_SALARY, null)?.toDoubleOrNull() ?: 0.0

    companion object {
        private const val PREFERENCES_NAME = "talent_track_preferences"
        private const val KEY_TOTAL_SALARY = "total_salary"
    }
}