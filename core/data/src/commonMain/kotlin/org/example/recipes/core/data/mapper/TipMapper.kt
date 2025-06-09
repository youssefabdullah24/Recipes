package org.example.recipes.core.data.mapper

import org.example.recipes.core.model.Tip
import org.example.recipes.core.network.model.RecipeTipDto

fun RecipeTipDto.toDomain() =
    Tip(
        //TODO: move url
        authorAvatarUrl = this.authorAvatarUrl ?: "https://lh3.googleusercontent.com/a-/AOh14GjUaTfAes1sSL53qdgOX_Pt7y46hFP7lWmYBwMq6w=s96-c?downsize=45:*&output-format=auto&output-quality=auto",
        authorName = this.authorName ?: "Anon",
        authorUsername = this.authorUsername ?: "anon",
        tipBody = this.tipBody,
        tipPhotoUrl = this.tipPhoto?.url,
        timestamp = this.createdAt ?: this.updatedAt ?: -1,
        upvotes = this.upvotesTotal ?: 0
    )