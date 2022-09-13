package com.nasa.gallery.mobile.presentation.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.domain.explore.GetSpaceImagesUseCase
import com.nasa.gallery.mobile.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getSpaceImagesUseCase: GetSpaceImagesUseCase
): ViewModel() {

    private val _state : MutableStateFlow<Result<List<SpaceImage>>> = MutableStateFlow(Result.Loading)
    val state : StateFlow<Result<List<SpaceImage>>> = _state

    init{
        viewModelScope.launch {
            fetchImages()
        }
    }

    suspend fun fetchImages(){
        getSpaceImagesUseCase().onEach {
            _state.value = it
        }
    }

}