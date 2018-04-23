///*
// 测试的假的数据    无用
// */
package db;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPollClass {
    private static DBPollClass dbPoll;
    private ComboPooledDataSource dbSource;

    //静态代码块，一开始我们就执行构造函数加载配置信息
    static {
        dbPoll = new DBPollClass();
    }

    public DBPollClass(){
        //设置配置信息
        try{
            dbSource = new ComboPooledDataSource();
            dbSource.setUser("root");
            dbSource.setPassword("0305hua");
            dbSource.setJdbcUrl("jdbc:mysql://localhost:3306/project_db");
            dbSource.setDriverClass("com.mysql.jdbc.Driver");
            dbSource.setInitialPoolSize(1);
            dbSource.setMinPoolSize(2);
            dbSource.setMaxPoolSize(10);
            dbSource.setMaxStatements(50);
            dbSource.setMaxIdleTime(60);
        }catch (PropertyVetoException e){
            throw new RuntimeException(e);
        }
    }

    //获得连接
    public final static DBPollClass getInstance(){
        return dbPoll;
    }

    public final Connection getConnection(){
        try{
            return dbSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException("无法获取连接", e);
        }
    }

    public static void main(String[] args) throws SQLException {
        for(int i = 0; i < 2000; i++) {
            long begintime = System.currentTimeMillis();
            Connection con = null;
            try {
                //取得空闲连接
                con = dbPoll.getInstance().getConnection();
                //执行sql语句并返回查询结果
                //ResultSet rs = con.createStatement().executeQuery("SELECT * from UserInfo");
                //使用PreparedStatement而不使用Statement
                PreparedStatement pst = con.prepareStatement("SELECT * from actionpre");
                ResultSet rs = pst.executeQuery();
                //输出查询结果
                while (rs.next()) {
                    System.out.println(rs.getObject(1) + " " + rs.getObject(2) + " " + rs.getObject(3) + " " + rs.getObject(4));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    //归还空闲连接
                    con.close();
                }
            }
            long endtime = System.currentTimeMillis();
            System.out.println((i+1) + " time is:" + (endtime-begintime));
        }
    }
}
