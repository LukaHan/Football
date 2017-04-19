package com.football.football.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.football.football.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val league = arrayOf("英超", "德甲", "西甲", "意甲", "中超")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        for (i in league.indices) {
            val button = Button(this)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(200, 20, 200, 20)
            button.layoutParams = params
            button.text = league[i]
            val finalI = i
            button.setOnClickListener { myStartActivity(HomeActivity.getExterDataIntent(this, league[finalI])) }

            llContent?.addView(button)
        }

        supportActionBar!!.title = "选择联赛"
    }
}
