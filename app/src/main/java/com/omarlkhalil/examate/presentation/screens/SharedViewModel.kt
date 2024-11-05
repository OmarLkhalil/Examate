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

    private val _isTutorialActive = MutableStateFlow(false)
    val isTutorialActive = _isTutorialActive.asStateFlow()

    init {
        viewModelScope.launch {
            getFirstTimeLaunchUseCase().collectLatest { isFirstTime ->
                if (isFirstTime) {
                    _isTutorialActive.emit(true)
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

    private val _isHomeHintVisible = MutableStateFlow(false)
    val isHomeHintVisible = _isHomeHintVisible.asStateFlow()

    private val _isConnectHintVisible = MutableStateFlow(false)
    val isConnectHintVisible = _isConnectHintVisible.asStateFlow()


    private val _isQuestionHintVisible = MutableStateFlow(false)
    val isQuestionHintVisible = _isQuestionHintVisible.asStateFlow()

    private val _isToolsHintVisible = MutableStateFlow(false)
    val isToolsHintVisible = _isToolsHintVisible.asStateFlow()


    private val _isProfileHintVisible = MutableStateFlow(false)
    val isProfileHintVisible = _isProfileHintVisible.asStateFlow()

    private val _isFiltersHintVisible = MutableStateFlow(false)
    val isFiltersHintVisible = _isFiltersHintVisible.asStateFlow()


    private val _isFirstItemHintVisible = MutableStateFlow(false)
    val isFirstItemHintVisible = _isFirstItemHintVisible.asStateFlow()


    fun setHomeHintVisibility(isVisible: Boolean){
        resetHints()
        updateSelectedIndex(0)
        _isHomeHintVisible.value = isVisible
    }

    fun setConnectHintVisibility(isVisible: Boolean){
        resetHints()
        updateSelectedIndex(1)
        _isConnectHintVisible.value = isVisible
    }

    fun setQuestionHintVisibility(isVisible: Boolean){
        resetHints()
        updateSelectedIndex(2)
        _isQuestionHintVisible.value = isVisible
    }

    fun setToolsHintVisibility(isVisible: Boolean){
        resetHints()
        updateSelectedIndex(3)
        _isToolsHintVisible.value = isVisible
    }

    fun setProfileHintVisibility(isVisible: Boolean){
        resetHints()
        updateSelectedIndex(4)
        _isProfileHintVisible.value = isVisible
    }

    fun setFiltersHintVisibility(isVisible: Boolean){
        resetHints()
        updateSelectedIndex(2)
        _isFiltersHintVisible.value = isVisible
    }

    fun setFirstItemHintVisibility(isVisible: Boolean){
        resetHints()
        updateSelectedIndex(2)
        _isFirstItemHintVisible.value = isVisible
    }


    fun resetHints(){
        _isHomeHintVisible.value = false
        _isConnectHintVisible.value = false
        _isQuestionHintVisible.value = false
        _isToolsHintVisible.value = false
        _isProfileHintVisible.value = false
        _isFiltersHintVisible.value = false
        _isFirstItemHintVisible.value = false
    }

    companion object{
        const val ORAL_TAB_INDEX = 1
        const val WRITING_TAB_INDEX = 0
    }
}
