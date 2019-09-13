/**  
 * GitHub address: https://yubuntu0109.github.io/
 * @Title: StringUtil.java   
 * @Package pers.huangyuhui.sms.util   
 * @Description: ����
 * @author: Huang Yuhui     
 * @date: May 7, 2019 9:43:20 PM   
 * @version 1.0
 *
 */
package pers.huangyuhui.sms.util;

/**
 * @ClassName: StringUtil
 * @Description: �����ַ����ĳ��ù�����
 * @author: HuangYuhui
 * @date: May 7, 2019 9:43:20 PM
 * 
 */
public class StringUtil {

	/**
	 * @Title: isEmpty
	 * @Description: �ж��ַ����Ƿ�Ϊ��
	 * @param: String
	 * @return: boolean
	 */
	public static boolean isEmpty(String string) {
		if (string == null || "".equals(string)) {
			return true;
		}
		return false;
	}

}
