package com.omarlkhalil.rt_task.data.repo

import com.omarlkhalil.rt_task.domain.repo.PreferenceDataSource
import com.omarlkhalil.rt_task.domain.repo.PreferenceRepository
import com.omarlkhalil.rt_task.domain.utils.Constants.IS_FIRST_TIME_PREFS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(private val preferenceDataSource: PreferenceDataSource) :
    PreferenceRepository {
    override suspend fun getIsFirstTime(): Flow<Boolean> {
        return flow {
            preferenceDataSource.getValue(IS_FIRST_TIME_PREFS, true).collect {
                emit(it as Boolean)
            }
        }
    }

    override suspend fun setIsFirstTime(firstTime: Boolean) {
        preferenceDataSource.setValue(IS_FIRST_TIME_PREFS, firstTime)
    }
}