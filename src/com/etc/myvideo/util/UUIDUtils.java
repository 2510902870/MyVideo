package com.etc.myvideo.util;


import java.util.UUID;

/**
 * ��������ַ����Ĺ�����
 
 */
public class UUIDUtils {
	/**
	 * ���������ַ���
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}