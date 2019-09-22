package com.shujia.util;

import com.shujia.bean.User;

import java.sql.SQLException;
import java.util.List;

public class TestDBUtils {
    public static void main(String[] args) throws SQLException {

        List<User> list = new_DBUtils.select("select * from user", User.class);

        for (User user : list) {
            System.out.println(user);
        }
    }
}
