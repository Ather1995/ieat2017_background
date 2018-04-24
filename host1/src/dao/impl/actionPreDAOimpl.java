package dao.impl;

import bean.actionPre;
import dao.actionPreDAO;
import db.SQLHelper;


public class actionPreDAOimpl implements actionPreDAO {
    SQLHelper sqlHelper=new SQLHelper();

    @Override
    public boolean addActionPre(actionPre actionPre) {
//        DBManager dbManager=new DBManager();
//        Statement statement=dbManager.getConnect();
        String sql;
        sql="insert into actionpre(userId,foodId,isCollection,clickTime,star) values(" +
                actionPre.getUserId()+","+ actionPre.getFoodId()+","+ actionPre.isCollection()+",\'"+ actionPre.getClickTime()+"\',"+actionPre.getStar()+");";
//        sql="insert into actionPre(userId,foodId,isCollection,clickTime,star) \n" +
//                "values(13,333,true,\"2018-04-18\",2);";
        return (sqlHelper.update(sql)==1);
    }
}

