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
import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.domian.LookHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.ui.LookHouseStatusActivity;
import cn.zalezone.ui.PropertyVerityActivity;
import cn.zalezone.ui.SearchHouseStatusActivity;

public class SearchHouseRegisterListAdapter extends BaseAdapter {
	private ArrayList<LeaseHouseInfo> list; // 填充数据的list
	private Context context; // 上下文
	private LayoutInflater inflater = null; // 用来导入布局

	private static class ViewHolder {
		TextView houseNumberTextView;
		TextView communityNameTextView;
		TextView locationTextView;
		TextView buildingTextView;
		TextView floorTextView;
		Button verifyButton;
	}

	public SearchHouseRegisterListAdapter(ArrayList<LeaseHouseInfo> list,
			Context context)// 构造器
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
			convertView = inflater.inflate(R.layout.adapter_list_house_property,
					null);
			viewHolder = new ViewHolder();
			viewHolder.houseNumberTextView = (TextView) convertView
					.findViewById(R.id.house_number);
			viewHolder.communityNameTextView = (TextView) convertView
					.findViewById(R.id.community_name);
			viewHolder.locationTextView = (TextView) convertView
					.findViewById(R.id.location);
			viewHolder.buildingTextView = (TextView) convertView
					.findViewById(R.id.building_info);
			viewHolder.floorTextView = (TextView) convertView
					.findViewById(R.id.floor_info);
			viewHolder.verifyButton = (Button) convertView
					.findViewById(R.id.verify);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final LeaseHouseInfo info = list.get(position);

		viewHolder.houseNumberTextView.setText(info.getHouseCode());
		viewHolder.communityNameTextView.setText(info.getVillageName());
		viewHolder.locationTextView.setText(info.getHouseAdd());
		viewHolder.verifyButton.setText("详细");
		 viewHolder.buildingTextView.setText(info.getBuildingNo()+"幢"+info.getBuildingUnit()+"单元"+info.getBuildingRoom()+"室");
	        viewHolder.floorTextView.setText(info.getFloorNum()+"/"+info.getFloorCnt()+"层");
		viewHolder.verifyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent searchHouseIntent = new Intent(context,
						SearchHouseStatusActivity.class);
				searchHouseIntent.putExtra("searchHouseInfo", info);
				context.startActivity(searchHouseIntent);
			}
		});
		return convertView;
	}
}
