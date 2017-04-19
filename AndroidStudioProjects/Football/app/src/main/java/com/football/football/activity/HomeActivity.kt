package com.football.football.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.football.football.*
import com.football.football.widget.bottombar.BottomBarTab
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private var baseFragment: BaseFragment? = null

    private var scheduleFragment: ScheduleFragment? = null
    private var scheduleFragment1: ScheduleFragment? = null
    private var scoreFragment: ScoreFragment? = null
    private var scoreFragment1: ScoreFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(TAG, "onCreate()")
        setContentView(R.layout.activity_home)
        getExterData()
        initView()
        initData()
    }

    private fun getExterData() {
        val league = intent.getStringExtra("league")
        Constant.LEAGUE = league
    }

    private fun initView() {
        bottomBar.addItem(BottomBarTab(this, R.drawable.icon1_sel))
                .addItem(BottomBarTab(this, R.drawable.icon2_sel))
                .addItem(BottomBarTab(this, R.drawable.icon3_sel))
                .addItem(BottomBarTab(this, R.drawable.icon4_sel))

        bottomBar.setOnTabClickListener { position -> switchFragment(position) }
        supportActionBar?.title = (Constant.LEAGUE)
    }

    private fun initData() {
        switchFragment(0)
    }


    private fun switchFragment(index: Int) {
        var fragment: BaseFragment? = null
        when (index) {
            0 -> {
                if (scheduleFragment == null) {
                    scheduleFragment = ScheduleFragment(this, 0)
                }
                fragment = scheduleFragment as ScheduleFragment
            }
            1 -> {
                if (scheduleFragment1 == null) {
                    scheduleFragment1 = ScheduleFragment(this, 1)
                }
                fragment = scheduleFragment1 as ScheduleFragment
            }
            2 -> {
                if (scoreFragment == null) {
                    scoreFragment = ScoreFragment(this, 0)
                }
                fragment = scoreFragment as ScoreFragment
            }
            3 -> {
                if (scoreFragment1 == null) {
                    scoreFragment1 = ScoreFragment(this, 1)
                }
                fragment = scoreFragment1 as ScoreFragment
            }
        }
        if (baseFragment == null) {
            replaceContent(fragment as BaseFragment, R.id.fragmentContent)
            baseFragment = fragment
        } else {
            if (fragment != null) {
                switchContent(baseFragment as BaseFragment, fragment, R.id.fragmentContent)
                baseFragment = fragment
            }
        }
    }

    override fun onResume() {
        super.onResume()
        baseFragment!!.userVisibleHint = true
    }

    companion object {

        fun getExterDataIntent(context: Context, league: String): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra("league", league)
            return intent
        }
    }
}
