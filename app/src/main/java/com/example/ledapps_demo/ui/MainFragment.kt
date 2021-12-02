package com.example.ledapps_demo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ledapps_demo.R
import com.example.ledapps_demo.databinding.FragmentMainBinding
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.Color
import android.graphics.LightingColorFilter
import androidx.lifecycle.lifecycleScope
import com.example.ledapps_demo.zoomInImage
import com.example.ledapps_demo.zoomOutImage
import kotlinx.coroutines.delay


/**
 *@Created by Yerimah on 01/12/2021.
 */

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var categories: List<Category>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        categories = listOf(
            Category(binding.hairSelector, HAIR_KEY),
            Category(binding.beardSelector, BEARD_KEY),
            Category(binding.clotheSelector, CLOTHE_KEY)
        )

        categories.forEach { initSelector(it.imageView, it.key) }

        lifecycleScope.launchWhenCreated {
            delay(300)
            binding.hairSelector.performClick()
        }
    }


    private fun initSelector(imageView: ImageView, key: String) {

        imageView.setOnClickListener {
            imageView.setBackgroundResource(R.drawable.selected_category_background)
            categories.forEach {
                if (imageView.id != it.imageView.id) {
                    it.imageView.setBackgroundResource(0)
                    imageView.drawable.colorFilter = LightingColorFilter(Color.WHITE, Color.WHITE)
                }
                else {
                    imageView.drawable.colorFilter = LightingColorFilter(Color.GRAY, Color.DKGRAY)
                }
            }
            when (key) {
                HAIR_KEY -> {
                    initRecyclerView(hairCuts, selectedHair, HAIR_KEY)
                    zoomInImage(binding.displayImageView)
                }
                BEARD_KEY -> {
                    initRecyclerView(beards, selectedBeard, BEARD_KEY)
                    zoomInImage(binding.displayImageView)
                }
                CLOTHE_KEY -> {
                    initRecyclerView(clothes, selectedClothe, CLOTHE_KEY)
                    zoomOutImage(binding.displayImageView)
                }
            }
        }

    }



    private fun initRecyclerView(items: List<Int>, selectedIndex: Int, key: String) {

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        binding.categoryRecyclerView.layoutManager = layoutManager
        binding.categoryRecyclerView.adapter = CategoryAdapter(items, selectedIndex) {
            when (key) {
                HAIR_KEY -> selectedHair = it
                BEARD_KEY -> selectedBeard = it
                CLOTHE_KEY -> selectedClothe = it
            }
        }

    }
}