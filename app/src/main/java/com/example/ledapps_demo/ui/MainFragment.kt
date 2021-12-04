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
import androidx.core.view.updatePadding
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
                    zoomInImage(binding.characterFrame)
                }
                BEARD_KEY -> {
                    initRecyclerView(beards, selectedBeard, BEARD_KEY)
                    zoomInImage(binding.characterFrame)
                }
                CLOTHE_KEY -> {
                    initRecyclerView(clothes, selectedClothe, CLOTHE_KEY)
                    zoomOutImage(binding.characterFrame)
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
                HAIR_KEY -> displayHair(it)
                BEARD_KEY -> displayBeard(it)
                CLOTHE_KEY -> displayClothe(it)
            }
        }

    }

    private fun displayHair(selectedIndex: Int) {
        when (selectedIndex) {
            0 -> binding.hairImageView.setImageResource(0)
            1 -> binding.hairImageView.setImageResource(R.drawable.images_hair_04)
            2 -> binding.hairImageView.setImageResource(R.drawable.images_hair_03)
            3 -> binding.hairImageView.setImageResource(R.drawable.images_hair_02)
        }
        selectedHair = selectedIndex
    }

    private fun displayBeard(selectedIndex: Int) {
        when (selectedIndex) {
            0 -> binding.beardImageView.setImageResource(0)
            1 -> binding.beardImageView.setImageResource(R.drawable.images_beard_01)
            2 -> binding.beardImageView.setImageResource(R.drawable.images_beard_02)
        }
        selectedBeard = selectedIndex
    }

    private fun displayClothe(selectedIndex: Int) {
        when (selectedIndex) {
            0 -> {
                binding.clothesImageview.setImageResource(0)
                binding.clothesImageview.updatePadding(top = 70)
            }
            1 -> {
                binding.clothesImageview.setImageResource(R.drawable.images_clothes_tee)
                binding.clothesImageview.updatePadding(top = 80)
            }
            2 -> {
                binding.clothesImageview.setImageResource(R.drawable.images_clothes_suit)
                binding.clothesImageview.updatePadding(top = 50)
            }
            3 -> {
                binding.clothesImageview.setImageResource(R.drawable.images_clothes_jacket)
                binding.clothesImageview.updatePadding(top = 10)
            }
        }
        selectedClothe = selectedIndex
    }

}