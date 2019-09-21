package com.shujia.demo;

import com.shujia.util.DBUtils;
import com.shujia.util.LoginUtils;
import com.shujia.util.MD5Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 * 用户注册，
 * 插入数据库之前，需要判断用户名是否已经存在
 *
 * 需要对密码做一个md5加密
 *
 */
public class Register {


    public static void main(String[] args) {

        System.out.println("--------------用户注册-------------");
        Scanner scanner = new Scanner(System.in);
        String username = null;
        boolean flag = true;
        //当输入用户名不存在的时候循环结束
        while (flag){
            System.out.println("请输入用户名：");
            username = scanner.next();
            //判断用户名是否已经存在
            flag = LoginUtils.verifyUsernmae(username);
            if (flag){
                System.out.println("用户名已存在-----");
            }
        }


        System.out.println("请输入密码：");
        //密码使用MD5加密
        String password = MD5Util.getMD5( scanner.next());
        String sql = "insert into user (name,password) values('" + username + "','" + password + "')";
        System.out.println(sql);

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtils.getConnection();
            statement = connection.createStatement();
            int i = statement.executeUpdate(sql);

            //结果大于0说明执行成功
            if (i>0){
                System.out.println("注册成功------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            DBUtils.close(connection,statement);
        }


    }

}
