package com.hbtangxun.boxuegu2022.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.hbtangxun.boxuegu2022.R;


/**
 * 轮播图的小圆点控件
 */
public class ViewPagerIndicator extends LinearLayout {

    private int mCount;  //小圆点的个数
    private int mIndex;  //当前小圆点的位置
    private Context mContext;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setGravity(Gravity.CENTER); //设置此布局居中
    }

    /**
     * 设置滑动到当前小数点时其他圆点的位置
     *
     * @param currentIndex
     */
    public void setCurrentPosition(int currentIndex) {
        mIndex = currentIndex; //当前小圆点
        removeAllViews();      //移除界面上存在的View
        int pex = 5;
        for (int i = 0; i < mCount; i++) {
            //创建一个ImageView控件来放置小圆点
            ImageView imageView = new ImageView(mContext);
            if (mIndex == i) {
                imageView.setImageResource(R.drawable.indicator_on);
            } else {
                imageView.setImageResource(R.drawable.indicator_off);
            }

            imageView.setPadding(pex, 0, pex, 0);
            addView(imageView);

        }
    }

    /**
     * 设置小圆点的数目
     *
     * @param count
     */
    public void setCount(int count) {
        this.mCount = count;
    }

}
