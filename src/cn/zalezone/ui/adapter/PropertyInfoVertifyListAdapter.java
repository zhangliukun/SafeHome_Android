package cn.zalezone.ui.adapter;

import java.util.ArrayList;

import cn.zalezone.domian.LeaseHouseInfo;
import cn.zalezone.safehome_android.R;
import cn.zalezone.setting.FunctionState;
import cn.zalezone.ui.HouseVertifyActivity;
import cn.zalezone.ui.LookHouseStatusActivity;
import cn.zalezone.ui.PropertyVerityActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 产权审核中的房屋信息adapter
 * 
 * @author zlk
 *
 */
public class PropertyInfoVertifyListAdapter extends BaseAdapter {
    private ArrayList<LeaseHouseInfo> list;           // 填充数据的list
    private Context                   context;        // 上下文
    private LayoutInflater            inflater = null; // 用来导入布局
    private int                       functionState;//确定是哪个功能点的按钮，比如审核或者登记等等。

    private static class ViewHolder {
        TextView houseNumberTextView;
        TextView communityNameTextView;
        TextView locationTextView;
        TextView buildingTextView;
        TextView floorTextView;
        Button   verifyButton;
        
    }

    public PropertyInfoVertifyListAdapter(ArrayList<LeaseHouseInfo> list, Context context,int state)// 构造器
    {
        this.context = context;
        this.list = list;
        this.functionState = state;
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_list_house_property, null);
            viewHolder = new ViewHolder();
            viewHolder.houseNumberTextView = (TextView) convertView.findViewById(R.id.house_number);
            viewHolder.communityNameTextView = (TextView) convertView.findViewById(R.id.community_name);
            viewHolder.locationTextView = (TextView) convertView.findViewById(R.id.location);
            viewHolder.buildingTextView =(TextView)convertView.findViewById(R.id.building_info);
            viewHolder.floorTextView = (TextView)convertView.findViewById(R.id.floor_info);
            viewHolder.verifyButton = (Button) convertView.findViewById(R.id.verify);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // UserInfo userInfo = list.get(position);
        // viewHolder.alpha.setText(userInfo.getAlpha());
        // viewHolder.name.setText(userInfo.getName());
        final LeaseHouseInfo info = list.get(position);
        viewHolder.houseNumberTextView.setText(info.getHouseCode());
        viewHolder.communityNameTextView.setText(info.getVillageName());
        viewHolder.locationTextView.setText(info.getHouseAdd());
        viewHolder.buildingTextView.setText(info.getBuildingNo()+"幢"+info.getBuildingUnit()+"单元"+info.getBuildingRoom()+"室");
        viewHolder.floorTextView.setText(info.getFloorNum()+"/"+info.getFloorCnt()+"层");

        switch (functionState) {
            case FunctionState.PROPERTY_VERIFY:
                viewHolder.verifyButton.setText("审核");
                break;
            case FunctionState.HOUSE_VERIFY:
                viewHolder.verifyButton.setText("审核");
                break;
            case FunctionState.LOOK_HOUSE_REGISTER:
                viewHolder.verifyButton.setText("登记");
                break;
            case FunctionState.CHECK_RECORD:
                viewHolder.verifyButton.setText("记录");
                break;
            case FunctionState.HOUSE_SEARCH:
                viewHolder.verifyButton.setText("详细");
                break;

            default:
                break;
        }

        viewHolder.verifyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
                switch (functionState) {
                    case FunctionState.PROPERTY_VERIFY:
                        Intent propertyVerityIntent = new Intent(context, PropertyVerityActivity.class);
                        propertyVerityIntent.putExtra("leaseHouseInfo", info);
                        context.startActivity(propertyVerityIntent);
                        break;
                    case FunctionState.HOUSE_VERIFY:
                        Intent houseVerityIntent = new Intent(context, HouseVertifyActivity.class);
                        houseVerityIntent.putExtra("leaseHouseInfo", info);
                        context.startActivity(houseVerityIntent);
                        break;
//                    case FunctionState.LOOK_HOUSE_REGISTER:
//                        Intent lookHouseIntent = new Intent(context, LookHouseStatusActivity.class);
//                        lookHouseIntent.putExtra("leaseHouseInfo", info);
//                        context.startActivity(lookHouseIntent);
//                        break;
                    case FunctionState.CHECK_RECORD:
                        break;
                    case FunctionState.HOUSE_SEARCH:
                        break;

                    default:
                        break;
                }
                
                
            }
        });
        return convertView;
    }
}
