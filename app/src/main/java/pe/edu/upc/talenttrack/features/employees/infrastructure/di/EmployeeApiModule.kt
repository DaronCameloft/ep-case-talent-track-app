// File: features/employees/infrastructure/di/EmployeeApiModule.kt
package pe.edu.upc.talenttrack.features.employees.infrastructure.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upc.talenttrack.features.employees.infrastructure.remote.EmployeeService
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Tells Hilt how to build the Retrofit service used by the repository.
 */
@Module
@InstallIn(SingletonComponent::class)
object EmployeeApiModule {

    @Provides
    @Singleton
    fun provideEmployeeService(retrofit: Retrofit): EmployeeService =
        retrofit.create(EmployeeService::class.java)
}
