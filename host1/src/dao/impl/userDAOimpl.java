package dao.impl;

import bean.user;
import config.Config;
import dao.userDAO;
//import db.DBManager;
import db.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class userDAOimpl implements userDAO {
    SQLHelper sqlHelper=new SQLHelper();
    @Override

    public boolean addUser(user user, String type) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
        String sql;
        switch (type) {
            case Config.TEL://通过电话号码注册
                sql = "insert into oauth(oauthId,oauthType,oauthAccessToken) values(\'" +
                        user.getUserTel()+"\',\'"+ Config.TEL+"\',\'"+user.oauthAccessToken+"\');";
                return (sqlHelper.update(sql)==1);
            case Config.WEICHAT://通过微信注册
                sql="insert into oauth(oauthId,oauthType,oauthAccessToken) values(\'" +
                        user.getUserWeiChat()+"\',\'"+ Config.WEICHAT+"\',\'"+user.oauthAccessToken+"\');";
                return (sqlHelper.update(sql)==1);
            case Config.QQ://通过QQ注册
                sql = "insert into oauth(oauthId,oauthType,oauthAccessToken) values(\'" +
                        user.getUserQQ()+"\',\'"+ Config.QQ+"\',\'"+user.oauthAccessToken+"\');";
                return (sqlHelper.update(sql)==1);
        }

        return false;
    }

    @Override
    public int update(user user, String sql) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
        return sqlHelper.update(sql);
    }


    /**
     * 返回对应账号的密码
     * @param account
     * @param type
     * @return
     * @throws SQLException
     */
    @Override
    public String getAcess(String account, String type) throws SQLException {
//        DBManager dbManager=new DBManager();
//        Statement statement=dbManager.getConnect();
        String sql="select oauthAccessToken from oauth where oauthId=\'" +
                account+"\' and oauthType=\'"+type+"\';";
        ResultSet resultSet=sqlHelper.queryRs(sql);
        String oauthAccessToken=null;
        if (resultSet == null){
            return oauthAccessToken;
        }
        while (resultSet.next()){
            oauthAccessToken=resultSet.getString("oauthAccessToken");
        }
        return oauthAccessToken;
    }

//    @Override
//    public int getUserId() {
//
//        return 0;
//    }
//
//    @Override
//    public boolean checkTel_and_QQ(String Tel, String QQ) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
//        String sql = "insert into user(userTel,userQQ) values (\'" + Tel + "\',\'" + QQ + "\');";
//        return (dbManager.update(sql, statement) == 1);
//    }
//
//    @Override
//    public String getPsd(String usertName) {
//        DBManager dbManager = new DBManager();
//        Statement statement = dbManager.getConnect();
//        String sql = "select userPassword from user where user";
//        return null;
//    }

}
