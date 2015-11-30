package com.etc.myvideo.videocount.service;

import java.util.List;

import com.etc.myvideo.videocount.dao.Videocountdao;
import com.etc.myvideo.videocount.entity.Videocount;

public class Videocountservice {
	private Videocountdao videocountdao;
	
	
	
	public void setVideocountdao(Videocountdao videocountdao) {
		this.videocountdao = videocountdao;
	}



	/**
	 * ���ݵ��������ѯӰ����Ϣ
	 * @return
	 */
		public List<Videocount> finddbycouClick(){
			return videocountdao.finddbycouClick();
		}
		
		
		/**
		 * ����id����ѯӰ����Ϣ
		 * 
		 * @return
		 */
		public Videocount findBycouId(Integer couId) {
			
			return videocountdao.findBycouId(couId);
		}
		
		//������Ƶ������Ϣ
		  public void saveVideocount(Videocount transientInstance) {

		          
		     videocountdao.saveVideocount(transientInstance);
		  }
		  
		// DAO�е�ɾ���ķ���
			public void delete(Videocount videocount) {
				videocountdao.deleteVideocount(videocount);
			}

			public void updatevideocount(Videocount videocount) {
				videocountdao.updatevideocount(videocount);
			}
}
