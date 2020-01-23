package com.onit.mycarouseltestproject

import android.content.res.Resources
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var a = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val margin = (getWidth() - (getWidth()*73/100)) /2
        Log.e("test", "${(getWidth()*73/100).toInt()}는 이미지의 크기입니다.")

        viewPager.otherPagesWidth = dpToPx((3*margin / 7).toInt())
        viewPager.sliderPageMargin =  dpToPx((4*margin / 7).toInt())
        Log.e("test", "디바이스의 가로 dp는 ${getWidth()}입니다.")


        val list = ArrayList<Card>()
        val card1 = Card("T_membership", type =  1)
        list.add(card1)
        val card2 = Card("CU", null, 1)
        list.add(card2)
        val card4 = Card("T_membership", "CU", 2)
        list.add(card4)
        val card3 = Card("T_membership", null, 1)
        list.add(card3)

        viewPager.adapter = CardAdapter(list, clickCard, dpToPx((getWidth()*73/100).toInt()))
        Log.e("test", "한쪽 마진은 ${margin.toInt()}")
        Log.e("test", "양옆 페이지 가로는 ${(4*margin/7).toInt()}")
        Log.e("test", "마진은 ${(3*margin/7).toInt()}")

        val cardName = (viewPager.adapter as CardAdapter).getCardName(0)
        pointCardNameTextView.text = cardName

        //viewPager.sliderPageMargin = margin/5
        //viewPager.otherPagesWidth = dpToPx((margin / 5).toInt())

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                val cardName = (viewPager.adapter as CardAdapter).getCardName(position)
                pointCardNameTextView.text = cardName
            }
        })

        button1.setOnClickListener {
            viewPager.currentItem = a % 3
            a += 1
        }

        viewPager
    }

    private val clickCard: (String, Int) -> Unit = { cardId, position ->
        if (viewPager.currentItem == position) {
            // 현재 페이지의 포지션과 누른 아이템의 포지션이 같기 때문에 누른 아이템의 정보를 보여줌
            Toast.makeText(applicationContext, "${cardId}", Toast.LENGTH_SHORT).show()
        } else {
            // 현재 페이지의 포지션과 누른 아이템의 포지션이 다르다는건 양 옆의 아이템을 터치한것이기 때문에 정보를 보여주지 않고 이동함
            viewPager.currentItem = position
        }
    }

    private fun getWidth(): Float {
        val display = windowManager.defaultDisplay // in case of Activity
        val size = Point()
        display.getSize(size)
        val width = size.x

        return pxToDp(width)
    }

    fun dpToPx(dp: Int): Float {
        val px = dp * Resources.getSystem().displayMetrics.density
        return px
    }

    fun pxToDp(px: Int): Float {
        val dp = px / Resources.getSystem().displayMetrics.density
        return dp
    }
}
