package com.etc.myvideo.user.dao;

import java.util.List;

import com.etc.myvideo.user.entity.User;


public interface IUserDao {

	// ������ѯ�Ƿ��и��û�:
	public abstract User findByUsername(String username);

	// ע���û��������ݿ����ʵ��
	public abstract void save(User user);

	// ���ݼ������ѯ�û�
	public abstract User findByCode(String code);

	// �޸��û�״̬�ķ���
	public abstract void update(User existUser);

	// �û���¼�ķ���
	public abstract User login(User user);

	//����Id��ѯ�û�
	public abstract User findByEmail(String email);
	
	//����Id��ѯ�û�
	public abstract User findByUserId(int userId);
	
	// ��ѯ�����û�
	public abstract List<User> findAll();
	
	// ��ҳ��ѯ�����û�
	public abstract List<User> findAll(int begin, int limit);
	
	public void delete(int id);
}