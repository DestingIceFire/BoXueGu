package com.hbtangxun.boxuegu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.activity.VideoListActivity;
import com.hbtangxun.boxuegu.bean.CourseBean;

import java.util.List;

/**
 * 课程 适配器
 */
public class CourseAdapter extends BaseAdapter {

    private Context mContext;
    private List<CourseBean> cb1;

    //加载图片 实验ing
    private int[] imgs = {R.drawable.chapter_1_icon, R.drawable.chapter_2_icon, R.drawable.chapter_3_icon,
            R.drawable.chapter_4_icon, R.drawable.chapter_5_icon, R.drawable.chapter_6_icon, R.drawable.chapter_7_icon,
            R.drawable.chapter_8_icon, R.drawable.chapter_9_icon, R.drawable.chapter_10_icon};

    public CourseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 设置数据更新界面
     *
     * @param cb1
     */
    public void setData(List<CourseBean> cb1) {
        this.cb1 = cb1;
        notifyDataSetChanged();
    }

    /**
     * 获取Item的总数
     *
     * @return
     */
    @Override
    public int getCount() {
        return cb1 == null ? 0 : cb1.size();
    }

    /**
     * 根据position得到对应的Item的对象
     *
     * @param position
     * @return
     */
    @Override
    public CourseBean getItem(int position) {
        return cb1 == null ? null : cb1.get(position);
    }

    /**
     * 根据position得到对应的Item的ID
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到相应的position对应的Item布局
     * Position是当前Item的位置
     * convertView参数就是画出屏幕的Item的View
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.course_grid_item, null);
            vh.tv_item_course_title = convertView.findViewById(R.id.tv_item_course_title);
            vh.tv_item_course_img = convertView.findViewById(R.id.tv_item_course_img);
            vh.iv_item_course_img = convertView.findViewById(R.id.iv_item_course_img);
            convertView.setTag(vh);
        } else {
            //复用convertView
            vh = (ViewHolder) convertView.getTag();
        }

        final CourseBean bean = getItem(position);
        if (bean != null) {
            vh.tv_item_course_title.setText(bean.getTitle());
            vh.tv_item_course_img.setText(bean.getImgTitle());
            vh.iv_item_course_img.setImageResource(imgs[position]);
            vh.iv_item_course_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击进入课程的详情页面
                    Intent intent = new Intent(mContext, VideoListActivity.class);
                    intent.putExtra("id", bean.getId());
                    intent.putExtra("intro", bean.getIntro());
                    mContext.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        public TextView tv_item_course_title, tv_item_course_img;
        public ImageView iv_item_course_img;
    }

}
