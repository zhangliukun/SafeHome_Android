package cn.zalezone.domian;

import java.util.Date;

/**
*
* @author zhouc
* 2015-03-18
*/

public class LeaseSatisfactionResult {
	private Integer satisfactionResultId;

	public Integer getSatisfactionResultId(){
		return satisfactionResultId;
	}

	public void setSatisfactionResultId(Integer satisfactionResultId){
		this.satisfactionResultId=satisfactionResultId;
	}

	private Integer intentionAppointId;

	public Integer getIntentionAppointId(){
		return intentionAppointId;
	}

	public void setIntentionAppointId(Integer intentionAppointId){
		this.intentionAppointId=intentionAppointId;
	}

	private String intentionAppointCd;

	public String getIntentionAppointCd(){
		return intentionAppointCd;
	}

	public void setIntentionAppointCd(String intentionAppointCd){
		this.intentionAppointCd=intentionAppointCd;
	}

	private String satisfactionResultCd;

	public String getSatisfactionResultCd(){
		return satisfactionResultCd;
	}

	public void setSatisfactionResultCd(String satisfactionResultCd){
		this.satisfactionResultCd=satisfactionResultCd;
	}

	private String satisfactionResultNm;

	public String getSatisfactionResultNm(){
		return satisfactionResultNm;
	}

	public void setSatisfactionResultNm(String satisfactionResultNm){
		this.satisfactionResultNm=satisfactionResultNm;
	}

	private String satisfactionFlag;

	public String getSatisfactionFlag(){
		return satisfactionFlag;
	}

	public void setSatisfactionFlag(String satisfactionFlag){
		this.satisfactionFlag=satisfactionFlag;
	}

}