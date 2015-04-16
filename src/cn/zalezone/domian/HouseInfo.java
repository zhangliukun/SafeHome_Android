package cn.zalezone.domian;

/**
 * 实体类
 * @author zlk
 *
 */

public class HouseInfo {
	private String houseNumber;
	private String community;
	private String location;
	
	public HouseInfo(String houseNumber,String community,String location){
		this.houseNumber = houseNumber;
		this.community = community;
		this.location = location;
	}
	
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
