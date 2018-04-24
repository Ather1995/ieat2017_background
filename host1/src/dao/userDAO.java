package dao;

import bean.user;

import java.sql.SQLException;

public interface userDAO {
    public boolean addUser(user user, String type);//根据用户选择登录方式的不同来添加用户
    public int update(user user, String sql);//更新用户信息
    public String getAcess(String key, String type) throws SQLException;//由输入的电话号码或者QQ号来获取对应的密码

}
