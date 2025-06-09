package org.example.recipes.core.network.model

interface PagingResponse<T> {
    val count: Int?
    val results: List<T>?
}