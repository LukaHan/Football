package com.football.football.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.football.football.R

/**
 * Created by hanshaobo on 16/10/10.
 */
class WebViewActivity : BaseActivity() {
    private var webview: WebView? = null
    private var mUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        showProgress()
        initView()
        getExterData()
    }

    private fun getExterData() {
        mUrl = intent.getStringExtra("url")
    }

    private fun initView() {
        webview = findViewById(R.id.webview) as WebView
        webview!!.settings.javaScriptEnabled = true// 设置在webview中可用js
        webview!!.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)// 提高渲染的优先级
        webview!!.settings.blockNetworkImage = true// 把图片加载放在最后来加载渲染
        webview!!.settings.cacheMode = WebSettings.LOAD_NO_CACHE// 如果内容已经存在cache
        webview!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        })

        Handler().postDelayed({
            webview!!.loadUrl(mUrl)

            hideProgress()
        }, 1000)
    }

    companion object {

        fun getExterDataIntent(context: Context, url: String): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", url)
            return intent
        }
    }
}
