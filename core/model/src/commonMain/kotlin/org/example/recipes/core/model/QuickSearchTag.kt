package org.example.recipes.core.model

import kotlinx.serialization.Serializable

@Serializable
data class QuickSearchTag(val title: String,
                          val image: String)
