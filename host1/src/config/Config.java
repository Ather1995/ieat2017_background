package config;

public class Config {
    /*
        user 表
    */
    public static final String TEL="tel";
    public static final String WEICHAT="weichat";
    public static final String QQ="qq";


    public static final String ACCOUNT="account";
    public static final String PASSWORD="password";

    public static final String REQUEST_TYPE="request_type";
    public static final String REGISTER="register";
    public static final String LOGIN="login";
    public static final String FOOD_CLICK="food_click";


    public static final String UPDATE_ACTION="update_action";

    /*
     * actionPre 记录用户行为的表
     * */
    public static final String USERID="userId";
    public static final String FOODID="foodId";
    public static final String ISCOLLECTION="isCollection";
    public static final String CLICKTIME="clickTime";
    public static final String STAR="star";

    /*
     * 连接数据库信息
     * */
    public static final String URL="jdbc:mysql://localhost:3306/project_db_sql?useSSL=true";
    public static final String USER="root";
    public static final String PSD="0305hua";
    public static final String DRIVER="com.mysql.jdbc.Driver";

}
