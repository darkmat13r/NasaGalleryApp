package com.nasa.mobile.ui.explore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nasa.gallery.mobile.data.repository.NasaSpaceImageRepository
import com.nasa.gallery.mobile.domain.explore.GetSpaceImagesUseCase
import com.nasa.gallery.mobile.presentation.ui.explore.ExploreViewModel
import com.nasa.mobile.data.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExploreViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()



    private val testDataSource = TestDataSource()

    @Test
    fun testDataIsLoaded_ObservablesUpdated() = runTest {
        val viewModel = createViewModel()
        val state = viewModel.state.first { it is ExploreViewModel.ExploreState.Content } as ExploreViewModel.ExploreState.Content
        assertThat(state.data).isNotEmpty()
    }

    fun createViewModel() =  ExploreViewModel(
        GetSpaceImagesUseCase(NasaSpaceImageRepository(testDataSource), coroutineRule.testDispatcher)
    )
}