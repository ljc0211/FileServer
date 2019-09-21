package com.shujia.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class update {
    public static void main(String[] args) {

        //1、加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2、建立连接
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "123");
            //3、创建sql执行器
            statement = con.createStatement();

            String sql ="update user set name='shujia2' where name='shujia'";

            int i = statement.executeUpdate(sql);

            if(i>0){
                System.out.println("更新成功---");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //连接可能没有创建成功，所以需要判断一下是否为null,不然可能会出现空指针异常
                if (con != null) {
                    con.close();
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


    }
}
