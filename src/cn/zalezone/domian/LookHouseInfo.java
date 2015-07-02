package cn.zalezone.domian;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.R.string;


/**
 * 自定义的类，保存看房登记表中的一些信息
 * @author zlk
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LookHouseInfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    private String houseCode;//房屋编号
    private String villageName;//小区名
    private String houseAdd;//地址
    private int intentionAppointId;//看房预约ID
    private String intentionAppointCd;//看房预约编号
    private String buildingNo;//楼号
    private String buildingUnit;//楼层单元号
    private String buildingRoom;//楼层室
    private String floorNum;//实际楼层
    private String floorCnt;//总共楼层
    
    
    public String getFloorNum() {
        return floorNum;
    }
    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }
    public String getFloorCnt() {
        return floorCnt;
    }
    public void setFloorCnt(String floorCnt) {
        this.floorCnt = floorCnt;
    }
    public String getBuildingNo() {
        return buildingNo;
    }
    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }
    public String getBuildingUnit() {
        return buildingUnit;
    }
    public void setBuildingUnit(String buildingUnit) {
        this.buildingUnit = buildingUnit;
    }
    public String getBuildingRoom() {
        return buildingRoom;
    }
    public void setBuildingRoom(String buildingRoom) {
        this.buildingRoom = buildingRoom;
    }
    public int getIntentionAppointId() {
        return intentionAppointId;
    }
    public void setIntentionAppointId(int intentionAppointId) {
        this.intentionAppointId = intentionAppointId;
    }
    public String getHouseCode() {
        return houseCode;
    }
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
    public String getVillageName() {
        return villageName;
    }
    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
    public String getHouseAdd() {
        return houseAdd;
    }
    public void setHouseAdd(String houseAdd) {
        this.houseAdd = houseAdd;
    }
    public String getIntentionAppointCd() {
        return intentionAppointCd;
    }
    public void setIntentionAppointCd(String intentionAppointCd) {
        this.intentionAppointCd = intentionAppointCd;
    }
    
    
    
    
    
    
}
