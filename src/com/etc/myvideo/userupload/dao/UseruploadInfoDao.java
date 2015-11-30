package com.etc.myvideo.userupload.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.etc.myvideo.userupload.entity.Userupload;
import com.etc.myvideo.util.PageHibernateCallback;
import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videoinfo.entity.Videoinfo;

public class UseruploadInfoDao extends HibernateDaoSupport implements
		IUseruploadDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.etc.myvideo.userupload.dao.IUserVideoInfoDao#save(com.etc.myvideo
	 * .userupload.entity.Userupload)
	 */
	@Override
	public void save(Userupload userupload) {
		getHibernateTemplate().save(userupload);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.etc.myvideo.userupload.dao.IUserVideoInfoDao#findUseruploaByUld(java
	 * .lang.String)
	 */
	@Override
	public Userupload findUseruploaByUld(String uld) {

		return getHibernateTemplate().get(Userupload.class, Integer.parseInt(uld));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.etc.myvideo.userupload.dao.IUserVideoInfoDao#findUserupload(java.
	 * lang.String)
	 */
	@Override
	public List<Userupload> findUserupload(String uid) {
		String hql = "from Userupload u where u.uid=?";
		return getHibernateTemplate().find(hql, uid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.etc.myvideo.userupload.dao.IUserVideoInfoDao#findbyUserupload(int,
	 * int, int)
	 * 
	 * ����ʹ��limit���÷� limit��mysql���﷨select * from table limit
	 * m,n����m��ָ��¼��ʼ��index����0��ʼ����ʾ��һ����¼n��ָ�ӵ�m+1����ʼ��ȡn����select * from tablename
	 * limit 2,4��ȡ����3������6����4����¼
	 */
	@Override
	public List<Userupload> findbyUseruploadForPage(int uid, int begin,
			int limit) {

		String hql = "from Userupload u where u.uid=? order by upload desc";
		List<Userupload> list = getHibernateTemplate().execute(
				new PageHibernateCallback<Userupload>(hql,
						new Object[] { uid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;

	}

	@Override
	public void deleteUseruploadVideoInfo(Userupload userupload) {
		getHibernateTemplate().delete(userupload);

	}

	@Override
	public void updateUseruploadVideoInfo(Userupload userupload) {
		getHibernateTemplate().update(userupload);

	}

	/**
	 * ��ѯ��������
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int findAllCount() {

		List<Long> list = this.getHibernateTemplate().find(
				"select count(*) from Userupload ");
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;

	}

	/**
	 * �����û����ң����Ҷ��õ�����
	 * 
	 * @param uid
	 * @return
	 */
	@Override
	public int findCountByUid(String uid) {

		List<Long> list = this.getHibernateTemplate().find(
				"select count(*) from Userupload where uid=?",
				Integer.parseInt(uid));
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;

	}

	@Override
	public List<Userupload> findUseruploadForPage(int begin, int limit) {

		String hql = "from Userupload u order by u.upload desc";

		List<Userupload> list = getHibernateTemplate().execute(
				new PageHibernateCallback<Userupload>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;

		}
		return null;
	}

	@Override
	public List<Userupload> getUseruploadVideoInfoByStatus(int status) {

		String hql = "from Userupload  u where u.status=? order by u.upload desc";

		List<Userupload> list = getHibernateTemplate().find(hql, status);
		if (list != null && list.size() > 0) {
			return list;

		}
		return null;
	}

	@Override
	public List<Userupload> getUseruploadVideoInfoByStatusForPage(int status,
			int begin, int limit) {
		String hql = "from Userupload  u where u.status=? order by u.upload desc";

		List<Userupload> list = getHibernateTemplate().execute(
				new PageHibernateCallback<Userupload>(hql,
						new Object[] { status }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;

		}
		return null;
	}

	@Override
	public void saveOrUpdate(Userupload userupload) {
		
	}

	@Override
	public List<Userupload> getUseruploadVideoInfo(int begin, int limit) {
		String hql="from Userupload u order by u.upload desc";
		
		List<Userupload> list=getHibernateTemplate().execute(new  PageHibernateCallback<Userupload>(hql, null, begin, limit));
		if (list!=null&&list.size()>0) {
			return list;
			
		}
		return null;
	}

	@Override
	public void addUseruploadToVideoInfo(Videoinfo videoinfo) {
		
	   	getHibernateTemplate().saveOrUpdate(videoinfo);
	   	
		
	}

	@Override
	public void deleteUseruploadFromVideoInfo(Videoinfo videoinfo) {
		getHibernateTemplate().delete(videoinfo);
	}

	@Override
	public void addUseruploadToVideoCount(Videocount videocount) {
		
		getHibernateTemplate().save(videocount);
		
	}

}
