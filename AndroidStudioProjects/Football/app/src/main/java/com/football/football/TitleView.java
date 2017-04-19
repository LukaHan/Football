package com.football.football;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Luka on 2016/7/26.
 * 397308937@qq.com
 * <p>
 * 标题栏
 */
public class TitleView extends FrameLayout implements View.OnClickListener {

    private int mTitleColor;
    private boolean mIsShowBack;
    private int mBackground;
    private TextView tvTitle;
    private ImageView ivMore;
    private String mTitleText;
    private ImageView ivBack;
    private ImageView ivMoreSecond;
    private RelativeLayout rlyt_title;
    private LinearLayout llLine;
    private TextView tvMore;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, 0);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.TitleView_titleText:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.TitleView_titleColor:
                    mTitleColor = typedArray.getColor(attr, defStyleAttr);
                    break;
                case R.styleable.TitleView_showBack:
                    mIsShowBack = typedArray.getBoolean(attr, false);
                    break;
                case R.styleable.TitleView_titleBackground:
                    mBackground = typedArray.getColor(attr, defStyleAttr);
                    break;
            }
        }
        typedArray.recycle();

        LayoutInflater.from(context).inflate(R.layout.layout_widget_title, this);
        initView();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);
        ivBack.setVisibility(mIsShowBack ? VISIBLE : GONE);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(mTitleText);
        tvTitle.setTextColor(mTitleColor == 0 ? Color.BLACK : mTitleColor);
        tvMore = (TextView) findViewById(R.id.tvMore);

        rlyt_title = (RelativeLayout) findViewById(R.id.rlyt_title);
        rlyt_title.setBackgroundColor(mBackground);


        llLine = (LinearLayout) findViewById(R.id.llLine);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
//                ((BaseActivity) getContext()).myFinish();
                break;
        }
    }

    public void setTitle(String text) {
        if (text.length() > 12) {
            tvTitle.setText(text.substring(0, 12));
        } else {
            tvTitle.setText(text);
        }
    }

    public void setTitleVisible(boolean visible) {
        tvTitle.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置右边的图片
     *
     * @param drawable
     */
    public void setMoreImg(int drawable, OnClickListener listener) {
        ivMore = (ImageView) findViewById(R.id.ivMore);
        ivMore.setVisibility(View.VISIBLE);
        ivMore.setImageResource(drawable);
        ivMore.setOnClickListener(listener);
    }

    /**
     * 设置右边的点击事件
     *
     * @param listener
     */
    public void setMoreClickListener(OnClickListener listener) {
        if (ivMore == null) {
            ivMore = (ImageView) findViewById(R.id.ivMore);
        }
        ivMore.setVisibility(View.VISIBLE);
        ivMore.setOnClickListener(listener);
    }

    /**
     * 设置右边的文字
     *
     * @param text
     */
    public void setMoreText(String text, OnClickListener listener) {
        tvMore.setVisibility(VISIBLE);
        tvMore.setText(text);
        tvMore.setOnClickListener(listener);
    }

    public void setBackListener(OnClickListener listener) {
        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setVisibility(VISIBLE);
        ivBack.setOnClickListener(listener);
    }

    public void setBackVisible(boolean visible) {
        ivBack.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置右边的图片
     *
     * @param drawable
     */
    public void setMoreSecondImg(int drawable, OnClickListener listener) {
        ivMoreSecond = (ImageView) findViewById(R.id.ivMoreSecond);
        ivMoreSecond.setVisibility(View.VISIBLE);
        ivMoreSecond.setImageResource(drawable);
        ivMoreSecond.setOnClickListener(listener);
    }

    public void setTitleTextColor(int color) {
        tvTitle.setTextColor(color);
        tvMore.setTextColor(color);
        ivBack.setImageResource(R.drawable.icon_back_white);
    }

    /**
     * 分割线是否可见
     *
     * @param isInvisible
     */
    public void setSplitLineInvisible(boolean isInvisible) {
        if (isInvisible) llLine.setVisibility(GONE);
    }

    public void setBackColorWhite(boolean b) {
        if (b) ivBack.setImageResource(R.drawable.icon_back_white);
    }
}
