package org.example.recipes.core.data.mapper

import org.example.recipes.core.model.Direction
import org.example.recipes.core.model.Ingredient
import org.example.recipes.core.model.Measurement
import org.example.recipes.core.model.Nutrition
import org.example.recipes.core.model.Recipe
import org.example.recipes.core.network.model.RecipeDto

fun RecipeDto.toDomain(): Recipe {
    val component = this.sections.firstOrNull{ it.position == 1 }?.components ?: emptyList()
    return Recipe(
        id = this.id,
        title = this.name,
        description = this.description ?: "",
        duration = if (this.totalTimeMinutes != 0) {
            "${this.totalTimeMinutes} mins"
        } else {
            this.totalTimeTier?.displayTier
        }
            ?: this.tags.firstOrNull { it.id == 8091744 || it.id == 64472 || it.id == 8091748 }?.displayName
            ?: "",
        image = this.thumbnailUrl,
        servings = this.yields,
        type = this.tags.firstOrNull { it.type == "meal" }?.displayName ?: "",
        nutrition = Nutrition(
            this.nutrition.calories,
            this.nutrition.carbohydrates,
            this.nutrition.fat,
            this.nutrition.fiber,
            this.nutrition.protein,
            this.nutrition.sugar
        ),
        directions = this.instructions.map {
            Direction(
                id = it.id,
                position = it.position,
                startTime = it.startTime,
                endTime = it.endTime,
                appliance = it.appliance,
                temperature = it.temperature,
                text = it.displayText
            )
        },
        ingredients = component.map {
            Ingredient(
                position = it.position,
                measurement = Measurement(
                    name = if (it.measurements.isNotEmpty()) it.measurements.firstOrNull()?.measuringUnit?.name ?:""  else "",
                    abbreviation = if (it.measurements.isNotEmpty()) it.measurements.firstOrNull()?.measuringUnit?.abbreviation ?: "" else "",
                    quantity = if (it.measurements.isNotEmpty()) it.measurements.firstOrNull()?.quantity ?: "" else ""

                ), name = it.ingredient.name
            )
        },
        videoUrl = this.originalVideoUrl ?: this.videoUrl,
        tags = this.tags.map { it.toDomain() },
        updatedAt = this.updatedAt,
        createdAt = this.createdAt,
        ratings = Triple(
            this.userRatings.countPositive,
            this.userRatings.countNegative,
            this.userRatings.score
        )

    )
} // TODO FIX :  Collection contains no element matching the predicate
// 2024-09-11 22:39:52.126  7082-7209  System.out              org.example.recipes                  I  FROM: https://tasty.p.rapidapi.com/recipes/list?q=crepe&from=30&size=5
// 2024-09-05 23:59:55.668 27396-27430 System.out              org.example.recipes                  I  {"count":10063,"results":[{"approved_at":1559404327,"aspect_ratio":"16:9","beauty_url":null,"brand":null,"brand_id":null,"buzz_id":null,"canonical_id":"recipe:5296","compilations":[],"cook_time_minutes":0,"country":"US","created_at":1558802172,"credits":[{"name":"Codii Lopez","type":"internal"}],"description":"","draft_status":"published","facebook_posts":[],"id":5296,"inspired_by_url":null,"instructions":[{"appliance":"oven","display_text":"Preheat the oven to 400°F (200°C). Line a baking sheet with parchment paper.","end_time":0,"id":47750,"position":1,"start_time":0,"temperature":400},{"appliance":null,"display_text":"In a large bowl, whisk together the flour, baking powder, and salt.","end_time":0,"hacks":[{"end_index":21,"id":24,"match":"whisk","start_index":17}],"id":47751,"position":2,"start_time":0,"temperature":null},{"appliance":null,"display_text":"Using your hands or a pastry cutter, work the butter into the dry ingredients until the butter breaks down to pea-sized pieces.","end_time":0,"id":47752,"position":3,"start_time":0,"temperature":null},{"appliance":null,"display_text":"Add the milk and fold with a rubber spatula until fully incorporated and the dough comes together in a ball.","end_time":0,"id":47753,"position":4,"start_time":0,"temperature":null},{"appliance":null,"display_text":"Scoop the dough into 12 portions onto the prepared baking sheet. Brush each biscuit with honey.","end_time":0,"id":47754,"position":5,"start_time":0,"temperature":null},{"appliance":null,"display_text":"Bake for about 20 minutes, or until the tops are golden brown.","end_time":0,"id":47755,"position":6,"start_time":0,"temperature":null},{"appliance":null,"display_text":"Serve the biscuits with bacon jam and soft-boiled eggs.","end_time":0,"hacks":[{"end_index":48,"id":18,"match":"boiled","start_index":43}],"id":47756,"position":7,"start_time":0,"temperature":null},{"appliance":null,"display_text":"Enjoy!","end_time":15000,"id":47757,"position":8,"start_time":0,"temperature":null}],"is_app_only":false,"is_one_top":false,"is_shoppable":true,"is_subscriber_content":false,"keywords":"","language":"eng","name":"Biscuit: Bit O Heaven","num_servings":12,"nutrition":{"calories":342,"carbohydrates":33,"fat":18,"fiber":0,"protein":12,"sugar":14,"updated_at":"2024-05-01T08:06:07+02:00"},"nutrition_visibility":"auto","original_video_url":"https://s3.amazonaws.com/video-api-prod/assets/fa92895ca8774780a0fa8ebaeb7168ad/F5F.mp4","prep_time_minutes":0,"price":{"consumption_portion":50,"consumption_total":550,"portion":50,"total":650,"updated_at":"2024-09-05T07:17:33+02:00"},"promotion":"partial","renditions":[{"aspect":"landscape","bit_rate":586,"container":"mp4","content_type":"video/mp4","duration":15087,"file_size":1103836,"height":404,"maximum_bit_rate":null,"minimum_bit_rate":null,"name":"mp4_720x404","poster_url":"https://img.buzzfeed.com/video-transcoder-prod/output/134126/square_720/1558802113_00001.png","url":"https://vid.tasty.co/output/134126/square_720/1558802113","width":720},{"aspect":"landscape","bit_rate":303,"container":"mp4","content_type":"video/mp4","duration":15087,"file_size":570570,"height":180,"maximum_bit_rate":null,"minimum_bit_rate":null,"name":"mp4_320x180","poster_url":"https://img.buzzfeed.com/video-transcoder-prod/output/134126/square_320/1558802113_00001.png","url":"https://vid.tasty.co/output/134126/square_320/1558802113","width":320},{"aspect":"landscape","bit_rate":1228,"container":"mp4","content_type":"video/mp4","duration":15087,"file_size":2315774,"height":720,"maximum_bit_rate":null,"minimum_bit_rate":null,"name":"mp4_1280x720","poster_url":"https://img.buzzfeed.com/video-transcoder-prod/output/134126/landscape_720/1558802113_00001.png","url":"https://vid.tasty.co/output/134126/landscape_720/1558802113","width":1280},{"aspect":"landscape","bit_rate":516,"container":"mp4","content_type":"video/mp4","duration":15087,"file_size":971493,"height":360,"maximum_bit_rate":null,"minimum_bit_rate":null,"name":"mp4_640x360","poster_url":"https://img.buzzfeed.com/video-t