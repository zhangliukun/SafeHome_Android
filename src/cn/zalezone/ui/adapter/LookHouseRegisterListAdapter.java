package cn.zalezone.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import cn.zalezone.domian.HouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.ui.LookHouseStatusActivity;
import cn.zalezone.ui.PropertyVerityActivity;

public class LookHouseRegisterListAdapter extends BaseAdapter{
	private ArrayList<HouseInfo> list;           // 填充数据的list
    private Context             context;        // 上下文
    private LayoutInflater      inflater = null; // 用来导入布局

    private static class ViewHolder {
        TextView  houseNumberTextView;
        TextView  communityNameTextView;
        TextView locationTextView;
        Button verifyButton;
    }

    public LookHouseRegisterListAdapter(ArrayList<HouseInfo> list, Context context)// 构造器
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
            convertView = inflater.inflate(R.layout.adapter_list_lookhouse, null);
            viewHolder = new ViewHolder();
            viewHolder.houseNumberTextView = (TextView) convertView.findViewById(R.id.house_number);
            viewHolder.communityNameTextView = (TextView) convertView.findViewById(R.id.community_name);
            viewHolder.locationTextView = (TextView) convertView.findViewById(R.id.location);
            viewHolder.verifyButton = (Button)convertView.findViewById(R.id.verify);
            convertView.setTag(viewHolder);
        }
        else {   
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        UserInfo userInfo = list.get(position);
//        viewHolder.alpha.setText(userInfo.getAlpha());
//        viewHolder.name.setText(userInfo.getName());
        final HouseInfo info = list.get(position);
        viewHolder.houseNumberTextView.setText(info.getHouseNumber());
        viewHolder.communityNameTextView.setText(info.getCommunity());
        viewHolder.locationTextView.setText(info.getLocation());
        viewHolder.verifyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent propertyVerityIntent = new Intent(context, LookHouseStatusActivity.class);
				propertyVerityIntent.putExtra("house_number", info.getHouseNumber());
				context.startActivity(propertyVerityIntent);
			}
		});
        return convertView;
    }
}
