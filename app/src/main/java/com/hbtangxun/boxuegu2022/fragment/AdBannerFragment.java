package com.hbtangxun.boxuegu2022.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hbtangxun.boxuegu2022.R;

/**
 * 设置ViewPager控制数据
 */
public class AdBannerFragment extends Fragment {

    private String ab; // 广告
    private ImageView iv; // 图片

    public static AdBannerFragment newInstance(Bundle bundle) {
        AdBannerFragment af = new AdBannerFragment();
        af.setArguments(bundle);
        return af;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fragment 接收数据
        Bundle arguments = getArguments();
        ab = arguments.getString("ad");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建广告图片控件
        iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ab != null) {
            if ("banner_1".equals(ab)) {
                iv.setImageResource(R.drawable.banner_1);
            } else if ("banner_2".equals(ab)) {
                iv.setImageResource(R.drawable.banner_2);
            } else if ("banner_3".equals(ab)) {
                iv.setImageResource(R.drawable.banner_3);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iv != null) {
            iv.setImageDrawable(null);
        }
    }


}
