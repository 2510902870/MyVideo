package com.etc.myvideo.videocount.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videocount.service.Videocountservice;
import com.etc.myvideo.videoinfo.entity.Videoinfo;
import com.etc.myvideo.videoinfo.service.Videoinfoservice;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminVideocountAction extends  ActionSupport implements ModelDriven<Videocount> {

	
	private Videocount videocount=new Videocount();
	
	private Videocountservice videocountservice;
	private  Videoinfoservice videoinfoservice;
	public void setVideoinfoservice(Videoinfoservice videoinfoservice) {
		this.videoinfoservice = videoinfoservice;
	}
	
	 public void setVideocountservice(Videocountservice videocountservice) {
			this.videocountservice = videocountservice;
		}

	@Override
	public Videocount getModel() {
		
		return videocount;
	}
	//ӰƬ��id
	private int vidId;
	
	
	
	public int getVidId() {
		return vidId;
	}

	public void setVidId(int vidId) {
		this.vidId = vidId;
	}

	public File getCouUrlfile() {
		return couUrlfile;
	}

	public void setCouUrlfile(File couUrlfile) {
		this.couUrlfile = couUrlfile;
	}

	public String getCouUrlfileFileName() {
		return couUrlfileFileName;
	}

	public void setCouUrlfileFileName(String couUrlfileFileName) {
		this.couUrlfileFileName = couUrlfileFileName;
	}

	public String getCouUrlfileContentType() {
		return couUrlfileContentType;
	}

	public void setCouUrlfileContentType(String couUrlfileContentType) {
		this.couUrlfileContentType = couUrlfileContentType;
	}



	// ӰƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File couUrlfile;
	private String couUrlfileFileName;
	private String couUrlfileContentType;

	
	
	
	//��ת����Ƶ������
	public String toshowDetail(){
		
		videocount=videocountservice.findBycouId(videocount.getCouId());
		
		return "toshowDetail";
		
	}
	
	//��ת�����Ƶ������
	  public String tosaveVideocount()  {
		  
		  Videoinfo  videoinfo= videoinfoservice.findByvidId(vidId);
		  
		  ServletActionContext.getRequest().setAttribute("videoinfo", videoinfo);
		  return "tosaveVideocount";
	  }
	
	
	//������Ƶ������Ϣ ����ûд������
	  public String saveVideocount() throws Exception {
		  
		  Videoinfo videoinfo=new Videoinfo();
			videoinfo.setVidId(vidId);
			videocount.setVideoinfo(videoinfo);
			videocount.setCouClick(0);
		  if (couUrlfile != null) {
			// ����ƷͼƬ�ϴ�����������.
				// ����ϴ�ͼƬ�ķ�������·��.
				String path = ServletActionContext.getServletContext().getRealPath(
						"/uploadvideo");
				// �����ļ����Ͷ���:
				File diskFile = new File(path + "//" + couUrlfileFileName);
				// �ļ��ϴ�:
				FileUtils.copyFile(couUrlfile, diskFile);

				
				
				videocount.setCouUrl("uploadvideo/" + couUrlfileFileName);
			}
		  
		  
		  videocount.setCouUpload(new Date());
		  videocountservice.saveVideocount(videocount);
		  
		  
		  
		  return "saveVideocount";
		  
	  }
	  
	  
	// �޸���Ƶ���ķ���
			public String updateVideocount() throws IOException {
				Videoinfo videoinfo=new Videoinfo();
				videoinfo.setVidId(vidId);
				videocount.setVideoinfo(videoinfo);
				
				// �ϴ�:
				if (couUrlfile != null) {
					String delPath = ServletActionContext.getServletContext()
							.getRealPath("/" +videocount.getCouUrl());
					File file = new File(delPath);
					file.delete();
					// ����ϴ�ͼƬ�ķ�������·��.
					String path = ServletActionContext.getServletContext().getRealPath(
							"/uploadvideo");
					// �����ļ����Ͷ���:
					File diskFile = new File(path + "//" + couUrlfileFileName);
					// �ļ��ϴ�:
					FileUtils.copyFile(couUrlfile, diskFile);

					videocount.setCouUrl("uploadvideo/" + couUrlfileFileName);
				}
				
				
		
				
				
				
				videocountservice.updatevideocount(videocount);
				// ҳ����ת
				//return "updateSuccess";
				System.out.println("dddd");
				return "updateSuccess";
			}
			
			
			public String deletVideocount() {
				
				Videocount _currrvideocount=videocountservice.findBycouId(videocount.getCouId());
				
				// ɾ��:
				String path = ServletActionContext.getServletContext().getRealPath(
						"/" + videocount.getCouUrl());
				File file = new File(path);
				file.delete();
				// ɾ�����ݿ��м�¼:
				videocountservice.delete(_currrvideocount);
				return "deletsuccess";
			}		
}
