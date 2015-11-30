package com.etc.myvideo.videoinfo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.etc.myvideo.area.entity.Area;
import com.etc.myvideo.comment.entity.Comment;
import com.etc.myvideo.grade.entity.Grade;
import com.etc.myvideo.maxtype.entity.Maxtype;
import com.etc.myvideo.mintype.entity.Mintype;
import com.etc.myvideo.videocount.entity.Videocount;


//��Ƶ
public class Videoinfo implements Serializable {



	@Override
	public String toString() {
		return "Videoinfo [vidId=" + vidId + ", mintype=" + mintype
				+ ", maxtype=" + maxtype + ", area=" + area + ", vidName="
				+ vidName + ", vidDirector=" + vidDirector + ", vidStar="
				+ vidStar + ", vidYear=" + vidYear + ", vidjianjie="
				+ vidjianjie + ", vidInfo=" + vidInfo + ", vidImage="
				+ vidImage + ", vidState=" + vidState + ", grades=" + grades
				+ ", videocounts=" + videocounts 
				+ "]";
	}
	private Integer vidId;//	--ӰƬ����������Զ�����
	private Mintype mintype; //	--С����
	private Maxtype maxtype;  //	--������
	private Area area;//	 //	--����
	private String vidName;//	--ӰƬ���� 
	private String vidDirector;//	--����o
	private String vidStar;//	--����
	private String vidYear;//	--���
	private String  vidjianjie      ;	//    Ӱ�Ӽ��  Varchar(30), 
	private String vidInfo;//	--ӰƬ���ݼ��  
	private String vidImage;//	--����ͼƬ��ӰƬ������   û�о͸�һ�� ������ͼƬ��
	private String vidState;//	--״̬	(������)��(�����)
	private Set<Grade> grades = new HashSet<Grade>();//����
	private Set<Videocount> videocounts = new HashSet<Videocount>();//����
	
	
	
	public Integer getVidId() {
		return vidId;
	}
	public void setVidId(Integer vidId) {
		this.vidId = vidId;
	}
	public Mintype getMintype() {
		return mintype;
	}
	public void setMintype(Mintype mintype) {
		this.mintype = mintype;
	}
	public Maxtype getMaxtype() {
		return maxtype;
	}
	public void setMaxtype(Maxtype maxtype) {
		this.maxtype = maxtype;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String getVidName() {
		return vidName;
	}
	public void setVidName(String vidName) {
		this.vidName = vidName;
	}
	public String getVidDirector() {
		return vidDirector;
	}
	public void setVidDirector(String vidDirector) {
		this.vidDirector = vidDirector;
	}
	public String getVidStar() {
		return vidStar;
	}
	public void setVidStar(String vidStar) {
		this.vidStar = vidStar;
	}
	
	
	public String getVidYear() {
		return vidYear;
	}
	public void setVidYear(String vidYear) {
		this.vidYear = vidYear;
	}
	public String getVidjianjie() {
		return vidjianjie;
	}
	public void setVidjianjie(String vidjianjie) {
		this.vidjianjie = vidjianjie;
	}
	public String getVidInfo() {
		return vidInfo;
	}
	public void setVidInfo(String vidInfo) {
		this.vidInfo = vidInfo;
	}
	public String getVidImage() {
		return vidImage;
	}
	public void setVidImage(String vidImage) {
		this.vidImage = vidImage;
	}
	public String getVidState() {
		return vidState;
	}
	public void setVidState(String vidState) {
		this.vidState = vidState;
	}
	public Set<Grade> getGrades() {
		return grades;
	}
	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}
	public Set<Videocount> getVideocounts() {
		return videocounts;
	}
	public void setVideocounts(Set<Videocount> videocounts) {
		this.videocounts = videocounts;
	}

	


}