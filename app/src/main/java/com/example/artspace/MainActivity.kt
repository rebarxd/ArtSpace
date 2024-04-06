package com.example.artspace

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.util.Log


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView

    private var currentIndex = 0
    private val imageResources = arrayOf(R.drawable.opm, R.drawable.berserk, R.drawable.haikyuu) // Add more image resources as needed
    private val titles = arrayOf(R.string.spaceship, R.string.swordsman, R.string.fly) // Titles corresponding to each image
    private val descriptions = arrayOf(R.string.opm, R.string.berserk, R.string.haikyuu) // Descriptions corresponding to each image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageView = findViewById(R.id.imageView3)
        titleTextView = findViewById(R.id.textView2)
        descriptionTextView = findViewById(R.id.textView3)
        updateContent()

        val previousButton: Button = findViewById(R.id.previous)
        val nextButton: Button = findViewById(R.id.next)

        previousButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateContent()
            }

        }

        nextButton.setOnClickListener {
            if (currentIndex < imageResources.size - 1) {
                currentIndex++
                updateContent()
            }
        }
    }
    private fun updateContent() {
        if (currentIndex < 0 || currentIndex >= imageResources.size) {
            return  // Index out of bounds protection
        }

        try {
            imageView.setImageResource(imageResources[currentIndex])
            titleTextView.setText(titles[currentIndex])
            descriptionTextView.setText(descriptions[currentIndex])

            // Log current index and values for debugging
            Log.d("MainActivity", "Current Index: $currentIndex")
            Log.d("MainActivity", "Title: ${resources.getString(titles[currentIndex])}")
            Log.d("MainActivity", "Description: ${resources.getString(descriptions[currentIndex])}")
        } catch (e: Exception) {
            Log.e("MainActivity", "Error updating content: ${e.message}")
            e.printStackTrace()
        }
    }


}