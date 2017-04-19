package com.football.football

import android.widget.TextView
import com.football.football.activity.BaseActivity
import com.football.football.activity.WebViewActivity

/**
 * Created by hanshaobo on 27/03/2017.
 */

class ScheduleAdapter(private val context: BaseActivity, private val mDatas: List<LeagueEntity.ViewsBean.Saicheng1Bean>) : QuickAdapter<LeagueEntity.ViewsBean.Saicheng1Bean>(mDatas) {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_shcedule
    }

    override fun convert(holder: QuickAdapter.VH, data: LeagueEntity.ViewsBean.Saicheng1Bean, position: Int) {
        holder.itemView.tag = mDatas[position]

        val tvTime = holder.itemView.findViewById(R.id.tvTime) as TextView
        val tvStatus = holder.itemView.findViewById(R.id.tvStatus) as TextView
        val tvHome = holder.itemView.findViewById(R.id.tvHome) as TextView
        val tvScore = holder.itemView.findViewById(R.id.tvScore) as TextView
        val tvVisiting = holder.itemView.findViewById(R.id.tvVisiting) as TextView

        val saichengBean = mDatas[position]
        tvTime.text = saichengBean.c2 + " " + saichengBean.c3
        tvStatus.text = saichengBean.c1
        tvHome.text = saichengBean.c4T1
        tvVisiting.text = saichengBean.c4T2
        tvScore.text = saichengBean.c4R

        holder.itemView.setOnClickListener {
            val intent = WebViewActivity.getExterDataIntent(context, mDatas[position].c4T2URL)
            context.myStartActivity(intent)
        }
    }
}
