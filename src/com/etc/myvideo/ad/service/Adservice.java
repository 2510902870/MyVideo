package com.etc.myvideo.ad.service;

import java.util.List;

import com.etc.myvideo.ad.dao.Addao;
import com.etc.myvideo.ad.entity.Ad;
import com.etc.myvideo.util.PageBean;


public class Adservice {
	
	
	private Addao addao;
	


	public void setAddao(Addao addao) {
		this.addao = addao;
	}

	//��ѯ���еĹ��
	public List<Ad> findadList(){
		
		return addao.findadList();
	}
	
	//��ѯid���
	public Ad findadbyid(int id){
		return addao.findadbyid(id);
	}
	
	

	// ҵ���ɾ��
	public void deleteAd(Ad ad) {
		addao.deleteAd(ad);
	}

	// ҵ����޸ĵķ���
	public void updateAd(Ad ad) {
		addao.updateAd(ad);
	}
	

	//������

	public void saveAd(Ad ad) {
		addao.saveAd(ad);
		
	}
	
	
	
	/**
	 * ���й��ķ�ҳ��ѯ
	 * 
	 * @return
	 */
	
	public PageBean<Ad> findallAd(int page) {
		PageBean<Ad> pageBean = new PageBean<Ad>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 10;
		pageBean.setLimit(limit);
		//  �����ܼ�¼��:
		int totalCount = 0 ;
		totalCount = addao.findpageadliscount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
	
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Ad> list = addao.findpageadlis(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
}
