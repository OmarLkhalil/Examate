package com.omarlkhalil.examate.domain.usecase.datastore

import com.omarlkhalil.examate.domain.repo.PreferenceRepository
import javax.inject.Inject

class GetFirstTimeLaunchUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    suspend operator fun invoke() = preferenceRepository.getIsFirstTime()
}