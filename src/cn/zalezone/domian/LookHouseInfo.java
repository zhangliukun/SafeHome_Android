package cn.zalezone.domian;

import java.io.Serializable;


/**
 * 自定义的类，保存看房登记表中的一些信息
 * @author zlk
 *
 */
public class LookHouseInfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    private String houseCode;//房屋编号
    private String villageName;//小区名
    private String houseAdd;//地址
    private String intentionAppointCd;//看房预约编号
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
