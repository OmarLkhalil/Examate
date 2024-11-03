package com.omarlkhalil.rt_task.di

import android.content.Context
import com.omarlkhalil.rt_task.data.datasource.PreferenceDataSourceImpl
import com.omarlkhalil.rt_task.domain.repo.PreferenceDataSource
import com.omarlkhalil.rt_task.data.repo.PreferenceRepositoryImpl
import com.omarlkhalil.rt_task.domain.repo.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context


    @Provides
    @Singleton
    fun providePreferencesDataSource(@ApplicationContext context: Context): PreferenceDataSource =
        PreferenceDataSourceImpl(context)

    @Provides
    @Singleton
    fun providePreferencesRepository(preferencesDataSource: PreferenceDataSource): PreferenceRepository =
        PreferenceRepositoryImpl(preferencesDataSource)



}