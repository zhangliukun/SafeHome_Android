package cn.zalezone.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zalezone.safehome_android.R;

public class MainActivityListAdapter extends BaseAdapter{
	private ArrayList<String> list;           // 填充数据的list
    private Context             context;        // 上下文
    private LayoutInflater      inflater = null; // 用来导入布局

    private static class ViewHolder {
    	ImageView mainIconImageView;
        TextView mainListNameTextView;
    }

    public MainActivityListAdapter(ArrayList<String> list, Context context)// 构造器
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_list_main_activity, null);
            viewHolder = new ViewHolder();
            viewHolder.mainListNameTextView = (TextView) convertView.findViewById(R.id.main_list_item);
            viewHolder.mainIconImageView = (ImageView)convertView.findViewById(R.id.main_list_icon);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        UserInfo userInfo = list.get(position);
//        viewHolder.alpha.setText(userInfo.getAlpha());
//        viewHolder.name.setText(userInfo.getName());
        String  info = list.get(position);
        viewHolder.mainListNameTextView.setText(info);
        switch (position) {
            case 0:
                viewHolder.mainIconImageView.setImageResource(R.drawable.property_verty_icon);
                break;
            case 1:
                viewHolder.mainIconImageView.setImageResource(R.drawable.house_verty_icon);
                break;
            case 2:
                viewHolder.mainIconImageView.setImageResource(R.drawable.house_register_icon);
                break;
            case 3:
                viewHolder.mainIconImageView.setImageResource(R.drawable.inspection_icon);
                break;
            case 4:
                viewHolder.mainIconImageView.setImageResource(R.drawable.search_big_icon);
                break;
            
            default:
                break;
        }
        return convertView;
    }
}
