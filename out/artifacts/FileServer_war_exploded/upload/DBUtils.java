package com.shujia.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBUtils {

    static String driver;
    static String url;
    static String username;
    static String password;

    /**
     *
     * 在静态代码块中初始化配置
     */
    static {
        //创建配置文件读取对象
        Properties properties = new Properties();
        //加载数据
        try {
            properties.load(new FileInputStream("E:\\bigdata\\jdbc-login\\src\\com\\shujia\\util\\DButils.properties"));
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取连接的方法
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        try {
            //加载驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }


    /**
     * 查询数据
     */

    public static ArrayList<ArrayList<String>> select(String sql,int num){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //创建存储结果的集合
        ArrayList<ArrayList<String>> lists = new ArrayList<>();

        try {
            //获取连接
            connection = DBUtils.getConnection();
            //创建执行器
            statement = connection.createStatement();
            //执行sql，返回结果
            resultSet = statement.executeQuery(sql);

            //循环获取数据，保存到集合里面
            while (resultSet.next()){
                ArrayList<String> list = new ArrayList<>();
                for (int i=1;i<=num ;i++){
                    list.add(resultSet.getString(i));
                }
                lists.add(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            DBUtils.close(connection,statement);
        }
        return lists;
    }


    /**
     * 关闭连接
     */

    public static void close(Connection con, Statement stat) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stat != null)
                stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
