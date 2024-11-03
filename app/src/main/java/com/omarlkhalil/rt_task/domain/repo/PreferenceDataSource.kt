package com.omarlkhalil.rt_task.domain.repo

import kotlinx.coroutines.flow.Flow

interface PreferenceDataSource {
    suspend fun getValue(key: String, default: Any? = ""): Flow<Any?>
    suspend fun setValue(key: String, value: Any?)
    suspend fun removeValue()
}