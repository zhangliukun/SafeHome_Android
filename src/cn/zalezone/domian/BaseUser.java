package cn.zalezone.domian;


import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/***
 * 
 * @author jiayy
 * @version 1.0 2015/03/11
 * @param 基础
 *            -用户表 t_base_user
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;// 用户ID

    private String photoPath;//照片路径

    private String loginId;//账号
    
    private String loginPwd;//密码
    
    private String userName;//姓名
    
    private Integer orgId;//组织机构ID
    
    private String userSex;//性别
    
    private Integer userTypeId;//用户类别ID
    
    private String userTypeFlag;//类别区分
    
    private Integer roleId;//角色ID
    
    private String cellTel;//手机/电话
    
    private String userEmail;//电子邮箱
    
    private String otherCon;//其他联系方式
    
    private String postId;//职务ID
    
    private Date userBirthday;//生日
    
    private String idCardNo;//身份证号码
    
    private String domicilePlace;//户籍地
    
    private Integer serviceCenterId;//所属站点
    
    private Date lastLoginTime;//最近登录系统时间
    
    private Integer loginTimes;//累计登录系统次数
    
    private String isLocked;//账号是否冻结
    
    private String occupationId;//职业ID
    
    private String hobbiesId;//兴趣ID
    
    private String regResourceCd;//来源系统代码
    
    private String regResource;//来源系统
    
    private String isDeleted;//
    
    private Date createTime;//
    
    private String creator;//
    
    private String creatorIp;//
    
    private Date modifyTime;//
    
    private String modifier;//
    
    private String modifierIp;//
    
    private String maritalStatus;//

    private String serviceCenterName;
    
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeFlag() {
        return userTypeFlag;
    }

    public void setUserTypeFlag(String userTypeFlag) {
        this.userTypeFlag = userTypeFlag;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCellTel() {
        return cellTel;
    }

    public void setCellTel(String cellTel) {
        this.cellTel = cellTel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOtherCon() {
        return otherCon;
    }

    public void setOtherCon(String otherCon) {
        this.otherCon = otherCon;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getDomicilePlace() {
        return domicilePlace;
    }

    public void setDomicilePlace(String domicilePlace) {
        this.domicilePlace = domicilePlace;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(String occupationId) {
        this.occupationId = occupationId;
    }

    public String getHobbiesId() {
        return hobbiesId;
    }

    public void setHobbiesId(String hobbiesId) {
        this.hobbiesId = hobbiesId;
    }

    public String getRegResourceCd() {
        return regResourceCd;
    }

    public void setRegResourceCd(String regResourceCd) {
        this.regResourceCd = regResourceCd;
    }

    public String getRegResource() {
        return regResource;
    }

    public void setRegResource(String regResource) {
        this.regResource = regResource;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorIp() {
        return creatorIp;
    }

    public void setCreatorIp(String creatorIp) {
        this.creatorIp = creatorIp;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierIp() {
        return modifierIp;
    }
    public void setModifierIp(String modifierIp) {
        this.modifierIp = modifierIp;
    }

    public Integer getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Integer serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    public String getServiceCenterName() {
        return serviceCenterName;
    }

    public void setServiceCenterName(String serviceCenterName) {
        this.serviceCenterName = serviceCenterName;
    }
    
}

