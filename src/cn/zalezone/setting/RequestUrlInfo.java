package cn.zalezone.setting;

/**
 * 记录对应的请求
 * 
 * @author zlk
 *
 */
public class RequestUrlInfo {

    // 服务器地址
    //public static final String SERVER_ADDRESS                      = "http://10.10.64.254:8080/SafeLease";
    public static final String SERVER_ADDRESS                      = "http://192.168.1.102:8080/SafeLease";
	//public static final String SERVER_ADDRESS                      = "http://192.168.0.72:8080/SafeLease";
	
	//public static final String SERVER_ADDRESS                       = "http://http://paw.sqhmfw.com";
    // 登录
    public static final String HTTP_LOGIN                          = SERVER_ADDRESS + "/android/login.do";
    // 产权审核查询
    public static final String HTTP_SEARCH_COMMIT_FINISH           = SERVER_ADDRESS + "/android/searchCommitFinish.do";
    // 房屋审核查询
    public static final String HTTP_SEARCH_PROPERTY_ACCESS         = SERVER_ADDRESS + "/android/searchPropertyAccess.do";
    // 产权审核意见插入
    public static final String HTTP_INSERT_PROPERTY_VERIFY_COMMENT = SERVER_ADDRESS + "/android/insertPropertyVerifyComment.do";
    // 房屋审核意见插入
    public static final String HTTP_INSERT_HOUSE_VERIFY_COMMENT    = SERVER_ADDRESS + "/android/insertHouseVerifyComment.do";
    // 看房登记查询
    public static final String HTTP_SEATCH_LOOK_HOUSE              = SERVER_ADDRESS + "/android/lookHouseInfo.do";
    // 获得满意度代码列表
    public static final String HTTP_GET_SATIFICATION_NAME          = SERVER_ADDRESS + "/android/satificationName.do";
    // 看房登记成交意见与否插入
    public static final String HTTP_LOOKHOUSE_DEAL                 = SERVER_ADDRESS + "/android/insertLookHouseComment.do";
    // 巡查记录信息查询
    public static final String HTTP_SEARCH_PATROL_RECORD           = SERVER_ADDRESS + "/android/searchPatrolRecord.do";
    // 获取租赁人和承租人的用户名手机号
    public static final String HTTP_GET_USERINFO                   = SERVER_ADDRESS + "/android/getUserInfo.do";
    // 插入巡查记录
    public static final String HTTP_INSERT_PARTOL_RECORD           = SERVER_ADDRESS + "/android/insertPatrolRecord.do";
    // 房屋查询
    public static final String HTTP_SEARCH_HOUSE                   = SERVER_ADDRESS + "/android/searchHouse.do";
}
