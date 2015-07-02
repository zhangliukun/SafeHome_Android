package cn.zalezone.domian;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * 巡查记录中的出租人饿求租人的信息
 * @author zlk
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeaseTenantUserInfo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    //出租人信息
    private String leaseUserName;
    private String leaseUserPhone;
    //承租人信息
    private String tenantUserName;
    private String tenantUserPhone;
    //承租开始时间
    private Date startDate;
    //承租结束时间
    private Date endDate;
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getLeaseUserName() {
        return leaseUserName;
    }
    public void setLeaseUserName(String leaseUserName) {
        this.leaseUserName = leaseUserName;
    }
    public String getLeaseUserPhone() {
        return leaseUserPhone;
    }
    public void setLeaseUserPhone(String leaseUserPhone) {
        this.leaseUserPhone = leaseUserPhone;
    }
    public String getTenantUserName() {
        return tenantUserName;
    }
    public void setTenantUserName(String tenantUserName) {
        this.tenantUserName = tenantUserName;
    }
    public String getTenantUserPhone() {
        return tenantUserPhone;
    }
    public void setTenantUserPhone(String tenantUserPhone) {
        this.tenantUserPhone = tenantUserPhone;
    }
    
    

}
