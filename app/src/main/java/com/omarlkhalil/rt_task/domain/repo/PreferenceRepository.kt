package com.omarlkhalil.rt_task.domain.repo

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    suspend fun getIsFirstTime(): Flow<Boolean>
    suspend fun setIsFirstTime(firstTime: Boolean)

}