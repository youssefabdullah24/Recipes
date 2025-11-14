package org.example.recipes.core.data.mapper

import org.example.recipes.core.model.Tag
import org.example.recipes.core.network.model.TagDto

fun TagDto.toDomain() : Tag{
    return Tag(
        displayName = this.displayName ?: "NA",
        name = this.name ?: "NA",
        rootTagName = this.rootTagType ?: "NA",
        isSelected = false,
    )
}