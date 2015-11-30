package com.etc.myvideo.videoinfo.service;

import java.util.List;

import com.etc.myvideo.util.PageBean;
import com.etc.myvideo.videoinfo.dao.Videoinfodao;
import com.etc.myvideo.videoinfo.entity.Videoinfo;




public class Videoinfoservice {

	
	private Videoinfodao videoinfodao;

	public void setVideoinfodao(Videoinfodao videoinfodao) {
		this.videoinfodao = videoinfodao;
	}
	
	

	/**
	 * //��ѯ����ǰʮ����Ƶ��Ϣ
	 * 
	 * @return
	 */
	public List<Videoinfo> findbymaxtype(int vidMax) {
		
		
		return videoinfodao.findbymaxtype(vidMax);
	}
	
	
	
	/**
	 * ����vidId�õ���Ƶ��Ϣ
	 * @param id
	 * @return
	 */
	public Videoinfo findByvidId(Integer vidId) {

		
		return videoinfodao.findByvidId(vidId);

	}
	
	
	/**
	 * ������Ƶ�ķ�ҳ��ѯ
	 * 
	 * @return
	 */
	
	public PageBean<Videoinfo> findallvideo(int page) {
		PageBean<Videoinfo> pageBean = new PageBean<Videoinfo>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 10;
		pageBean.setLimit(limit);
		//  �����ܼ�¼��:
		int totalCount = 0 ;
		totalCount = videoinfodao.findallcount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Videoinfo> list = videoinfodao.findallvideo(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}


	
	//������Ƶ

	public void savevideoinfo(Videoinfo videoinfo) {
		videoinfodao.savevideoinfo(videoinfo);
		
	}


	

	/**
	 * ģ����ѯ
	 * @param id
	 * @return
	 */
	
	public PageBean<Videoinfo> findbylikename(String name, int page) {
		PageBean<Videoinfo> pageBean = new PageBean<Videoinfo>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 3;
		pageBean.setLimit(limit);
		//  �����ܼ�¼��:
		int totalCount = 0 ;
		totalCount = videoinfodao.countbylikename(name);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Videoinfo> list = videoinfodao.findbylikename(name, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	
	
	
	
	
	/**
	 * ������ģ����ѯ
	 * @param id
	 * @return
	 */
	
	public PageBean<Videoinfo> findbymaxtype(int typeid, int page) {
		PageBean<Videoinfo> pageBean = new PageBean<Videoinfo>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 3;
		pageBean.setLimit(limit);
		//  �����ܼ�¼��:
		int totalCount = 0 ;
		totalCount = videoinfodao.countbymaxtype(typeid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Videoinfo> list = videoinfodao.findbymaxtype(typeid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}


	// ҵ���ɾ����Ƶ
	public void delete(Videoinfo videoinfo) {
		videoinfodao.delete(videoinfo);
	}

	// ҵ����޸���Ƶ�ķ���
	public void updatevideoinfo(Videoinfo videoinfo) {
		videoinfodao.updatevideoinfo(videoinfo);
	}
	

}
