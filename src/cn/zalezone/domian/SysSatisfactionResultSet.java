package cn.zalezone.domian;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/***
 * 
 * @author 
 * @version 1.0 
 * @param 系统-系统参数  t_sys_satisfaction_result_set
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SysSatisfactionResultSet {
	private Integer satisfactionResultSetId;//系统参数ID
	
	private String satisfactionResultCd;//参数代码
	
	private String satisfactionResultNm;//参数值

	public Integer getSatisfactionResultSetId() {
		return satisfactionResultSetId;
	}

	public void setSatisfactionResultSetId(Integer satisfactionResultSetId) {
		this.satisfactionResultSetId = satisfactionResultSetId;
	}

	public String getSatisfactionResultCd() {
		return satisfactionResultCd;
	}

	public void setSatisfactionResultCd(String satisfactionResultCd) {
		this.satisfactionResultCd = satisfactionResultCd;
	}

	public String getSatisfactionResultNm() {
		return satisfactionResultNm;
	}

	public void setSatisfactionResultNm(String satisfactionResultNm) {
		this.satisfactionResultNm = satisfactionResultNm;
	}


	
}
