package com.etc.myvideo.admin.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.etc.myvideo.admin.entity.Admin;



/**
 *  ����Աdao��
 * @author Administrator
 *
 */
public class AdminDao extends HibernateDaoSupport implements IAdminDao {

	/**
	 * �����û�������
	 */
	@Override
	public Admin findByAdminName(String AdminName) {
		String hql = "from Admin where username = ?";
		List<Admin> list = this.getHibernateTemplate().find(hql, AdminName);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
    
	/**
	 * ��ӹ���Ա
	 */
	@Override
	public void save(Admin Admin) {
		this.getHibernateTemplate().save(Admin);
	}


	/**
	 * ���º��޸Ĺ���Ա
	 */
	@Override
	public void update(Admin existAdmin) {
		this.getHibernateTemplate().update(existAdmin);
	}

	/**
	 * ����Ա��¼����
	 */
	@Override
	public Admin login(Admin Admin) {
		String hql = "from Admin where username = ? and password = ?";
		List<Admin> list = this.getHibernateTemplate().find(hql,
				Admin.getUsername(), Admin.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

   /**
    * ����email���ҹ���Ա
    */
	@Override
	public Admin findByEmail(String email) {

		String hql = "from Admin where email=?";
		List<Admin> list = this.getHibernateTemplate().find(hql, email);
		if (list.size()>0) {
			
			return list.get(0);
		}
		return null;
	}

	/**
	 * ����Id�����û�
	 * @param AdminId
	 * @return
	 */
	public Admin findByAdminId(int AdminId) {
			Admin Admin = getHibernateTemplate().get(Admin.class, AdminId);
			return Admin;
	}

	
}
