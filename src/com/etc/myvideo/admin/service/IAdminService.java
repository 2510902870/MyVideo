package com.etc.myvideo.admin.service;

import com.etc.myvideo.admin.dao.IAdminDao;
import com.etc.myvideo.admin.entity.Admin;

public interface IAdminService {

	public abstract void setAdminDao(IAdminDao AdminDao);

	// ���û�����ѯ�û��ķ���:
	public abstract Admin findByAdminName(String AdminName);

	// ҵ�������û�ע�����:
	public abstract void save(Admin admin);

	// �޸��û���״̬�ķ���
	public abstract void update(Admin existAdmin);

	// �û���¼�ķ���
	public abstract Admin login(Admin Admin);
	//ͨ���������
	public abstract Admin findByEmail(String email);
	//������Ϣ�鿴
	public Admin findByAdminId(int AdminId);

}