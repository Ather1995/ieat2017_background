package dao.impl;

import dao.updateFinalScoreDAO;
import db.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class updateFinalScoreDAOimpl implements updateFinalScoreDAO {
    SQLHelper sqlHelper=new SQLHelper();
    ResultSet rs,rs1;
    @Override
    public void updateFinalScore() throws SQLException {
        String sql="SELECT * FROM actionpre;";
        rs=sqlHelper.queryRs(sql);
        System.out.println("updateFinalScoreDAOimpl");
        int score=0;
        String timeStamp;
        String userId;
        String foodId;
        int i=0;
        while (rs.next()){
            System.out.println("*************"+i++);
            if(rs.getObject("userId")==null||rs.getObject("foodId")==null||rs.getObject("isCollection")==null||rs.getObject("clickTime")==null||rs.getObject("star")==null){
                continue;
            }else {
                userId=  rs.getString("userId");
                foodId=rs.getString("foodId");
                int isCollection=rs.getInt("isCollection");
                int star=rs.getInt("star");
                timeStamp=rs.getString("timeStamp");

//                sql1查在finalScore表中是否存在用户userId和foodId
                String sql1="SELECT * FROM finalScore " +
                        "where userId="+userId+" and foodId="+foodId+";";
                SQLHelper sqlHelper2=new SQLHelper();
                if(sqlHelper2.queryCount(sql1)==0){
                    //若在finalScore表中无用户A对物品a的记录室，插入
                    String sql3="insert into finalScore (userId,foodId,score,timeStamp)\n" +
                            "value ("+userId+","+foodId+","+calCulate(0,isCollection,star)+","+timeStamp+");";
                    sqlHelper2.update(sql3);
                }else {
                    SQLHelper sqlHelper1=new SQLHelper();
                    rs1=sqlHelper1.queryRs(sql1);
                    int oldScore=0;
                    boolean isNext=false;
                    while (rs1.next()){
                        isNext=true;
                        oldScore=rs1.getInt("score");
                    }
                    String sql4="UPDATE finalScore SET score="+calCulate(oldScore,isCollection,star)+"\n" +
                            "WHERE userId = "+userId+" and foodId="+foodId+";";
                    rs1.close();
                    sqlHelper1.close();

                    if(isNext){
                        sqlHelper2.update(sql4);
                    }
                }

                sqlHelper2.close();

            }

        }

        rs.close();
        sqlHelper.close();


    }
    //计算得分，若收藏，则加10分，若点击加1
    public int calCulate(int old,int isCollected,int star){
        if(isCollected==1){
            return 11+star+old;
        }else {
            return 1+star+old;
        }
    }
}
