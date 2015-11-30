package com.etc.myvideo.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.ad.entity.Ad;
import com.etc.myvideo.ad.service.Adservice;
import com.etc.myvideo.grade.entity.Grade;
import com.etc.myvideo.grade.service.Gradeservice;
import com.etc.myvideo.switchimage.entity.SwitchImage;
import com.etc.myvideo.switchimage.service.SwitchImageService;
import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videocount.service.Videocountservice;
import com.etc.myvideo.videoinfo.entity.Videoinfo;
import com.etc.myvideo.videoinfo.service.Videoinfoservice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	
	private SwitchImageService switchImageService;
	
	
	private Videoinfoservice videoinfoservice;
	private Videocountservice videocountservice;
	private Gradeservice gradeservice;
	
	private Adservice adservice;
		
		
	public void setAdservice(Adservice adservice) {
		this.adservice = adservice;
	}
	
	

	public void setGradeservice(Gradeservice gradeservice) {
		this.gradeservice = gradeservice;
	}
	public void setVideocountservice(Videocountservice videocountservice) {
		this.videocountservice = videocountservice;
	}
	public void setVideoinfoservice(Videoinfoservice videoinfoservice) {
		this.videoinfoservice = videoinfoservice;
	}
	public void setSwitchImageService(SwitchImageService switchImageService) {
		this.switchImageService = switchImageService;
	}
		/**
	 * ִ�еķ�����ҳ�ķ���:
	 */
	public String execute(){
		/**
		 * ��ѯͼƬ�л�
		 */
	     List<SwitchImage> switchImagelist= switchImageService.findall();
		
		
		
		/**
		 * ��ѯǰ10�ĵ��Ӿ�
		 */
		List<Videoinfo> dianshijutenlist=videoinfoservice.findbymaxtype(2);
		
		/**
		 * ��ѯǰ10�ĵ�Ӱ
		 */
		List<Videoinfo> dianyingtenlist=videoinfoservice.findbymaxtype(1);
		
		
		/**
		 * ���ݵ������ѯ10����Ƶ
		 */
		
		
		List<Videocount> videocounts=videocountservice.finddbycouClick();
	
		
		/**
		 * ��������ǰ10��ѯ�ĵ�Ӱ
		 */
		
		List<Grade> grades=gradeservice.finddbygraCount(1);
		
		//��ѯ���
		List<Ad> ads=adservice.findadList();
		
		
		// ���浽ֵջ��:
		ActionContext.getContext().getValueStack().set("dianyingtenlist", dianyingtenlist);
		ActionContext.getContext().getValueStack().set("dianshijutenlist", dianshijutenlist);
		ActionContext.getContext().getValueStack().set("dianjirb", videocounts);
		ActionContext.getContext().getValueStack().set("grades", grades);
		ActionContext.getContext().getValueStack().set("ads", ads);
		
		
		
		ServletActionContext.getRequest().setAttribute("SwitchImageitem", switchImagelist);
		/*ServletActionContext.getRequest().getSession().setAttribute("ad", ads);*/
		
	
		return "index";
	}
	
	
}
