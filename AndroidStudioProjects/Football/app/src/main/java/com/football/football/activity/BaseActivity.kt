package com.football.football.activity

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.football.football.BaseFragment
import com.football.football.rxjava.RxjavaUtil
import com.football.football.rxjava.UITask

/**
 * Created by lukahan on 2016/9/6.
 */
open class BaseActivity : AppCompatActivity(), GestureDetector.OnGestureListener, View.OnTouchListener {
    var TAG = "cmlog"

    private var mProgressDialog: ProgressDialog? = null

    private var inflater: LayoutInflater? = null
    private val view: View? = null
    private var gestureDetector: GestureDetector? = null
    private val toolbar: Toolbar? = null
    private val toolbarTitle: TextView? = null

    private val isShowToolbarMenu: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        inflater = layoutInflater
        gestureDetector = GestureDetector(this, this)
    }

    /**
     * toolbar start
     */
    //在基类的setContetView()中添加toolbar并添加title
//    override fun setContentView(@LayoutRes layoutResID: Int) {
//        this.setContentView(layoutResID, false)
//    }
//
//    fun setContentView(@LayoutRes layoutResID: Int, isShowToolbarMenu: Boolean) {
//        super.setContentView(layoutResID)
//    }


    //响应子类的setTitle()方法
    override fun onTitleChanged(title: CharSequence, color: Int) {
        super.onTitleChanged(title, color)
        if (toolbarTitle != null) {
            toolbarTitle.text = title
        }
    }


    /**
     * toolbar end
     */


    /**
     * 设置fragment

     * @param fragment
     * *
     * @param fragmentResId
     */
    fun replaceContent(fragment: BaseFragment, fragmentResId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentResId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun switchContent(from: Fragment, to: Fragment, id: Int) {
        if (from !== to) {
            val transaction = supportFragmentManager.beginTransaction()
            if (!to.isAdded) {
                transaction.hide(from).add(id, to).commitAllowingStateLoss()
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss()
            }
        }
    }

    /**
     * 跳转方式

     * @param cls
     * *
     * @param needFinish
     */
    fun myStartActivity(cls: Class<*>, needFinish: Boolean) {
        val intent = Intent(this, cls)
        startActivity(intent)
        if (needFinish) this.finish()
    }

    fun myStartActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    fun myStartActivity(intent: Intent) {
        startActivity(intent)
    }

    fun myStartActivity(intent: Intent, needFinish: Boolean) {
        startActivity(intent)
        //        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        if (needFinish) this.finish()
    }

    fun myStartActivityForResult(cls: Class<*>, requestCode: Int) {
        val intent = Intent(this, cls)
        startActivityForResult(intent, requestCode)
        //        overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    fun myStartActivityForResult(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }

    fun myFinish() {
        finish()
        //        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    @JvmOverloads fun showProgress(message: String = "请稍候...") {
        RxjavaUtil.doInUIThread(object : UITask<Any>() {
            override fun doInUIThread() {
                if (mProgressDialog == null) {
                    mProgressDialog = ProgressDialog(this@BaseActivity)
                    mProgressDialog!!.setCanceledOnTouchOutside(false)
                }
                mProgressDialog!!.setMessage(message)
                mProgressDialog!!.show()
            }
        })

        //        mProgressDialog = new ProgressDialog(BaseActivity.this);
        //        mProgressDialog.setCanceledOnTouchOutside(false);
        //        mProgressDialog.setMessage(message);
        //        mProgressDialog.show();
    }

    fun hideProgress() {
        RxjavaUtil.doInUIThread(object : UITask<Any>() {
            override fun doInUIThread() {
                if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                    mProgressDialog!!.hide()
                }
            }
        })
    }

    override fun onDestroy() {
        //        LogUtil.d(TAG, "onDestroy:" + this.getClass().getSimpleName());
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        //        super.onBackPressed();
        myFinish()
    }

    /**
     * gesture start
     */
    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {

    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    /**
     * gesture end
     */

    /**
     * touch start
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        gestureDetector!!.onTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector!!.onTouchEvent(event)
    }

    /**
     * touch end
     */

    //    public  static Intent getExterDataIntent(Context context, VinQueryEntity vinQueryEntity) {
    //        Intent intent = new Intent(context, VinPayActivity.class);
    //        intent.putExtra("vinQueryEntity", vinQueryEntity);
    //        return intent;
    //    }
    //
    //    private void getExterData() {
    //        Intent intent = getIntent();
    //        vinQueryEntity = (VinQueryEntity) intent.getSerializableExtra("vinQueryEntity");
    //    }


    fun showDialog(message: String, listener: DialogInterface.OnClickListener) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(message)
        dialog.setPositiveButton("返回", listener)
        dialog.setCancelable(false)
        dialog.show()
    }
}
