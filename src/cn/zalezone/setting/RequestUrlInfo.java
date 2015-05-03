package cn.zalezone.setting;

/**
 * 记录对应的请求
 * 
 * @author zlk
 *
 */
public class RequestUrlInfo {

    public static final String SERVER_ADDRESS                      = "http://192.168.1.126:8080";
    public static final String HTTP_LOGIN                          = SERVER_ADDRESS + "/SafeLease/android/login.do";
    public static final String HTTP_SEARCH_COMMIT_FINISH           = SERVER_ADDRESS + "/SafeLease/android/searchCommitFinish.do";
    public static final String HTTP_SEARCH_PROPERTY_ACCESS         = SERVER_ADDRESS + "/SafeLease/android/searchPropertyAccess.do";
    public static final String HTTP_INSERT_PROPERTY_VERIFY_COMMENT = SERVER_ADDRESS + "/SafeLease/android/insertPropertyVerifyComment.do";
    public static final String HTTP_INSERT_HOUSE_VERIFY_COMMENT    = SERVER_ADDRESS + "/SafeLease/android/insertHouseVerifyComment.do";
    public static final String HTTP_SEATCH_LOOK_HOUSE              = SERVER_ADDRESS + "/SafeLease/android/lookHouseInfo.do";
    public static final String HTTP_GET_SATIFICATION_NAME          = SERVER_ADDRESS + "/SafeLease/android/satificationName.do";
}
