package com.nasa.gallery.mobile.presentation.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasa.gallery.mobile.data.model.SpaceImage
import com.nasa.gallery.mobile.domain.explore.GetSpaceImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ExploreViewModel @Inject constructor(
    private val getSpaceImagesUseCase: GetSpaceImagesUseCase
): ViewModel() {

    private val _state : MutableStateFlow<ExploreState> = MutableStateFlow(ExploreState.Loading)
    val state : StateFlow<ExploreState> = _state

    init{
        viewModelScope.launch {
            fetchImages()
        }
    }

    suspend fun fetchImages(){
        getSpaceImagesUseCase().collect {
            when(it){
                is com.nasa.gallery.mobile.result.Result.Loading ->{
                    _state.value = ExploreState.Loading
                }
                is com.nasa.gallery.mobile.result.Result.Error ->{
                    _state.value = ExploreState.Error(it.exception.localizedMessage ?: "Unexpected error occurred")
                }
                is com.nasa.gallery.mobile.result.Result.Success ->{
                    _state.value = ExploreState.Content(it.data)
                }
            }
        }
    }
    sealed class ExploreState{
        data class Content(val data : List<SpaceImage>) : ExploreState()
        data class Error(val message : String) : ExploreState()
        object Loading : ExploreState()
    }
}

