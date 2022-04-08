package com.hbtangxun.boxuegu2022.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.activity.VideoPlayActivity;
import com.hbtangxun.boxuegu2022.bean.VideoBean;

import java.util.List;

/**
 *  播放记录 适配器
 */

public class PlayHistoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<VideoBean> videoBeans;

    //加载图片
    private int[] imgs = {R.drawable.video_play_icon1, R.drawable.video_play_icon2, R.drawable.video_play_icon3, R.drawable.video_play_icon4,
            R.drawable.video_play_icon5, R.drawable.video_play_icon6, R.drawable.video_play_icon7, R.drawable.video_play_icon8,
            R.drawable.video_play_icon9, R.drawable.video_play_icon10,};

    public PlayHistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<VideoBean> videoBeans) {
        this.videoBeans = videoBeans;
    }

    @Override
    public int getCount() {
        return videoBeans == null ? 0 : videoBeans.size();
    }

    @Override
    public VideoBean getItem(int position) {
        return videoBeans == null ? null : videoBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.play_history_list_item, null);
            holder.iv_his_icon = convertView.findViewById(R.id.iv_his_icon);
            holder.tv_his_title = convertView.findViewById(R.id.tv_his_title);
            holder.tv_his_second_title = convertView.findViewById(R.id.tv_his_second_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final VideoBean bean = videoBeans.get(position);
        if (bean != null) {
            holder.tv_his_title.setText(bean.getTitle());
            holder.tv_his_second_title.setText(bean.getSecondTitle());
            holder.iv_his_icon.setImageResource(imgs[position]);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("videoPath", bean.getVideoPath());
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageView iv_his_icon;
        TextView tv_his_title, tv_his_second_title;
    }

}
