package com.etc.myvideo.userupload.service;

import java.util.List;

import com.etc.myvideo.userupload.entity.Userupload;
import com.etc.myvideo.util.PageBean;
import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videoinfo.entity.Videoinfo;

public interface IUseruploadInfoService {

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
	 * @param uid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public abstract List<Userupload> findbyUseruploadForPage(int uid, int begin,
			int limit);
	
	/**
	 * ɾ���û��ϴ�����Ƶ
	 * @param userupload
	 */
	public abstract void deleteUseruploadVideoInfo(Userupload userupload);
	/**
	 * �����û��ϴ�����Ƶ
	 * @param userupload
	 */
	public abstract void updateUseruploadVideoInfo(Userupload  userupload);
	

	/**
	 * �ҵ���Ӧ������
	 * @param begin
	 * @param limit
	 * @return
	 */
	public abstract List<Userupload> findUseruploadForPage(int begin,
			int limit); 
	
	
	public abstract  PageBean<Userupload> findUseruploadforPageBean(String uid,int page);
	
	

	/**
	 * ������Ƶ�ϴ���״̬���Ҷ�Ӧ��Ƶ�������ϴ���ʱ������
	 */
	
	public List<Userupload> getUseruploadVideoInfoByStatus(int status);
	
	/**
	 * ������Ƶ�ϴ���״̬���Ҷ�Ӧ��Ƶ�������ϴ���ʱ������,ʵ�ַ�ҳ
	 * @param status 
	 * @param begin
	 * @param limit
	 * @return
	 */
	public PageBean getUseruploadVideoInfoByStatusForPage(int status,int page);
	
	
	public abstract PageBean<Userupload> getUseruploadVideoInfo(int page);
	
	
	/**
	 * ���û��ϴ�����Ƶ���ͨ������Ƶ�ϴ���ӵ������б���
	 * @param userupload
	 * @return
	 */
	public abstract void addUseruploadToVideoInfo(Videoinfo videoinfo);
    
	public abstract void addUseruploadToVideoCount(Videocount videocount);
	
	public void deleteUseruploadFromVideoInfo(Videoinfo videoinfo);
}