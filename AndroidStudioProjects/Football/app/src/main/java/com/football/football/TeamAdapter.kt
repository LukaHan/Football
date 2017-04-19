package com.football.football

import android.widget.TextView
import com.football.football.activity.BaseActivity
import com.football.football.activity.WebViewActivity

/**
 * Created by hanshaobo on 27/03/2017.
 */

class TeamAdapter(private val context: BaseActivity, private val mDatas: List<TeamEntity.ListBean>) : QuickAdapter<TeamEntity.ListBean>(mDatas) {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_shcedule
    }

    override fun convert(holder: QuickAdapter.VH, data: TeamEntity.ListBean, position: Int) {
        holder.itemView.tag = mDatas[position]

        val tvTime = holder.itemView.findViewById(R.id.tvTime) as TextView
        val tvStatus = holder.itemView.findViewById(R.id.tvStatus) as TextView
        val tvHome = holder.itemView.findViewById(R.id.tvHome) as TextView
        val tvScore = holder.itemView.findViewById(R.id.tvScore) as TextView
        val tvVisiting = holder.itemView.findViewById(R.id.tvVisiting) as TextView

        val teamEntity = mDatas[position]
        tvTime.text = teamEntity.c2 + " " + teamEntity.c3
        tvStatus.text = teamEntity.c1
        tvHome.text = teamEntity.c4T1
        tvVisiting.text = teamEntity.c4T2
        tvScore.text = teamEntity.c4R

        holder.itemView.setOnClickListener {
            val intent = WebViewActivity.getExterDataIntent(context, mDatas[position].c53Link)
            context.myStartActivity(intent)
        }
    }
}
