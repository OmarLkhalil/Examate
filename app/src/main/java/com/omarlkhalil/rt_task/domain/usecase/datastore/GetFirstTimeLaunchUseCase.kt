package com.omarlkhalil.rt_task.domain.usecase.datastore

import com.omarlkhalil.rt_task.domain.repo.PreferenceRepository
import javax.inject.Inject

class GetFirstTimeLaunchUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    suspend operator fun invoke() = preferenceRepository.getIsFirstTime()
}