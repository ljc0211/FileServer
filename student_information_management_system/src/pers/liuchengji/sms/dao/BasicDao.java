package pers.liuchengji.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pers.liuchengji.sms.util.DbUtil;

/**
 * @ClassName: BasicDao
 * @Description: 基本数据库操作
 * @author: liuchengji
 * @date: May 10, 2019 2:32:54 PM
 * 
 */
public class BasicDao {

	private Connection connection = DbUtil.getConnection();

	/**
	 * @Title: query
	 * @Description: 查询数据
	 * @param: sql
	 * @return: ResultSet
	 */
	public static ResultSet query(String sql) {
		try {
			PreparedStatement preparedStatement = new BasicDao().connection.prepareStatement(sql);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: update
	 * @Description: 判断是否已更新数据库信息
	 * @param: sql
	 * @return: boolean
	 */
	public boolean update(String sql) {

		try {
			return connection.prepareStatement(sql).executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//	取用数据库连接。
	public static Connection getConnection() {
		return new BasicDao().connection;
	}

	/**
	 * @Title: releaseConnection
	 * @Description: 释放数据库连接资源
	 * @return: void
	 */
//	public static void releaseConnection() {
//		DbUtil.freeResource(null, null, connection);
//	}

}
