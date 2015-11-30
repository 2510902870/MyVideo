package com.etc.myvideo.switchimage.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.etc.myvideo.switchimage.entity.SwitchImage;




public class SwitchImageDao extends HibernateDaoSupport {

	public List<SwitchImage> findall() {	
		// ʹ������������ѯ:
		DetachedCriteria criteria = DetachedCriteria.forClass(SwitchImage.class);
		// ��id���е�������:
		criteria.addOrder(Order.desc("sId"));
		// ִ�в�ѯ:
		List<SwitchImage> list = this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

}
