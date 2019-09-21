package com.shujia.crud;

import java.sql.*;
import java.util.Scanner;

public class Select {
    public static void main(String[] args) throws ClassNotFoundException {

        //键盘输入学生名
        System.out.println("请输入需要查询学生的姓名");
        Scanner scanner = new Scanner(System.in);
        String stuname = scanner.next();

        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //DriverManager.registerDriver(new Driver());

        //建立连接

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "123");
            //创建sql执行器
            statement = connection.createStatement();

            //编写sql语句
            String sql = "select * from student where name='"+stuname+"'";

            //执行sql，返回查询结果
            ResultSet resultSet = statement.executeQuery(sql);

            //遍历结果集合
            while (resultSet.next()){
                //mysql下表从1开始
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt("age");
                String gender = resultSet.getString(4);
                String clazz = resultSet.getString(5);
                System.out.println(id+"\t"+name+"\t"+age+"\t"+gender+"\t"+clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
