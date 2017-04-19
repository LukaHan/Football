package com.football.football

import android.widget.TextView
import com.football.football.activity.BaseActivity
import com.football.football.activity.TeamActivity

/**
 * Created by hanshaobo on 27/03/2017.
 */

class ScoreAdapter(private val context: BaseActivity, private val mDatas: List<LeagueEntity.ViewsBean.JifenbangBean>) : QuickAdapter<LeagueEntity.ViewsBean.JifenbangBean>(mDatas) {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_score
    }

    override fun convert(holder: QuickAdapter.VH, data: LeagueEntity.ViewsBean.JifenbangBean, position: Int) {
        holder.itemView.tag = mDatas[position]

        val tvRanking = holder.itemView.findViewById(R.id.tvRanking) as TextView
        val tvName = holder.itemView.findViewById(R.id.tvName) as TextView

        val saichengBean = mDatas[position]
        tvRanking.text = saichengBean.c1
        tvName.text = saichengBean.c2

        holder.itemView.setOnClickListener {
            val intent = TeamActivity.getExterDataIntent(context, mDatas[position].c2)
            context.myStartActivity(intent)
        }
    }
}
