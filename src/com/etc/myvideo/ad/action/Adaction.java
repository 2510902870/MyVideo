package com.etc.myvideo.ad.action;



import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.ad.entity.Ad;
import com.etc.myvideo.ad.service.Adservice;
import com.etc.myvideo.area.entity.Area;
import com.etc.myvideo.maxtype.entity.Maxtype;
import com.etc.myvideo.mintype.entity.Mintype;
import com.etc.myvideo.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Adaction extends ActionSupport implements ModelDriven<Ad>{
	private Adservice adservice;
	private Ad ad=new Ad();

	public void setAdservice(Adservice adservice) {
		this.adservice = adservice;
	}
	
	// ӰƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File adimagefile;
	private String adimagefileFileName;
	private String adimagefileContentType;

	@Override
	public Ad getModel() {
		
		return ad;
	}

	
	public File getAdimagefile() {
		return adimagefile;
	}


	public void setAdimagefile(File adimagefile) {
		this.adimagefile = adimagefile;
	}


	public String getAdimagefileFileName() {
		return adimagefileFileName;
	}


	public void setAdimagefileFileName(String adimagefileFileName) {
		this.adimagefileFileName = adimagefileFileName;
	}


	public String getAdimagefileContentType() {
		return adimagefileContentType;
	}


	public void setAdimagefileContentType(String adimagefileContentType) {
		this.adimagefileContentType = adimagefileContentType;
	}

//������
	public String saveAd() throws Exception{

		if (adimagefile != null) {
			// ���ϴ�����������.
			// ��õķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadimage");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + adimagefileFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(adimagefile, diskFile);

			
			ad.setAdimage("uploadimage/" + adimagefileFileName);
		}
		
		
		adservice.saveAd(ad);
		return "saveAd";
		
	}
	public String deleteAd(){
		ad=adservice.findadbyid(ad.getId());
		if (!ad.getAdimage().isEmpty()) {
			
			String path = ServletActionContext.getServletContext().getRealPath(
					"/" + ad.getAdimage());
			File file = new File(path);
			file.delete();
		}
		adservice.deleteAd(ad);
		return "deleteAd";
		
	}
	
	
	// �༭���ķ���
	public String editadinfo() {
		// ����id��ѯ�����Ϣ
		ad=adservice.findadbyid(ad.getId());
		// ҳ����ת���༭ҳ��:
		
	
		return "editadSuccess";
	}
	
	
	public String updateAd() throws Exception {
		
		// �ϴ�:
					if (adimagefile != null) {
						String delPath = ServletActionContext.getServletContext()
								.getRealPath("/" +ad.getAdimage());
						File file = new File(delPath);
						file.delete();
						// ����ϴ��ķ�������·��.
						String path = ServletActionContext.getServletContext().getRealPath(
								"/uploadimage");
						// �����ļ����Ͷ���:
						File diskFile = new File(path + "//" + adimagefileFileName);
						// �ļ��ϴ�:
						FileUtils.copyFile(adimagefile, diskFile);

						
						ad.setAdimage("uploadimage/" + adimagefileFileName);
					}
					
		adservice.updateAd(ad);
		return "updateAd";
	}
	
	
	//ҳ��
		private int page;
		
		
		
		
		public int getPage() {
			return page;
		}



		public void setPage(int page) {
			this.page = page;
		}

	//����
		public String findallAd(){
			
			PageBean<Ad>  vList= adservice.findallAd(page);
			// �����ݴ��뵽ֵջ�б��浽ҳ��
			ActionContext.getContext().getValueStack().set("AdpageBean", vList);
			// ҳ����ת:
			return "adminfindallAd";
		}
		
		
		public String toaddview(){
			
			return "toaddview";
		}
		

	
}
