package cn.zalezone.domian;

import java.io.Serializable;
import java.util.Date;

/**
*
* @author zhouc
* 2015-03-18
*/
public class LeaseHouseInfo implements Serializable{
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    private Integer houseInfoId;

	public Integer getHouseInfoId(){
		return houseInfoId;
	}

	public void setHouseInfoId(Integer houseInfoId){
		this.houseInfoId=houseInfoId;
	}

	private String houseCode;

	public String getHouseCode(){
		return houseCode;
	}

	public void setHouseCode(String houseCode){
		this.houseCode=houseCode;
	}
	
	private Integer propertyCheckUserId;
	
	private Date propertyCheckTime;
	
	private String propertyCheckResult;
	
	private String propertyCheckOpinion;
	
	public Integer getPropertyCheckUserId() {
		return propertyCheckUserId;
	}

	public void setPropertyCheckUserId(Integer propertyCheckUserId) {
		this.propertyCheckUserId = propertyCheckUserId;
	}

	public Date getPropertyCheckTime() {
		return propertyCheckTime;
	}

	public void setPropertyCheckTime(Date propertyCheckTime) {
		this.propertyCheckTime = propertyCheckTime;
	}

	public String getPropertyCheckResult() {
		return propertyCheckResult;
	}

	public void setPropertyCheckResult(String propertyCheckResult) {
		this.propertyCheckResult = propertyCheckResult;
	}

	public String getPropertyCheckOpinion() {
		return propertyCheckOpinion;
	}

	public void setPropertyCheckOpinion(String propertyCheckOpinion) {
		this.propertyCheckOpinion = propertyCheckOpinion;
	}

	private String propertyOwner;

	public String getPropertyOwner(){
		return propertyOwner;
	}

	public void setPropertyOwner(String propertyOwner){
		this.propertyOwner=propertyOwner;
	}

	private String poidCardNo;

	public String getPoidCardNo(){
		return poidCardNo;
	}

	public void setPoidCardNo(String poidCardNo){
		this.poidCardNo=poidCardNo;
	}

	private String pophone;

	public String getPophone(){
		return pophone;
	}

	public void setPophone(String pophone){
		this.pophone=pophone;
	}

	private Integer leaserUserId;

	public Integer getLeaserUserId(){
		return leaserUserId;
	}

	public void setLeaserUserId(Integer leaserUserId){
		this.leaserUserId=leaserUserId;
	}

	private Integer tenantUserId;

	public Integer getTenantUserId(){
		return tenantUserId;
	}

	public void setTenantUserId(Integer tenantUserId){
		this.tenantUserId=tenantUserId;
	}

	private String tenantWay;

	public String getTenantWay(){
		return tenantWay;
	}

	public void setTenantWay(String tenantWay){
		this.tenantWay=tenantWay;
	}

	private String houseAdd;

	public String getHouseAdd(){
		return houseAdd;
	}

	public void setHouseAdd(String houseAdd){
		this.houseAdd=houseAdd;
	}

	private Integer serviceCenterId;

	public Integer getServiceCenterId(){
		return serviceCenterId;
	}

	public void setServiceCenterId(Integer serviceCenterId){
		this.serviceCenterId=serviceCenterId;
	}

	private String houseType;

	public String getHouseType(){
		return houseType;
	}

	public void setHouseType(String houseType){
		this.houseType=houseType;
	}

	private String usingProperties;

	public String getUsingProperties(){
		return usingProperties;
	}

	public void setUsingProperties(String usingProperties){
		this.usingProperties=usingProperties;
	}

	private String leaseType;

	public String getLeaseType(){
		return leaseType;
	}

	public void setLeaseType(String leaseType){
		this.leaseType=leaseType;
	}

	private String buildingType;

	public String getBuildingType(){
		return buildingType;
	}

	public void setBuildingType(String buildingType){
		this.buildingType=buildingType;
	}

	private Double proportionM3;

	public Double getProportionM3(){
		return proportionM3;
	}

	public void setProportionM3(Double proportionM3){
		this.proportionM3=proportionM3;
	}

	private String decoration;

	public String getDecoration(){
		return decoration;
	}

	public void setDecoration(String decoration){
		this.decoration=decoration;
	}

	private Integer rentalNum;

	public Integer getRentalNum(){
		return rentalNum;
	}

	public void setRentalNum(Integer rentalNum){
		this.rentalNum=rentalNum;
	}

	private Double rentIntention;

	public Double getRentIntention(){
		return rentIntention;
	}

	public void setRentIntention(Double rentIntention){
		this.rentIntention=rentIntention;
	}

	private String paymentWay;

	public String getPaymentWay(){
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay){
		this.paymentWay=paymentWay;
	}

	private String villageName;

	public String getVillageName(){
		return villageName;
	}

	public void setVillageName(String villageName){
		this.villageName=villageName;
	}

	private String orientation;

	public String getOrientation(){
		return orientation;
	}

	public void setOrientation(String orientation){
		this.orientation=orientation;
	}

	private Integer floorNum;

	public Integer getFloorNum(){
		return floorNum;
	}

	public void setFloorNum(Integer floorNum){
		this.floorNum=floorNum;
	}

	private Integer floorCnt;

	public Integer getFloorCnt(){
		return floorCnt;
	}

	public void setFloorCnt(Integer floorCnt){
		this.floorCnt=floorCnt;
	}

	private String buildingNo;

	public String getBuildingNo(){
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo){
		this.buildingNo=buildingNo;
	}

	private String buildingUnit;

	public String getBuildingUnit(){
		return buildingUnit;
	}

	public void setBuildingUnit(String buildingUnit){
		this.buildingUnit=buildingUnit;
	}

	private String buildingRoom;

	public String getBuildingRoom(){
		return buildingRoom;
	}

	public void setBuildingRoom(String buildingRoom){
		this.buildingRoom=buildingRoom;
	}

	private String haveBed;

	public String getHaveBed(){
		return haveBed;
	}

	public void setHaveBed(String haveBed){
		this.haveBed=haveBed;
	}

	private String haveKitchenBath;

	public String getHaveKitchenBath(){
		return haveKitchenBath;
	}

	public void setHaveKitchenBath(String haveKitchenBath){
		this.haveKitchenBath=haveKitchenBath;
	}

	private String haveNetwork;

	public String getHaveNetwork(){
		return haveNetwork;
	}

	public void setHaveNetwork(String haveNetwork){
		this.haveNetwork=haveNetwork;
	}

	private String haveTv;

	public String getHaveTv(){
		return haveTv;
	}

	public void setHaveTv(String haveTv){
		this.haveTv=haveTv;
	}

	private String haveRefrigerator;

	public String getHaveRefrigerator(){
		return haveRefrigerator;
	}

	public void setHaveRefrigerator(String haveRefrigerator){
		this.haveRefrigerator=haveRefrigerator;
	}

	private String haveAc;

	public String getHaveAc(){
		return haveAc;
	}

	public void setHaveAc(String haveAc){
		this.haveAc=haveAc;
	}

	private String haveWashingMachine;

	public String getHaveWashingMachine(){
		return haveWashingMachine;
	}

	public void setHaveWashingMachine(String haveWashingMachine){
		this.haveWashingMachine=haveWashingMachine;
	}

	private String haveWaterHeater;

	public String getHaveWaterHeater(){
		return haveWaterHeater;
	}

	public void setHaveWaterHeater(String haveWaterHeater){
		this.haveWaterHeater=haveWaterHeater;
	}

	private String cqrdzwtsPath;

	public String getCqrdzwtsPath(){
		return cqrdzwtsPath;
	}

	public void setCqrdzwtsPath(String cqrdzwtsPath){
		this.cqrdzwtsPath=cqrdzwtsPath;
	}

	private Integer checkUserId;

	public Integer getCheckUserId(){
		return checkUserId;
	}

	public void setCheckUserId(Integer checkUserId){
		this.checkUserId=checkUserId;
	}

	private Date checkTime;

	public Date getCheckTime(){
		return checkTime;
	}

	public void setCheckTime(Date checkTime){
		this.checkTime=checkTime;
	}

	private String checkResult;

	public String getCheckResult(){
		return checkResult;
	}

	public void setCheckResult(String checkResult){
		this.checkResult=checkResult;
	}

	private String checkOpinion;

	public String getCheckOpinion(){
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion){
		this.checkOpinion=checkOpinion;
	}

	private Integer publishUserId;

	public Integer getPublishUserId(){
		return publishUserId;
	}

	public void setPublishUserId(Integer publishUserId){
		this.publishUserId=publishUserId;
	}

	private Date publishTime;

	public Date getPublishTime(){
		return publishTime;
	}

	public void setPublishTime(Date publishTime){
		this.publishTime=publishTime;
	}

	private String processStatus;

	public String getProcessStatus(){
		return processStatus;
	}

	public void setProcessStatus(String processStatus){
		this.processStatus=processStatus;
	}

	private String leaseStatus;

	public String getLeaseStatus(){
		return leaseStatus;
	}

	public void setLeaseStatus(String leaseStatus){
		this.leaseStatus=leaseStatus;
	}

	private Date startDate;

	public Date getStartDate(){
		return startDate;
	}

	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}

	private Date endDate;

	public Date getEndDate(){
		return endDate;
	}

	public void setEndDate(Date endDate){
		this.endDate=endDate;
	}

	private String memo;

	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo=memo;
	}

	private String isDeleted;

	public String getIsDeleted(){
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted){
		this.isDeleted=isDeleted;
	}

	private Date createTime;

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	private String creator;

	public String getCreator(){
		return creator;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	private String creatorIp;

	public String getCreatorIp(){
		return creatorIp;
	}

	public void setCreatorIp(String creatorIp){
		this.creatorIp=creatorIp;
	}

	private Date modifyTime;

	public Date getModifyTime(){
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}

	private String modifier;

	public String getModifier(){
		return modifier;
	}

	public void setModifier(String modifier){
		this.modifier=modifier;
	}

	private String modifierIp;

	public String getModifierIp(){
		return modifierIp;
	}

	public void setModifierIp(String modifierIp){
		this.modifierIp=modifierIp;
	}

	private String houseImagePath1;
	
	private String houseImagePath2;
	
	private String houseImagePath3;

	public String getHouseImagePath1() {
		return houseImagePath1;
	}

	public void setHouseImagePath1(String houseImagePath1) {
		this.houseImagePath1 = houseImagePath1;
	}

	public String getHouseImagePath2() {
		return houseImagePath2;
	}

	public void setHouseImagePath2(String houseImagePath2) {
		this.houseImagePath2 = houseImagePath2;
	}

	public String getHouseImagePath3() {
		return houseImagePath3;
	}

	public void setHouseImagePath3(String houseImagePath3) {
		this.houseImagePath3 = houseImagePath3;
	}
	
	private String propertyNo;

	public String getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}

}