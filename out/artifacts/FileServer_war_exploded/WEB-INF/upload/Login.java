package com.shujia.demo;

import com.shujia.util.DBUtils;
import com.shujia.util.LoginUtils;
import com.shujia.util.MD5Util;

import java.sql.*;
import java.util.Scanner;

/**
 * 登录
 * 1、判断用户名是否存在，
 * 2、判断密码是否正确
 */
public class Login {

    public static void main(String[] args) throws SQLException {


        //判断用户名是否存在，
        System.out.println("-------------用户登录----------------");
        Scanner scanner = new Scanner(System.in);
        String username = null;
        boolean flag = true;
        while (flag) {
            System.out.println("请输入用户名：");
            username = scanner.nextLine();
            //验证用户名是否存在
            flag = !LoginUtils.verifyUsernmae(username);
            if (flag) {
                System.out.println("用户名不存在-----");
            }
        }
        //2、判断密码是否正确
        boolean loginFlag = true;
        while (loginFlag){
            System.out.println("请输入密码：");
            //登录的时候也需要对密码加密
            String password = MD5Util.getMD5(scanner.nextLine());
            String sql = "select * from user where password=? and name=?";
            System.out.println(sql);
            //获取连接
            Connection connection = null;
            Statement statement = null;
            try {
                connection = DBUtils.getConnection();
                //获取sql执行器
                //statement = connection.createStatement();
                //ResultSet resultSet = statement.executeQuery(sql);

                //创建预编译sql执行器
                PreparedStatement statement1 = connection.prepareStatement(sql);

                //给参数赋值
                statement1.setObject(1,password);
                statement1.setObject(2,username);

                //执行sql
                ResultSet resultSet = statement1.executeQuery();

                //判断是否查询到数据
                loginFlag = ! resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtils.close(connection,statement);
            }

            if (loginFlag){
                System.out.println("密码不正确------");
            }else {
                System.out.println("登录成功----------");
            }
        }


        while (true){
            //修改密码
            //退出登录
            System.out.println("请选择，1：修改密码，2：退出登录");
            int i = scanner.nextInt();

            if(i==2){
                break;
            }else  if(i==1){

                boolean flag1 = true;
                String newPassword =null;
                while (flag1){
                    System.out.println("请输入密码：");
                    newPassword = scanner.next();
                    System.out.println("请再次输入密码：");
                    String new2Password = scanner.next();

                    flag1 = ! newPassword.equals(new2Password);

                    if (flag1) {
                        System.out.println("输入密码不一致：");
                    }
                }

                //获取连接
                Connection connection = DBUtils.getConnection();
                //创建执行器
                Statement statement = connection.createStatement();

                String sql = "update user set password='"+MD5Util.getMD5(newPassword)+"' where name='"+username+"'";

                int i1 = statement.executeUpdate(sql);

                if(i1>0){
                    System.out.println("密码修改成功------");
                }


            }

        }

    }


    public static void modifyPassword(){

    }

}
