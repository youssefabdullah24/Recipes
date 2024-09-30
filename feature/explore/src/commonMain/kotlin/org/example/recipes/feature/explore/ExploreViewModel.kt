package org.example.recipes.feature.explore

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.recipes.core.data.IRecipesRepository
import org.example.recipes.core.model.QuickSearchTag

class ExploreViewModel(private val repository: IRecipesRepository) : ViewModel() {

    private val mutableState = MutableStateFlow(repository.getQuickSearchTags())
    val state: StateFlow<List<QuickSearchTag>> = mutableState.asStateFlow()

}

