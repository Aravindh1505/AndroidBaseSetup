package com.aravindh.andriodbasesetup.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.databinding.ActivityFoodBinding
import com.aravindh.andriodbasesetup.utils.Logger
import com.aravindh.andriodbasesetup.utils.getViewModel

class FoodActivity : AppCompatActivity() {

    lateinit var binding: ActivityFoodBinding

    val viewModel: FoodViewModel by lazy {
        getViewModel { FoodViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_food)

        binding.lifecycleOwner = this
        binding.foodViewModel = viewModel

        viewModel.food.observe(this, Observer { foodList ->
            Logger.d(foodList.size.toString())

           /* if (foodList.isNotEmpty()) {
                for (food in foodList) {
                    createButton(food)
                }
            }*/

        })
    }

   /* private fun createButton(food: Food) {
        // creating the button
        val button_dynamic = Button(this)
        // setting layout_width and layout_height using layout parameters
        button_dynamic.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        button_dynamic.text = food.TabName
        // add Button to LinearLayout
        binding.llFoodItems.addView(button_dynamic)

        button_dynamic.setOnClickListener {

        }
    }*/
}
