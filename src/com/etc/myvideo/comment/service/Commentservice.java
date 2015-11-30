package com.etc.myvideo.comment.service;

import java.util.List;

import com.etc.myvideo.comment.dao.Commentdao;
import com.etc.myvideo.comment.entity.Comment;
import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.util.PageBean;

public class Commentservice {

	private Commentdao commentdao;

	public void setCommentdao(Commentdao commentdao) {
		this.commentdao = commentdao;
	}

	// ����vidId���з�ҳ��ѯ����
	public PageBean<Comment> findByPagecouId(Integer couId, int page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = commentdao.findByPagecouId(couId);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Comment> list = commentdao.findByPagecouId(couId, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ��������
	public void savecomment(Comment comment) {

		commentdao.savecomment(comment);
	}

	// ��ѯ�Ƿ����
	public Comment chadianzan(Integer uid, Integer couId) {
		return commentdao.chadianzan(uid, couId);

	}

	// ��ѯ�ܵ���
	public int findallzan(Integer couId) {

		return commentdao.findallzan(couId);
	}

	/**
	 * ͨ��comId��ѯ����
	 * 
	 * @param comId
	 * @param page
	 * @return
	 */
	public PageBean<Comment> findByVideoList(int comId, int page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = commentdao.findByPagecouId(comId);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Comment> list = commentdao.findByVideoList(comId, begin, limit);
		pageBean.setList(list);
		if (pageBean != null) {
			return pageBean;
		}
		return null;
	}

	public PageBean<Comment> getAllComment(int page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:

		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = commentdao.getAllCommentCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Comment> list = commentdao.getAllComment(begin, limit);
		pageBean.setList(list);
		if (pageBean != null) {
			return pageBean;
		}
		return null;
	}

	public void deleteComment(Comment comment) {
		commentdao.deleteComment(comment);
	}

}
