package com.omarlkhalil.rt_task.domain.usecase.datastore

import com.omarlkhalil.rt_task.domain.repo.PreferenceRepository
import javax.inject.Inject

class SetFirstTimeLaunchUseCase  @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    suspend operator fun invoke(isFirstTime: Boolean) = preferenceRepository.setIsFirstTime(isFirstTime)
}