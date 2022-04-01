package com.hbtangxun.boxuegu2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.bean.CourseBean;
import com.hbtangxun.boxuegu2022.utils.ToolUtils;

import java.util.List;

/**
 * 课程 适配器
 * 使用的是 GirdView
 */
public class CourseAdapter extends BaseAdapter {

    private Context mContext;
    private List<CourseBean> beans;

    // 课程图片
    private int[] imgs = {R.drawable.chapter_1_icon, R.drawable.chapter_2_icon,
            R.drawable.chapter_3_icon, R.drawable.chapter_4_icon, R.drawable.chapter_5_icon,
            R.drawable.chapter_6_icon, R.drawable.chapter_7_icon, R.drawable.chapter_8_icon,
            R.drawable.chapter_9_icon, R.drawable.chapter_10_icon,};

    public CourseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<CourseBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans == null ? 0 : beans.size();
    }

    @Override
    public CourseBean getItem(int position) {
        return beans == null ? null : beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.course_grid_item, null);
            vh.tv_item_course_title = convertView.findViewById(R.id.tv_item_course_title);
            vh.tv_item_course_img = convertView.findViewById(R.id.tv_item_course_img);
            vh.iv_item_course_img = convertView.findViewById(R.id.iv_item_course_img);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final CourseBean item = getItem(position);
        if (item != null) {
            vh.tv_item_course_title.setText(item.getTitle());
            vh.tv_item_course_img.setText(item.getImgTitle());
            vh.iv_item_course_img.setImageResource(imgs[position]);
            vh.iv_item_course_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击进入对应的课程详情界面
                    ToolUtils.showShortToast(mContext, "课程详情" + position);
                }
            });
        }

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_item_course_img, tv_item_course_title;
        private ImageView iv_item_course_img;
    }
}
