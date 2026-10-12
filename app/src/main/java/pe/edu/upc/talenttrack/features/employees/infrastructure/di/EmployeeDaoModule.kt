// File: features/employees/infrastructure/di/EmployeeDaoModule.kt
package pe.edu.upc.talenttrack.features.employees.infrastructure.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upc.talenttrack.features.employees.infrastructure.local.AppDatabase
import pe.edu.upc.talenttrack.features.employees.infrastructure.local.EmployeeDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EmployeeDaoModule {

    @Provides
    @Singleton
    fun provideEmployeeDao(database: AppDatabase): EmployeeDao = database.employeeDao()
}