package com.etc.myvideo.comment.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.etc.myvideo.comment.entity.Comment;
import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.util.PageHibernateCallback;





public class Commentdao extends HibernateDaoSupport {

	

	// ����ӰƬid��ѯ��ҳ���۵ļ���
		public List<Comment> findByPagecouId(Integer couId, int begin, int limit) {
			
			String hql = "from Comment cm  where cm.videocount.couId = ? order by cm.time desc";
			// ��ҳ��һ��д��:
			List<Comment> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Comment>(hql, new Object[]{couId}, begin, limit));
			if(list != null && list.size() > 0){
				return list;
			}
			return null;
			
		}
		
		
		// ����ӰƬid��ѯ���۵ĸ���
		public int findByPagecouId(Integer couId) {
			String hql = "select count(*) from Comment cm where cm.videocount.couId= ?";
			List<Long> list = this.getHibernateTemplate().find(hql,couId);
			if(list != null && list.size() > 0){
				return list.get(0).intValue();
			}
			return 0;
		}
		
		
		
		// DAO�еı������۵ķ���
		public void savecomment(Comment comment) {
			this.getHibernateTemplate().save(comment);
		}
		//��ѯ�Ƿ����
		public Comment chadianzan(Integer uid,Integer couId) {
			String hql=" from Comment cm where cm.user.uid=? and cm.videocount.couId=? and cm.isdianzan=1 ";
			List<Comment> list = this.getHibernateTemplate().find(hql, uid,couId);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		}
		
		
		//��ѯ�ܵ���
		public int findallzan(Integer couId) {
			String hql = "select count(*) from Comment cm where cm.videocount.couId= ? and cm.isdianzan=1";
			List<Long> list = this.getHibernateTemplate().find(hql,couId);
			if(list != null && list.size() > 0){
				return list.get(0).intValue();
			}
			return 0;
		}
		
		/**
		 * ͨ��comId��ѯ����
		 * 
		 * @param comId
		 * @param begin
		 * @param limit
		 * @return
		 */
		public List findByVideoList(int comId, int begin, int limit) {
			String hql = "from Comment c where c.comId= ? ";
			List<Comment> list = getHibernateTemplate().execute(
					new PageHibernateCallback<Comment>(hql, new Object[] { comId },
							begin, limit));
			if (list != null) {
				return list;
			}
			return null;
		}

		public List<Comment> findCommentByCouid(int couId, int begin, int limit) {
			String hql = "from Comment c where c.videocount.couId=?";
			List<Comment> list = getHibernateTemplate().execute(
					new PageHibernateCallback<Comment>(hql, new Object[] { couId },
							begin, limit));
			return list;

		}

		public List<Comment> getAllComment(int begin, int limit) {

			String hql = "from Comment";
			List<Comment> list = getHibernateTemplate().execute(
					new PageHibernateCallback<Comment>(hql, null, begin, limit));
			return list;

		}

		/**
		 * �������۱�������ܵ�����
		 * 
		 * @return
		 */
		public int getAllCommentCount() {

			String hql = "select count(*) from Comment";
			List<Long> list = getHibernateTemplate().find(hql);

			if (list != null && list.size() > 0) {
				return (list.get(0)).intValue();
			}
			return 0;
		}
		
		
		public void deleteComment(Comment comment){
			getHibernateTemplate().delete(comment);
		}


		
		
		


}
