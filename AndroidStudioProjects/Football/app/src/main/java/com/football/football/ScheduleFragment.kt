package com.football.football

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.football.football.activity.BaseActivity
import com.football.football.activity.HomeActivity
import com.football.football.entity.base.HttpResult
import com.football.football.http.HttpMethods
import com.football.football.http.HttpResultSubscriber
import com.football.football.http.TransformUtils
import kotlinx.android.synthetic.main.fragment_schedule.*
import java.util.*

/**
 * Created by hanshaobo on 09/04/2017.
 */

class ScheduleFragment(private val activity: HomeActivity, private val index: Int) : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private var list: ArrayList<LeagueEntity.ViewsBean.Saicheng1Bean>? = null
    private var mAdapter: ScheduleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        srfLayout?.setOnRefreshListener(this)
        rcvList?.setHasFixedSize(true)
        rcvList?.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)

    }

    private fun initData() {
        list = ArrayList<LeagueEntity.ViewsBean.Saicheng1Bean>()
        mAdapter = ScheduleAdapter(getActivity() as BaseActivity, list as ArrayList<LeagueEntity.ViewsBean.Saicheng1Bean>)
        rcvList!!.adapter = mAdapter

        srfLayout!!.post { srfLayout!!.isRefreshing = true }
        onRefresh()
    }

    fun getLeague() {
        HttpMethods.createService().getLeague(Constant.KEY, Constant.LEAGUE)
                .compose(TransformUtils.defaultSchedulers<HttpResult<LeagueEntity>>())
                .subscribe(object : HttpResultSubscriber<LeagueEntity>() {
                    override fun onSuccess(result: LeagueEntity, reason: String) {
                        setData(result)
                    }

                    override fun _onError(code: Int, throwable: Throwable, result: LeagueEntity) {
                        LogUtil.d(TAG, "error:" + throwable.message)
                    }

                    override fun onCompleted() {
                        srfLayout!!.post { srfLayout!!.isRefreshing = false }
                        super.onCompleted()
                    }
                })
    }

    private fun setData(result: LeagueEntity) {
        if (index == 0) {
            activity.supportActionBar!!.title = result.tabs.saicheng1
            list!!.addAll(result.views.saicheng1)
        } else {
            activity.supportActionBar!!.title = result.tabs.saicheng2

            list!!.addAll(result.views.saicheng2)
        }
        mAdapter!!.notifyDataSetChanged()
    }

    override fun onRefresh() {
        list!!.clear()
        getLeague()
    }
}
