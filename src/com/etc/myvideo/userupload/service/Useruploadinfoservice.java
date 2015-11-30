package com.etc.myvideo.userupload.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.userupload.dao.IUseruploadDao;
import com.etc.myvideo.userupload.entity.Userupload;
import com.etc.myvideo.util.Constant;
import com.etc.myvideo.util.DateUtil;
import com.etc.myvideo.util.PageBean;
import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videoinfo.entity.Videoinfo;

public class Useruploadinfoservice implements IUseruploadInfoService {

	private IUseruploadDao userVideoInfoDao;

	public IUseruploadDao getUserVideoInfoDao() {
		return userVideoInfoDao;
	}

	public void setUserVideoInfoDao(IUseruploadDao userVideoInfoDao) {
		this.userVideoInfoDao = userVideoInfoDao;
	}

	@Override
	public void save(Userupload userupload) {

		userupload.setClick("0");
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");

			
		userupload.setUpload(DateUtil.getDateTime((new Date()).toString()));
		userupload.setStatus(0);
		userVideoInfoDao.save(userupload);

	}

	@Override
	public Userupload findUseruploaByUld(String uld) {
		return userVideoInfoDao.findUseruploaByUld(uld);
	}

	@Override
	public List<Userupload> findUserupload(String uid) {
		return userVideoInfoDao.findUserupload(uid);
	}

	@Override
	public List<Userupload> findbyUseruploadForPage(int uid, int begin,
			int limit) {
		return userVideoInfoDao.findbyUseruploadForPage(uid, begin, limit);
	}

	@Override
	public void deleteUseruploadVideoInfo(Userupload userupload) {
		userVideoInfoDao.deleteUseruploadVideoInfo(userupload);

	}

	@Override
	public void updateUseruploadVideoInfo(Userupload userupload) {
		userVideoInfoDao.updateUseruploadVideoInfo(userupload);

	}

	@Override
	public List<Userupload> findUseruploadForPage(int begin, int limit) {

		return userVideoInfoDao.findUseruploadForPage(begin, limit);
	}

	/**
	 * �����û������ϴ�����Ƶ
	 */
	@Override
	public PageBean<Userupload> findUseruploadforPageBean(String uid, int page) {

		PageBean<Userupload> pageBean = new PageBean<Userupload>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = Constant.LIMIT;

		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;

		totalCount = userVideoInfoDao.findCountByUid(uid);

		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;// �����ҳ��
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);// ������ҳ��
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;

		List<Userupload> list = userVideoInfoDao.findUseruploadForPage(begin,
				limit);

		pageBean.setList(list);

		System.out.println(pageBean.getList().get(0).getName());

		return pageBean;
	}

	@Override
	public List<Userupload> getUseruploadVideoInfoByStatus(int status) {
		return userVideoInfoDao.getUseruploadVideoInfoByStatus(status);
	}

	@Override
	public PageBean getUseruploadVideoInfoByStatusForPage(int status,
			int page) {

		PageBean<Userupload> pageBean = new PageBean<Userupload>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = Constant.LIMIT;

		pageBean.setLimit(limit);

		// �����ܼ�¼��:
		int totalCount = 0;
        //���������
		totalCount = userVideoInfoDao.findAllCount();

		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;// �����ҳ��
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);// ������ҳ��
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;

		List<Userupload> list = userVideoInfoDao.getUseruploadVideoInfoByStatusForPage(status, begin, limit);

		pageBean.setList(list);
		return pageBean;
	}
   
	
	/**
	 *  ����Ա���û��ϴ�����Ƶ���в鿴
	 */
	@Override
	public PageBean<Userupload> getUseruploadVideoInfo(int page) {
		
		PageBean<Userupload> pageBean = new PageBean<Userupload>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = Constant.LIMIT;

		pageBean.setLimit(limit);

		// �����ܼ�¼��:
		int totalCount = 0;
        //���������
		totalCount = userVideoInfoDao.findAllCount();

		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;// �����ҳ��
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);// ������ҳ��
		int begin = (page - 1) * limit;
		

		List<Userupload> list = userVideoInfoDao.getUseruploadVideoInfo(begin, limit);

		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void addUseruploadToVideoInfo(Videoinfo videoinfo) {
		
		userVideoInfoDao.addUseruploadToVideoInfo(videoinfo);
	}

	@Override
	public void deleteUseruploadFromVideoInfo(Videoinfo videoinfo) {
		userVideoInfoDao.deleteUseruploadFromVideoInfo(videoinfo);
		
	}

	@Override
	public void addUseruploadToVideoCount(Videocount videocount) {
		
		userVideoInfoDao.addUseruploadToVideoCount(videocount);
		
	}
	
	

}
