package com.omarlkhalil.examate.presentation.screens

import androidx.compose.ui.layout.LayoutCoordinates
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarlkhalil.examate.domain.usecase.datastore.GetFirstTimeLaunchUseCase
import com.omarlkhalil.examate.domain.usecase.datastore.SetFirstTimeLaunchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getFirstTimeLaunchUseCase: GetFirstTimeLaunchUseCase,
    private val setFirstTimeLaunchUseCase: SetFirstTimeLaunchUseCase
) : ViewModel() {

    private val _currentTutorialStep = MutableStateFlow(0)
    val currentTutorialStep = _currentTutorialStep.asStateFlow()

    private val _isTutorialActive = MutableStateFlow(false)
    val isTutorialActive = _isTutorialActive.asStateFlow()

    init {
        viewModelScope.launch {
            getFirstTimeLaunchUseCase().collectLatest { isFirstTime ->
                if (isFirstTime) {
                    _isTutorialActive.emit(true)
                    _currentTutorialStep.emit(1)
                }
            }
        }
    }

    fun endTutorial() {
        viewModelScope.launch {
            _isTutorialActive.emit(false)
            setFirstTimeLaunchUseCase(false)
        }
    }

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex: StateFlow<Int> = _selectedIndex

    private val _questionsTabsIndex = MutableStateFlow(WRITING_TAB_INDEX)
    val questionsTabsIndex: StateFlow<Int> = _questionsTabsIndex

    private val _visibleHintCoordinates = MutableStateFlow<LayoutCoordinates?>(null)
    val visibleHintCoordinates: StateFlow<LayoutCoordinates?> = _visibleHintCoordinates

    fun setTabsIndex(index: Int){
        _questionsTabsIndex.value = index
    }

    fun updateSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    companion object{
        const val ORAL_TAB_INDEX = 1
        const val WRITING_TAB_INDEX = 0
    }
}
