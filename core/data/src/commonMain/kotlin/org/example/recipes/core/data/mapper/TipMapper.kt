package org.example.recipes.core.data.mapper

import org.example.recipes.core.data.BuildKonfig
import org.example.recipes.core.model.Tip
import org.example.recipes.core.network.model.RecipeTipDto

fun RecipeTipDto.toDomain() =
    Tip(
        authorAvatarUrl = this.authorAvatarUrl ?: BuildKonfig.avatarUrl,
        authorName = this.authorName ?: "Anon",
        authorUsername = this.authorUsername ?: "anon",
        tipBody = this.tipBody,
        tipPhotoUrl = this.tipPhoto?.url,
        timestamp = this.createdAt ?: this.updatedAt ?: -1,
        upvotes = this.upvotesTotal ?: 0
    )