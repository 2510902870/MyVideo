package com.etc.myvideo.userupload.dao;

import java.util.List;

import com.etc.myvideo.userupload.entity.Userupload;
import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videoinfo.entity.Videoinfo;

public interface IUseruploadDao {

	/**
	 * �û��ϴ�����
	 * 
	 * @param userupload
	 */
	public abstract void save(Userupload userupload);

	/**
	 * �����ϴ���id����
	 * 
	 * @param uld
	 * @return
	 */
	public abstract Userupload findUseruploaByUld(String uld);

	/**
	 * �����û������ϴ��ĵ�Ӱ
	 * 
	 * @param uid
	 * @return
	 */
	public abstract List<Userupload> findUserupload(String uid);

	/**
	 * �����û���ѯ��ʹ�÷�ҳ��ѯ
	 * 
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public abstract List<Userupload> findbyUseruploadForPage(int uid,
			int begin, int limit);

	/**
	 * ɾ���û��ϴ�����Ƶ
	 * 
	 * @param userupload
	 */
	public abstract void deleteUseruploadVideoInfo(Userupload userupload);

	/**
	 * �����û��ϴ�����Ƶ
	 * 
	 * @param userupload
	 */
	public abstract void updateUseruploadVideoInfo(Userupload userupload);

	/**
	 * �ҵ���Ӧ������
	 * 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public abstract List<Userupload> findUseruploadForPage(int begin, int limit);

	/**
	 * \ �����û���Ų����ϴ�����Ƶ��
	 * 
	 * @param uid
	 * @return
	 */
	int findCountByUid(String uid);

	/**
	 * �������е���Ƶ��
	 * 
	 * @return
	 */
	int findAllCount();

	/**
	 * ������Ƶ�ϴ���״̬���Ҷ�Ӧ��Ƶ�������ϴ���ʱ������
	 */

	public List<Userupload> getUseruploadVideoInfoByStatus(int status);

	/**
	 * ������Ƶ�ϴ���״̬���Ҷ�Ӧ��Ƶ�������ϴ���ʱ������,ʵ�ַ�ҳ
	 * 
	 * @param status
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Userupload> getUseruploadVideoInfoByStatusForPage(int status,
			int begin, int limit);

	public void saveOrUpdate(Userupload userupload);
    
	
	/**
	 * ����û��ϴ�����Ƶ
	 * @param begin
	 * @param limit
	 * @return
	 */
	public abstract List<Userupload> getUseruploadVideoInfo(int begin,int limit);
	
	/**
	 * ���û��ϴ�����Ƶ���ͨ������Ƶ�ϴ���ӵ������б���
	 * @param userupload
	 * @return
	 */
	public abstract void addUseruploadToVideoInfo(Videoinfo videoinfo);
	
	
	public abstract void addUseruploadToVideoCount(Videocount videocount);
	
	/**
	 * ���û��ϴ�����Ƶδ���ͨ������Ƶ�Ӳ����б���ɾ��
	 * @param userupload
	 * @return
	 */
	public abstract void deleteUseruploadFromVideoInfo(Videoinfo videoinfo);

}
