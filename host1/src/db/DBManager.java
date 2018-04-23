package db;

import config.Config;

import java.sql.*;

/**
 * 连接MySQL数据，并进行查询更新操作
 */

public class DBManager {
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;

    String url = Config.URL;
    String user = Config.USER;
    String psd = Config.PSD;
    String driver = Config.DRIVER;



    /**
     * 连接到数据库
     * @return
     */
    public Statement getConnect(){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,psd);
            statement=connection.createStatement();
            System.out.println("开始进行连接....");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    /**
     * 执行查询语句
     * @param sql
     * @param statement
     * @return
     */
    public ResultSet query(String sql,Statement statement){
        try {
            resultSet=statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 执行更新语句
     * @param sql
     * @param statement
     * @return
     */
    public int update(String sql,Statement statement){
        int rows=0;
        try {
            rows=statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

        return rows;
    }

    /**
     * 关闭资源
     */
    private void close() {
        try {
            if (resultSet!=null){
                resultSet.close();
                resultSet=null;
            }
            if (statement!=null){
                statement.close();
                statement=null;
            }
            if (connection!=null){
                connection.close();
                connection=null;
            }
        }catch (Exception e){
            System.out.println("关闭资源时发生异常");
            e.printStackTrace();
        }
    }
}
