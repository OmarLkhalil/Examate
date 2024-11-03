package com.omarlkhalil.examate.di

import android.content.Context
import com.omarlkhalil.examate.data.datasource.PreferenceDataSourceImpl
import com.omarlkhalil.examate.domain.repo.PreferenceDataSource
import com.omarlkhalil.examate.data.repo.PreferenceRepositoryImpl
import com.omarlkhalil.examate.domain.repo.PreferenceRepository
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