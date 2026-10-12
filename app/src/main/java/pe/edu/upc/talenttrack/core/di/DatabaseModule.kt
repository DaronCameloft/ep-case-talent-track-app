package pe.edu.upc.talenttrack.core.di

import android.content.Context
import androidx.room3.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pe.edu.upc.talenttrack.features.employees.infrastructure.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder<AppDatabase>(context, "talent_track_database").build()

}