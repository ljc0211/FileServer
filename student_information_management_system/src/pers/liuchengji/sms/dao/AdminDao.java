/**
 * GitHub address: https://yubuntu0109.github.io/
 *
 * @Title: AdminDao.java
 * @Package pers.liuchengji.sms.dao
 * @Description: 数据库操作
 * @author: Huang Yuhui
 * @date: May 10, 2019 5:44:31 PM
 * @version 1.0
 */
package pers.liuchengji.sms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import pers.liuchengji.sms.model.AdminInfo;

/**
 * @ClassName: AdminDao
 * @Description: 管理员的数据库的操作
 * @author: liuchengji
 * @date: May 10, 2019 5:44:31 PM
 */
public class AdminDao extends BasicDao {

    /**
     * @Title: getUserInfo
     * @Description: 获取用户信息
     * @param: name
     * @param: password
     * @return: AdminInfo
     */
    public AdminInfo getUserInfo(String name, String password) {
        String sql = "select id,name,password,status from user_Admin where name='" + name + "'  and password='"
                + password + "'";
        try (ResultSet resultSet = query(sql)) {
            if (resultSet.next()) {
                AdminInfo adminInfo = new AdminInfo();
                adminInfo.setId(resultSet.getString("id"));
                adminInfo.setStatus(resultSet.getInt("status"));
                adminInfo.setName(resultSet.getString("name"));
                adminInfo.setPassword(resultSet.getString("password"));
                return adminInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setUserInfo(String name, String password) throws SQLException {

        String sql = "INSERT INTO user_Admin(status, name, password) VALUES(" + 1 + ", '" + name + "', '" + password + "');";

        //	建立连接
        Connection con = null;
        Statement statement = null;

        try {
            con = BasicDao.getConnection();

            //	创建sql执行器
            statement = con.createStatement();

            //4、执行sql语句,返回影响的行数
            int i = statement.executeUpdate(sql);

            if (i > 0) {
                System.out.println("插入成功----");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            //if后面只有一行代码的时候可以省略{}
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: modifyPassword
     * @Description: 修改密码
     * @param: adminInfo
     * @param: newPassword
     * @return: boolean
     */
    public boolean modifyPassword(AdminInfo adminInfo, String newPassword) {
        String sql = "update user_admin set password = '" + newPassword + "' where id = '" + adminInfo.getId() + "'";
        return update(sql);
    }

}
