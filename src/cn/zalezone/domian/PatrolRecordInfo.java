package cn.zalezone.domian;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 巡查记录所需信息
 * @author zlk
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatrolRecordInfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String houseCode;//房屋编号
    private String villageName;//小区名
    private String houseAdd;//地址
    private String buildingNo;//楼号
    private String buildingUnit;//楼层单元号
    private String buildingRoom;//楼层室
    private int floorNum;//实际楼层
    private int floorCnt;//总共楼层
    private int dealInfoId;//租赁信息ID
    private int houseInfoId;//待租房ID
    private int leaseUserId;//出租人用户id
    private int tenantUserId;//求租人用户id
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
    public int getFloorNum() {
        return floorNum;
    }
    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }
    public int getFloorCnt() {
        return floorCnt;
    }
    public void setFloorCnt(int floorCnt) {
        this.floorCnt = floorCnt;
    }
    public int getDealInfoId() {
        return dealInfoId;
    }
    public void setDealInfoId(int dealInfoId) {
        this.dealInfoId = dealInfoId;
    }
    public int getHouseInfoId() {
        return houseInfoId;
    }
    public void setHouseInfoId(int houseInfoId) {
        this.houseInfoId = houseInfoId;
    }
    public int getLeaseUserId() {
        return leaseUserId;
    }
    public void setLeaseUserId(int leaseUserId) {
        this.leaseUserId = leaseUserId;
    }
    public int getTenantUserId() {
        return tenantUserId;
    }
    public void setTenantUserId(int tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
    

}
