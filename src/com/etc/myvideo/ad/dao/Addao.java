package com.etc.myvideo.ad.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.etc.myvideo.ad.entity.Ad;
import com.etc.myvideo.util.PageHibernateCallback;




public class Addao  extends HibernateDaoSupport{

	//��ѯ���й��
	public List<Ad> findadList(){
		String hql = "from Ad";
		List<Ad> list = this.getHibernateTemplate().find(hql);
		if (list.size()!=0) {
			
			return list;
		}
		return null;
	}
	
	/**
	 * ��ѯ��ҳ���
	 * 
	 * @return
	 */
	
	public List<Ad> findpageadlis(int begin,int limit) {
		
		String hql = "from Ad order by id desc";
		List<Ad> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Ad>(hql,
						null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
		
	
	
	}
	
	
	
	/**
	 * ��ѯ��������
	 * @param id
	 * @return
	 */
	
	public int findpageadliscount() {
	
		List<Long> list =this.getHibernateTemplate().find("select count(*) from Ad ");
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	
	
	
	}
	
	
	//��ѯid���
	public Ad findadbyid(int id){
		String hql = "from Ad where id=?";
		List<Ad> list = this.getHibernateTemplate().find(hql,id);
		if (list.size()!=0) {
			return list.get(0);
		}
		return null;
	}
	
	
	// DAO�е�ɾ���ķ���
		public void deleteAd(Ad ad) {
			this.getHibernateTemplate().delete(ad);
		}

		public void updateAd(Ad ad) {
			this.getHibernateTemplate().update(ad);
		}

		

		//������

		public void saveAd(Ad ad) {
			this.getHibernateTemplate().save(ad);
			
		}
}
