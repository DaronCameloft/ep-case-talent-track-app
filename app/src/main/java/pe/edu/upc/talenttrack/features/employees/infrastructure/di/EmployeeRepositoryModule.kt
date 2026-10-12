// File: features/employees/infrastructure/di/EmployeeRepositoryModule.kt
package pe.edu.upc.talenttrack.features.employees.infrastructure.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upc.talenttrack.features.employees.domain.EmployeeRepository
import pe.edu.upc.talenttrack.features.employees.infrastructure.repository.EmployeeRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface EmployeeRepositoryModule {

    /** Whenever someone asks for EmployeeRepository (domain), Hilt provides EmployeeRepositoryImpl. */
    @Binds
    @Singleton
    fun bindEmployeeRepository(impl: EmployeeRepositoryImpl): EmployeeRepository
}