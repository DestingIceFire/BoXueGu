package com.hbtangxun.boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hbtangxun.boxuegu.R;
import com.hbtangxun.boxuegu.utils.ToolUtils;
import com.hbtangxun.boxuegu.bean.ExercisesBean;

import java.util.List;

/**
 * 习题适配器
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
     * 获取Item的总数
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        //复用convertView
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exersises_list_item, null);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            viewHolder.tv_order = convertView.findViewById(R.id.tv_order);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //获取position对用Item的数据对象
        final ExercisesBean bean = getItem(position);
        if (bean != null) {
            viewHolder.tv_order.setText(position + 1 + "");
            viewHolder.tv_title.setText(bean.getTitle());
            viewHolder.tv_content.setText(bean.getContent());
            viewHolder.tv_order.setBackgroundResource(bean.getBackground());
        }
        //每个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到对应的习题界面
                if (bean == null) {
                    return;
                }
                ToolUtils.showToastShort(mContext,
                        "这是第" + viewHolder.tv_order.getText().toString() + "题");
            }
        });

        return convertView;
    }

    class ViewHolder {
        public TextView tv_title, tv_content, tv_order;
    }

}
