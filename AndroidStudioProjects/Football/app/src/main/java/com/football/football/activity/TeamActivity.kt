package com.football.football.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.football.football.Constant
import com.football.football.R
import com.football.football.TeamAdapter
import com.football.football.TeamEntity
import com.football.football.entity.base.HttpResult
import com.football.football.http.HttpMethods
import com.football.football.http.HttpResultSubscriber
import com.football.football.http.TransformUtils
import kotlinx.android.synthetic.main.fragment_schedule.*
import java.util.*

/**
 * Created by hanshaobo on 10/04/2017.
 */

class TeamActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var list: ArrayList<TeamEntity.ListBean>? = null
    private var mAdapter: TeamAdapter? = null
    private var mTeam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_schedule)
        getExterData()
        initView()
        initData()
    }

    private fun getExterData() {
        mTeam = intent.getStringExtra("team")
    }


    private fun initView() {
        supportActionBar?.title = mTeam

        srfLayout!!.setOnRefreshListener(this)
        rcvList!!.setHasFixedSize(true)
        rcvList!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun initData() {
        list = ArrayList<TeamEntity.ListBean>()
        mAdapter = TeamAdapter(this, list as ArrayList<TeamEntity.ListBean>)

        rcvList!!.adapter = mAdapter

        srfLayout!!.post { srfLayout!!.isRefreshing = true }
        onRefresh()
    }

    fun getTeamInfo() {
        HttpMethods.createService().getTeamInfo(Constant.KEY, mTeam)
                .compose(TransformUtils.defaultSchedulers<HttpResult<TeamEntity>>())
                .subscribe(object : HttpResultSubscriber<TeamEntity>() {
                    override fun onSuccess(result: TeamEntity?, reason: String?) {
                        setData(result!!)
                    }

                    override fun _onError(code: Int, throwable: Throwable?, result: TeamEntity?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onCompleted() {
                        srfLayout!!.post { srfLayout!!.isRefreshing = false }
                        super.onCompleted()
                    }

                })
    }

    private fun setData(result: TeamEntity) {
        list!!.addAll(result.list)

        mAdapter!!.notifyDataSetChanged()
    }

    override fun onRefresh() {
        list!!.clear()
        getTeamInfo()
    }

    companion object {
        fun getExterDataIntent(context: Context, team: String): Intent {
            val intent = Intent(context, TeamActivity::class.java)
            intent.putExtra("team", team)
            return intent
        }
    }
}
