package com.hbtangxun.boxuegu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.bean.VideoBean;
import com.hbtangxun.boxuegu.utils.onClickItemListener;

import java.util.List;

public class VideoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<VideoBean> videoBeans;
    private int clickPosition = -1; //点击时选中的位置
    private onClickItemListener itemListener;

    public VideoListAdapter(Context mContext, onClickItemListener itemListener) {
        this.mContext = mContext;
        this.itemListener = itemListener;
    }

    /**
     * 设置数据更新页面
     *
     * @param videoBeans
     */
    public void setData(List<VideoBean> videoBeans) {
        this.videoBeans = videoBeans;
        notifyDataSetChanged();
    }

    public void setClickPosition(int clickPosition) {
        this.clickPosition = clickPosition;
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
            //设置选中效果
            if (clickPosition == position) {
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
                if (bean == null)
                    return;
                //播放视频
                itemListener.onItemClick(position, holder.iv_left_icon);
            }
        });

        return convertView;
    }

    class ViewHolder {
        public TextView tv_video_title;
        public ImageView iv_left_icon;
    }

}
