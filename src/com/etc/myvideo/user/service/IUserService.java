package com.etc.myvideo.user.service;

import java.util.List;

import com.etc.myvideo.user.dao.IUserDao;
import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.util.PageBean;


public interface IUserService {

	public abstract void setUserDao(IUserDao userDao);

	// ���û�����ѯ�û��ķ���:
	public abstract User findByUsername(String username);

	// ҵ�������û�ע�����:
	public abstract void save(User user);

	// ҵ�����ݼ������ѯ�û�
	public abstract User findByCode(String code);

	// �޸��û���״̬�ķ���
	public abstract void update(User existUser);

	// �û���¼�ķ���
	public abstract User login(User user);
	
	//ͨ���������
	public abstract User findByEmail(String email);
	
	//����ID��ѯ�û�
	public User findByUserId(int userId);

	//��ѯ�����û�
	public List<User> findAll();
	
	//��ҳ��ѯ�����û�
	public PageBean<User> findAll(int page);

	void findPass(User user);
	
	public void delete(int id);
}