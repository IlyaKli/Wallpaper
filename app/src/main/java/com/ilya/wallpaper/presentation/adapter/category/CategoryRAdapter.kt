package com.ilya.wallpaper.presentation.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ilya.wallpaper.databinding.CategoryItemBinding
import com.ilya.wallpaper.domain.model.Category

class CategoryRAdapter : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null

    var categoryClickListener: ((category: Category) -> Unit)? = null

    init {
        val categories = mutableListOf<Category>()
        for (index in 0..19) {
            var name = ""
            var russianName = ""
            when (index) {
                0 -> {
                    name = "backgrounds"
                    russianName = "фоны"
                }
                1 -> {
                    name = "fashion"
                    russianName = "мода"
                }
                2 -> {
                    name = "nature"
                    russianName = "природа"
                }
                3 -> {
                    name = "science"
                    russianName = "наука"
                }
                4 -> {
                    name = "education"
                    russianName = "образование"
                }
                5 -> {
                    name = "feelings"
                    russianName = "чувства"
                }
                6 -> {
                    name = "health"
                    russianName = "здоровье"
                }
                7 -> {
                    name = "people"
                    russianName = "люди"
                }
                8 -> {
                    name = "religion"
                    russianName = "религия"
                }
                9 -> {
                    name = "places"
                    russianName = "места"
                }
                10 -> {
                    name = "animals"
                    russianName = "животные"
                }
                11 -> {
                    name = "industry"
                    russianName = "промышленность"
                }
                12 -> {
                    name = "computer"
                    russianName = "компьютер"
                }
                13 -> {
                    name = "food"
                    russianName = "еда"
                }
                14 -> {
                    name = "sports"
                    russianName = "спорт"
                }
                15 -> {
                    name = "transportation"
                    russianName = "транспорт"
                }
                16 -> {
                    name = "travel"
                    russianName = "путешествие"
                }
                17 -> {
                    name = "buildings"
                    russianName = "здания"
                }
                18 -> {
                    name = "business"
                    russianName = "бизнес"
                }
                19 -> {
                    name = "music"
                    russianName = "музыка"
                }
            }
            categories.add(index, Category(index, name, russianName))
        }
        submitList(categories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)

        with(holder.binding) {
            categoryItemTextView.text = category.russianName
            categoryCardView.setOnClickListener {
                categoryClickListener?.invoke(category)
            }
        }
    }
}