package com.etc.myvideo.grade.service;

import java.util.List;

import com.etc.myvideo.grade.dao.Gradedao;
import com.etc.myvideo.grade.entity.Grade;


public class Gradeservice {
	private Gradedao gradedao;
	
	
	public void setGradedao(Gradedao gradedao) {
		this.gradedao = gradedao;
	}


	/**
	 * ��������������ѯĳ�����͵���Ϣ
	 * @return
	 */
	public List<Grade> finddbygraCount(int maxId){
		
		return gradedao.finddbygraCount(maxId);
	}
}
