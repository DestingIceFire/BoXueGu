package com.hbtangxun.boxuegu2022.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.hbtangxun.boxuegu2022.R;

/**
 * 自定义控件
 * 轮播图的小圆点控件
 */
public class ViewPagerIndicator extends LinearLayout {

    private int mCount;//小圆点的个数
    private int mIndex;//当前小圆点的位置
    private Context mContext;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setGravity(Gravity.CENTER);//设置此布局居中
    }

    /**
     * 动态设置小圆点的数目
     *
     * @param count
     */
    public void setCount(int count) {
        this.mCount = count;
    }

    /**
     * 设置滑动到点前小数点时其他圆点的位置
     *
     * @param currentIndex
     */
    public void setCurrentPosition(int currentIndex) {

        mIndex = currentIndex; // 当前小圆点位置
        removeAllViews();//移除界面上存在的View
        for (int i = 0; i < mCount; i++) {
            //创建一个ImageView来存放小圆点的图片
            ImageView imageView = new ImageView(mContext);
            if (mIndex == i) {
                // 当前小圆点的位置与循坏的值相等时，小圆点是蓝色的
                imageView.setImageResource(R.drawable.indicator_on);
            } else {
                // 当前小圆点的位置与循坏的值不相等时，小圆点是灰色的
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(5, 0, 5, 0);
            addView(imageView);
        }
    }
}
