package com.hbtangxun.boxuegu2022.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbtangxun.boxuegu2022.R;
import com.hbtangxun.boxuegu2022.bean.ExercisesBean;
import com.hbtangxun.boxuegu2022.utils.AnalysisUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 习题详情适配器
 */
public class ExercisesDetailAdapter extends BaseAdapter {

    private Context mContext;
    private List<ExercisesBean> beanList;
    private OnSelectedListener onSelectedListener;

    public ExercisesDetailAdapter(Context mContext, OnSelectedListener onSelectedListener) {
        this.mContext = mContext;
        this.onSelectedListener = onSelectedListener;
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<ExercisesBean> data) {
        this.beanList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return beanList == null ? null : beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //记录用户点击的位置
    private ArrayList<String> selectedPosition = new ArrayList<>();

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercises_detail_list_item, null);
            viewHolder.tv_subject = convertView.findViewById(R.id.tv_subject);
            viewHolder.tv_ex_a = convertView.findViewById(R.id.tv_ex_a);
            viewHolder.tv_ex_b = convertView.findViewById(R.id.tv_ex_b);
            viewHolder.tv_ex_c = convertView.findViewById(R.id.tv_ex_c);
            viewHolder.tv_ex_d = convertView.findViewById(R.id.tv_ex_d);
            viewHolder.iv_ex_a = convertView.findViewById(R.id.iv_ex_a);
            viewHolder.iv_ex_b = convertView.findViewById(R.id.iv_ex_b);
            viewHolder.iv_ex_c = convertView.findViewById(R.id.iv_ex_c);
            viewHolder.iv_ex_d = convertView.findViewById(R.id.iv_ex_d);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //对item的控件进行赋值
        ExercisesBean bean = getItem(position);
        if (bean != null) {
            viewHolder.tv_subject.setText(bean.getSubject());
            viewHolder.tv_ex_a.setText(bean.getA());
            viewHolder.tv_ex_b.setText(bean.getB());
            viewHolder.tv_ex_c.setText(bean.getC());
            viewHolder.tv_ex_d.setText(bean.getD());
        }

        //用户点击A选项的点击事件
        viewHolder.iv_ex_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的position
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add("" + position);
                }
                onSelectedListener.onSelectA(position, viewHolder.iv_ex_a, viewHolder.iv_ex_b, viewHolder.iv_ex_c, viewHolder.iv_ex_d);
            }
        });

        //用户点击B选项的点击事件
        viewHolder.iv_ex_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的position
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add("" + position);
                }
                onSelectedListener.onSelectB(position, viewHolder.iv_ex_a, viewHolder.iv_ex_b, viewHolder.iv_ex_c, viewHolder.iv_ex_d);
            }
        });

        //用户点击C选项的点击事件
        viewHolder.iv_ex_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的position
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add("" + position);
                }
                onSelectedListener.onSelectC(position, viewHolder.iv_ex_a, viewHolder.iv_ex_b, viewHolder.iv_ex_c, viewHolder.iv_ex_d);
            }
        });

        //用户点击D选项的点击事件
        viewHolder.iv_ex_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断 selectedPosition 中是否包含此时点击的position
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add("" + position);
                }
                onSelectedListener.onSelectD(position, viewHolder.iv_ex_a, viewHolder.iv_ex_b, viewHolder.iv_ex_c, viewHolder.iv_ex_d);
            }
        });

        //判断用户选择
        //list.contains  是用来判断该列表是否有元素
        if (!selectedPosition.contains("" + position)) {
            viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
            viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
            viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
            viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
            AnalysisUtils.setABCDEnable(true, viewHolder.iv_ex_a, viewHolder.iv_ex_b, viewHolder.iv_ex_c, viewHolder.iv_ex_d);
        } else {
            AnalysisUtils.setABCDEnable(false, viewHolder.iv_ex_a, viewHolder.iv_ex_b, viewHolder.iv_ex_c, viewHolder.iv_ex_d);
            switch (bean.getSelect()) {
                //用户选择是正确
                case 0:
                    if (bean.getAnswer() == 1) {
                        // A 是正确答案
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        //B 是正确答案
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        //C 是正确答案
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        //D 是正确答案
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                //用户答错了，系统给出正确的答案，并将选择的选项标识
                case 1://A选项错了
                    viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 2) {
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2://B是错的
                    viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 3) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3://C是错的
                    viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 2) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_d);
                    } else if (bean.getAnswer() == 4) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4://D是错的
                    viewHolder.iv_ex_d.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.getAnswer() == 1) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 2) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_right_icon);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_c);
                    } else if (bean.getAnswer() == 3) {
                        viewHolder.iv_ex_a.setImageResource(R.drawable.exercises_a);
                        viewHolder.iv_ex_b.setImageResource(R.drawable.exercises_b);
                        viewHolder.iv_ex_c.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                default:
                    break;
            }
        }
        return convertView;
    }

    private class ViewHolder {
        public ImageView iv_ex_a, iv_ex_b, iv_ex_c, iv_ex_d;
        public TextView tv_subject, tv_ex_a, tv_ex_b, tv_ex_c, tv_ex_d;
    }

    /**
     * 接口：监听A、B、C、D选项，改变图片的状态
     */
    public interface OnSelectedListener {
        void onSelectA(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d);

        void onSelectB(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d);

        void onSelectC(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d);

        void onSelectD(int position, ImageView iv_ex_a, ImageView iv_ex_b, ImageView iv_ex_c, ImageView iv_ex_d);
    }
}
