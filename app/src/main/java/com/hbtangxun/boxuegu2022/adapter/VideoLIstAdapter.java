package com.hbtangxun.boxuegu2022.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.bean.VideoBean;
import com.hbtangxun.boxuegu2022.utils.OnClickItemListener;

import java.util.List;

/**
 * 视频 适配器
 */
public class VideoLIstAdapter extends BaseAdapter {

    private Context mContext;
    private List<VideoBean> beans;
    private int clickFlag = -1; // 点击时选中的位置
    private OnClickItemListener itemListener;

    public VideoLIstAdapter(Context mContext, OnClickItemListener itemListener) {
        this.mContext = mContext;
        this.itemListener = itemListener;
    }

    public void setData(List<VideoBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    public void setClickPosition(int clickPosition) {
        this.clickFlag = clickPosition;
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public VideoBean getItem(int position) {
        return beans == null ? null : beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.video_list_item, null);
            holder.tv_video_title = convertView.findViewById(R.id.tv_video_title);
            holder.iv_left_icon = convertView.findViewById(R.id.iv_left_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final VideoBean bean = getItem(position);
        holder.tv_video_title.setTextColor(Color.parseColor("#333333"));
        holder.iv_left_icon.setImageResource(R.drawable.course_bar_icon);
        if (bean != null) {
            holder.tv_video_title.setText(bean.getSecondTitle());
            //设置选中的效果
            if (clickFlag == position) {
                holder.tv_video_title.setTextColor(Color.parseColor("#009958"));
                holder.iv_left_icon.setImageResource(R.drawable.course_intro_icon);
            } else {
                holder.tv_video_title.setTextColor(Color.parseColor("#333333"));
                holder.iv_left_icon.setImageResource(R.drawable.course_bar_icon);
            }
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null) {
                    return;
                }
                //播放视频
                itemListener.onItemClick(position, holder.iv_left_icon);
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView iv_left_icon;
        TextView tv_video_title;
    }

}
