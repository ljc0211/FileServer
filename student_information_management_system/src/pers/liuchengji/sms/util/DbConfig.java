/**  
 * GitHub address: https://yubuntu0109.github.io/
 * @Title: DbConfig.java   
 * @Package pers.liuchengji.sms.util
 * @Description: ���ݿ�����
 * @author: Huang Yuhui     
 * @date: May 10, 2019 1:29:20 PM   
 * @version 1.0
 *
 */
package pers.liuchengji.sms.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: DbConfig
 * @Description: ��ʼ�����ݿ�������Ϣ
 * @author: liuchengji
 * @date: May 10, 2019 1:29:20 PM
 * 
 */
public class DbConfig {

	private static Properties properties;
	// ��ȡ���ݿ������ļ�
	private static InputStream inputStream = DbConfig.class.getResourceAsStream("/databaseConfig.properties");

	private DbConfig() {
	}

	static {
		try {
			properties = new Properties();
			properties.load(inputStream);
			properties.getProperty("Url");
			properties.getProperty("UserName");
			properties.getProperty("UserPassword");
			properties.getProperty("DriverName");

		} catch (FileNotFoundException e) {
			System.err.println("error : not found the specified configuration file: databaseConfig.properties");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Title: getProperties
	 * @Description: ��ȡ���ݿ�����
	 * @return: Properties
	 */
	public static Properties getProperties() {
		return properties;
	}

}
