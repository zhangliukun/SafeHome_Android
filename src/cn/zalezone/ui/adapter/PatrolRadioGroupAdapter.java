package cn.zalezone.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.zalezone.domian.LeaseSatisfactionResult;
import cn.zalezone.domian.PatrolRadioGroup;
import cn.zalezone.safehome_android.R;

public class PatrolRadioGroupAdapter extends BaseAdapter {
    private ArrayList<PatrolRadioGroup> list;           // 填充数据的list
    private Context                            context;        // 上下文
    private LayoutInflater                     inflater = null; // 用来导入布局
    private int                                functionState;   // 确定是哪个功能点的按钮，比如审核或者登记等等。

    private static class ViewHolder {
        TextView    codeName;
        RadioButton satification;
        RadioButton nosatification;
        RadioGroup  radioGroup;
    }

    public PatrolRadioGroupAdapter(ArrayList<PatrolRadioGroup> list, Context context)// 构造器
    {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_list_recordvalue, null);
            viewHolder = new ViewHolder();
            viewHolder.codeName = (TextView) convertView.findViewById(R.id.codeName);
            viewHolder.radioGroup = (RadioGroup) convertView.findViewById(R.id.radioGroup1);
            viewHolder.satification = (RadioButton) convertView.findViewById(R.id.satification1);
            viewHolder.nosatification = (RadioButton) convertView.findViewById(R.id.satification2);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final PatrolRadioGroup info = list.get(position);
        viewHolder.radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.satification1:
                        list.get(position).setValue(0);
                        break;
                    case R.id.satification2:
                        list.get(position).setValue(1);
                        break;
                    default:
                        break;
                }
            }
        });

        viewHolder.codeName.setText(info.getRecordName());
        viewHolder.satification.setText(info.getFirstOption());
        viewHolder.nosatification.setText(info.getSecondOption());

        return convertView;
    }
}
