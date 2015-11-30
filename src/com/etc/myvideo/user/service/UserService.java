package com.etc.myvideo.user.service;

import java.util.List;

import com.etc.myvideo.user.dao.IUserDao;
import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.util.MailUitls;
import com.etc.myvideo.util.PageBean;
import com.etc.myvideo.util.UUIDUtils;



public class UserService implements IUserService {
	// ע��UserDao
		private IUserDao userDao;

		/* (non-Javadoc)
		 * @see com.yede.myvideo.user.service.IUserService#setUserDao(com.yede.myvideo.user.dao.IUserDao)
		 */
		@Override
		public void setUserDao(IUserDao userDao) {
			this.userDao = userDao;
		}
		
		
		// ���û�����ѯ�û��ķ���:
		/* (non-Javadoc)
		 * @see com.yede.myvideo.user.service.IUserService#findByUsername(java.lang.String)
		 */
		@Override
		public User findByUsername(String username){
			return userDao.findByUsername(username);
		}

		// ҵ�������û�ע�����:
		/* (non-Javadoc)
		 * @see com.yede.myvideo.user.service.IUserService#save(com.yede.myvideo.user.entity.User)
		 */
		@Override
		public void save(User user) {
			// �����ݴ��뵽���ݿ�
			user.setState(0); // 0:�����û�δ����.  1:�����û��Ѿ�����.
			String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
			user.setCode(code);
			userDao.save(user);
			// ���ͼ����ʼ�;
			MailUitls.sendMail(user.getuMail(), code);
		}

		// ҵ�����ݼ������ѯ�û�
		/* (non-Javadoc)
		 * @see com.yede.myvideo.user.service.IUserService#findByCode(java.lang.String)
		 */
		@Override
		public User findByCode(String code) {
			return userDao.findByCode(code);
		}

		// �޸��û���״̬�ķ���
		/* (non-Javadoc)
		 * @see com.yede.myvideo.user.service.IUserService#update(com.yede.myvideo.user.entity.User)
		 */
		@Override
		public void update(User existUser) {
			userDao.update(existUser);
		}

		// �û���¼�ķ���
		/* (non-Javadoc)
		 * @see com.yede.myvideo.user.service.IUserService#login(com.yede.myvideo.user.entity.User)
		 */
		@Override
		public User login(User user) {
			return userDao.login(user);
		}


		@Override
		public User findByEmail(String email) {
			
			
			return userDao.findByEmail(email);
		}
		
		// ����ID��ѯ�û�
		public User findByUserId(int userId){
			return userDao.findByUserId(userId);
		}
		
		// ��ѯ�����û�
		public List<User> findAll(){
			return userDao.findAll();
		}
		
		//��ҳ��ѯ�û�
		public PageBean<User> findAll(int page){
			
			PageBean<User> pageBean = new PageBean<User>();
			
			int limit = 10;						//ÿҳ��¼��
			int totalCount = 0;					//�ܼ�¼��
			int totalPage = 0;					//��ҳ��
			
			pageBean.setPage(page);				//���õ�ǰҳ��
			pageBean.setLimit(limit);			//������ʾÿҳ��¼��
			pageBean.setTotalCount(totalCount);	//�����ܼ�¼��
			pageBean.setTotalPage(totalPage);	//������ҳ��
			
			if(totalCount % limit == 0){
				totalPage = totalCount / limit;
			}else{
				totalPage = totalCount / limit + 1;
			}
			
			pageBean.setTotalPage(totalPage);
			
			int begin = (page-1) * limit;
			List<User> list = userDao.findAll(begin, limit);
			
			pageBean.setList(list);
			return pageBean;
		}
		

		@Override
		public void findPass(User user){
			user.setState(0);
			String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
			user.setCode(code);
			userDao.update(user);
		   MailUitls.sendMainForByPass(user.getuMail(), code);
			
		}
		
		public void delete(int id){
			userDao.delete(id);
		}
}
