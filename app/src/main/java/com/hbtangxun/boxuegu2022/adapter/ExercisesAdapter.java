package com.hbtangxun.boxuegu2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.bean.ExercisesBean;

import java.util.List;

/**
 * 习题 适配器
 */
public class ExercisesAdapter extends BaseAdapter {

    private Context mContext;
    private List<ExercisesBean> beanList;

    public ExercisesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 设置数据更新页面
     *
     * @param beanList
     */
    public void setData(List<ExercisesBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    /**
     * 获取Item的数目
     *
     * @return
     */
    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
    }

    /**
     * 根据position得到对应的Item的对象
     *
     * @param position
     * @return
     */
    @Override
    public ExercisesBean getItem(int position) {
        return beanList == null ? null : beanList.get(position);
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
     * 根据position得到对应的Item布局
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //复用convertView
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item, null);
            viewHolder.tv_order = convertView.findViewById(R.id.tv_order);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //获取position对应的Item的数据对象
        final ExercisesBean bean = getItem(position);
        if (bean != null) {
            viewHolder.tv_order.setText(bean.getId() + "");
            viewHolder.tv_title.setText(bean.getTitle());
            viewHolder.tv_content.setText(bean.getContent());
            viewHolder.tv_order.setBackgroundResource(bean.getBackground());
        }
        //每个Item的对应的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null) {
                    return;
                }
                //跳转到对应的习题详情界面
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_order, tv_title, tv_content;
    }

}
