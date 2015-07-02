package cn.zalezone.setting;


/**
 * 记录操作后的状态码
 * @author zlk
 *
 */
public class OperationState {
    
    public static final int        LOGIN_SUCCESS     = 1;   //登录成功
    public static final int        LOGIN_FAILED      = 0;   //登录失败
    public static final int        NOACCESS_LOGIN    = 3;   //没有操作权限
    public static final int        NET_ERROR         = 2;   //网络故障
    public static final int        GET_DATA_FINISHED  = 4;   //获取数据完成
    public static final int        GET_LOOKHOUSE_DATA_FINISHED  = 7;   //获取数据完成
    public static final int        INSERT_DATA_SUCCESS = 5;  //插入操作成功
    public static final int        INSERT_DATA_FAILED = 6;     //插入操作失败
    public static final int        GET_USER_INFO_FINISHED =8;//获取租赁人和承租人的用户名和手机号
}
