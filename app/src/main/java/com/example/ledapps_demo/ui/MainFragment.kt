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
                    zoomInImage()
                }
                BEARD_KEY -> {
                    initRecyclerView(beards, selectedBeard, BEARD_KEY)
                    zoomInImage()
                }
                CLOTHE_KEY -> {
                    initRecyclerView(clothes, selectedClothe, CLOTHE_KEY)
                    zoomOutImage()
                }
            }
        }

    }

    private fun zoomInImage() {
        if (isFullDisplay) {
            val anim = ValueAnimator.ofFloat(1f, 1.5f)
            anim.duration = 1000
            anim.addUpdateListener { animation ->
                binding.displayImageView.scaleX = animation.animatedValue as Float
                binding.displayImageView.scaleY = animation.animatedValue as Float
            }
            anim.start()
            height = true
            binding.displayImageView.animate()
                .translationYBy(500f)
                .setDuration(1000).start()
            isFullDisplay = false
        }
    }

    private var isFullDisplay = true
    private var height = false

    private fun zoomOutImage() {

        val anim = ValueAnimator.ofFloat(1.5f, 1f)
        anim.duration = 1000
        anim.addUpdateListener { animation ->
            binding.displayImageView.scaleX = animation.animatedValue as Float
            binding.displayImageView.scaleY = animation.animatedValue as Float
        }
        anim.start()

        if (height) {
            binding.displayImageView.animate()
                .translationYBy(-500f)
                .setDuration(1000).start()
            height = false
        }
        isFullDisplay = true
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