package com.etc.myvideo.switchimage.service;

import java.util.List;

import com.etc.myvideo.switchimage.dao.SwitchImageDao;
import com.etc.myvideo.switchimage.entity.SwitchImage;


public class SwitchImageService {
	// ע��Dao
	private SwitchImageDao switchImageDao;

	public void setSwitchImageDao(SwitchImageDao switchImageDao) {
		this.switchImageDao = switchImageDao;
	}

	/**
	 * ��ѯ���е�ͼƬ�л�Դ
	 * 
	 * @return
	 */
	public List<SwitchImage> findall() {
		return switchImageDao.findall();
	}

}
