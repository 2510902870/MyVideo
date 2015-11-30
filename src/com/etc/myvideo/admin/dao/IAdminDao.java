package com.etc.myvideo.admin.dao;

import com.etc.myvideo.admin.entity.Admin;



public interface IAdminDao {

	// ������ѯ�Ƿ��и��û�:
	public abstract Admin findByAdminName(String AdminName);

	// ע���û��������ݿ����ʵ��
	public abstract void save(Admin admin);

	// �޸��û�״̬�ķ���
	public abstract void update(Admin existAdmin);

	// �û���¼�ķ���
	public abstract Admin login(Admin Admin);

	public abstract Admin findByEmail(String email);
	
	public abstract Admin findByAdminId(int AdminId);

}