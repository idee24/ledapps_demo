package com.example.ledapps_demo.ui

import android.widget.ImageView
import com.example.ledapps_demo.R

/**
 *@Created by Yerimah on 01/12/2021.
 */

const val HAIR_KEY = "hair"
var selectedHair = 0
val hairCuts = listOf(
    R.drawable.images_haircut_selection_none,
    R.drawable.images_haircut_selection_01,
    R.drawable.images_haircut_selection_02,
    R.drawable.images_haircut_selection_03,
)

const val BEARD_KEY = "beard"
var selectedBeard = 0
val beards = listOf(
    R.drawable.images_beard_selection_01,
    R.drawable.images_beard_selection_02,
    R.drawable.images_beard_selection_03
)

const val CLOTHE_KEY = "clothe"
var selectedClothe = 0
val clothes = listOf(
    R.drawable.images_clothes_selection_none,
    R.drawable.images_clothes_selection_tee,
    R.drawable.images_clothes_selection_suit,
    R.drawable.images_clothes_selection_jacket,
)

data class Category(
    val imageView: ImageView,
    val key: String
)