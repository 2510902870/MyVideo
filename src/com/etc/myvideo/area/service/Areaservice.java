package com.etc.myvideo.area.service;

import java.util.List;

import com.etc.myvideo.area.dao.Areadao;
import com.etc.myvideo.area.entity.Area;

public class Areaservice {
	
	private Areadao areadao;


	public void setAreadao(Areadao areadao) {
		this.areadao = areadao;
	}
	
	//��ѯ���еĵ���
		public List<Area> findaAreas(){
			
			
			return areadao.findaAreas();
		}
	
	

}
