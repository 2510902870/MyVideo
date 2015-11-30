package com.etc.myvideo.videocount.dao;


import java.util.List;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.etc.myvideo.util.PageHibernateCallback;
import com.etc.myvideo.videocount.entity.Videocount;




public class Videocountdao extends HibernateDaoSupport {

	/**
	 * ���ݵ��������ѯӰ����Ϣ
	 * 
	 * @return
	 */
	public List<Videocount> finddbycouClick() {

		String hql = "from Videocount vc group by vc.videoinfo.vidId  order by vc.couClick desc";
		List<Videocount> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Videocount>(hql, null, 0, 10));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;

	}
	
	
	
	
	
	
	
	
	
	


	/**
	 * ����id����ѯӰ����Ϣ
	 * 
	 * @return
	 */
	public Videocount findBycouId(Integer couId) {

		Videocount instance = getHibernateTemplate().get(Videocount.class,couId);
		return instance;

	}
	//������Ƶ������Ϣ
	  public void saveVideocount(Videocount transientInstance) {

	           this.getHibernateTemplate().save(transientInstance);
	
	  }
	  
	// DAO�е�ɾ���ķ���
		public void deleteVideocount(Videocount videocount) {
			this.getHibernateTemplate().delete(videocount);
		}

		public void updatevideocount(Videocount videocount) {
			this.getHibernateTemplate().update(videocount);
		}

}
