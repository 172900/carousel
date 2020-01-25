package com.onit.mycarouseltestproject

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.islamkhsh.CardSliderAdapter
import java.util.*

class CardAdapter(items: ArrayList<Card>, private val clickCardListener: (String, Int) -> (Unit), val itemWidth: Float, val itemHeight: Float) : CardSliderAdapter<Card>(items) {


    override fun bindView(position: Int, itemContentView: View, item: Card?) {
        val card = getItem(position) as Card

        if(card.type == 2) {
            val constraintLayout = itemContentView.findViewById<ConstraintLayout>(R.id.centerConstraintLayout)
            val params1 : ViewGroup.LayoutParams = constraintLayout.layoutParams
            params1.width = itemWidth.toInt()
            params1.height = itemHeight.toInt()


            itemContentView.findViewById<ImageView>(R.id.firstImage).setOnClickListener {
                clickCardListener(card.firstName, position)
            }
            itemContentView.findViewById<ImageView>(R.id.secondImage).setOnClickListener {
                clickCardListener(card.secondName!!, position)
            }
        } else {
            val imageView = itemContentView.findViewById<ImageView>(R.id.firstImage)
            imageView.setOnClickListener {
                clickCardListener(card.firstName, position)
            }
            val params : ViewGroup.LayoutParams = imageView.layoutParams
            params.width = itemWidth.toInt()
            Log.e("test", "item높이는 !!!! ${itemHeight.toInt()}")
            params.height = itemHeight.toInt()
            imageView.layoutParams = params
        }

        /*itemContentView.setOnClickListener {
          /  clickListener(position)
        }*/
    }

    fun getCardName(position: Int): String? {
        if (getItem(position)?.type == 1) {
            return getItem(position)?.firstName
        } else {
            return "${getItem(position)?.firstName} + ${getItem(position)?.secondName}"
        }

    }

    override fun getItemContentLayout(position: Int): Int {
        val card = getItem(position) as Card
        return if(card.type == 2) {
            R.layout.card_two_item
        } else {
            R.layout.card_item
        }
    }
}