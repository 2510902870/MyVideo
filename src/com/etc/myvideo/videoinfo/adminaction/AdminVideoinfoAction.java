package com.etc.myvideo.videoinfo.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.area.entity.Area;
import com.etc.myvideo.area.service.Areaservice;
import com.etc.myvideo.maxtype.entity.Maxtype;
import com.etc.myvideo.maxtype.service.Maxtypeservice;
import com.etc.myvideo.mintype.entity.Mintype;
import com.etc.myvideo.mintype.service.Mintypeservice;
import com.etc.myvideo.util.PageBean;
import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videoinfo.entity.Videoinfo;
import com.etc.myvideo.videoinfo.service.Videoinfoservice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminVideoinfoAction extends ActionSupport implements
		ModelDriven<Videoinfo> {

	private Videoinfo videoinfo = new Videoinfo();

	private Videoinfoservice videoinfoservice;
	private Areaservice areaservice;
	private Maxtypeservice maxtypeservice;

	private Mintypeservice mintypeservice;

	public Mintypeservice getMintypeservice() {
		return mintypeservice;
	}

	public void setMintypeservice(Mintypeservice mintypeservice) {
		this.mintypeservice = mintypeservice;
	}

	public void setMaxtypeservice(Maxtypeservice maxtypeservice) {
		this.maxtypeservice = maxtypeservice;
	}

	public void setVideoinfoservice(Videoinfoservice videoinfoservice) {
		this.videoinfoservice = videoinfoservice;
	}

	public void setAreaservice(Areaservice areaservice) {
		this.areaservice = areaservice;
	}

	// ͼƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File imageaddfiler;
	private String imageaddfilerFileName;
	private String imageaddfilerContentType;

	// ӰƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File viaddrfile;
	private String viaddrfileFileName;
	private String viaddrfileContentType;

	// ��ʱ����
	private int maxId;
	private int minId;
	private int areaId;
	private String couDuration;// ʱ��
	private int couCount;// ����

	public int getCouCount() {
		return couCount;
	}

	public void setCouCount(int couCount) {
		this.couCount = couCount;
	}

	public File getViaddrfile() {
		return viaddrfile;
	}

	public void setViaddrfile(File viaddrfile) {
		this.viaddrfile = viaddrfile;
	}

	public String getViaddrfileFileName() {
		return viaddrfileFileName;
	}

	public void setViaddrfileFileName(String viaddrfileFileName) {
		this.viaddrfileFileName = viaddrfileFileName;
	}

	public String getViaddrfileContentType() {
		return viaddrfileContentType;
	}

	public void setViaddrfileContentType(String viaddrfileContentType) {
		this.viaddrfileContentType = viaddrfileContentType;
	}

	public String getCouDuration() {
		return couDuration;
	}

	public void setCouDuration(String couDuration) {
		this.couDuration = couDuration;
	}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public int getMinId() {
		return minId;
	}

	public void setMinId(int minId) {
		this.minId = minId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public File getImageaddfiler() {
		return imageaddfiler;
	}

	public void setImageaddfiler(File imageaddfiler) {
		this.imageaddfiler = imageaddfiler;
	}

	public String getImageaddfilerFileName() {
		return imageaddfilerFileName;
	}

	public void setImageaddfilerFileName(String imageaddfilerFileName) {
		this.imageaddfilerFileName = imageaddfilerFileName;
	}

	public String getImageaddfilerContentType() {
		return imageaddfilerContentType;
	}

	public void setImageaddfilerContentType(String imageaddfilerContentType) {
		this.imageaddfilerContentType = imageaddfilerContentType;
	}

	@Override
	public Videoinfo getModel() {

		return videoinfo;
	}

	// ��ת��ӽ���

	public String toaddview() {

		List<Area> areas = areaservice.findaAreas();
		List<Maxtype> maxtypes = maxtypeservice.findMaxtype();
		List<Mintype> mintypes = mintypeservice.findMintype();

		ActionContext.getContext().getValueStack().set("areas", areas);
		ActionContext.getContext().getValueStack().set("maxtypes", maxtypes);
		ActionContext.getContext().getValueStack().set("mintypes", mintypes);
		return "toaddview";
	}

	// ������Ƶ�ķ���:
	public String savevideoinfo() throws IOException {
		// ���ύ��������ӵ����ݿ���.
		Area area = new Area();
		area.setAreaId(areaId);

		Mintype mintype = new Mintype();
		mintype.setMinId(minId);

		Maxtype maxtype = new Maxtype();
		maxtype.setMaxId(maxId);
		videoinfo.setArea(area);
		videoinfo.setMaxtype(maxtype);
		videoinfo.setMintype(mintype);

		if (imageaddfiler != null) {
			// ���ϴ�����������.
			// ��õķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadimage");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + imageaddfilerFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(imageaddfiler, diskFile);

			videoinfo.setVidImage("uploadimage/" + imageaddfilerFileName);
		}

		Videocount videocount = new Videocount();
		if (viaddrfile != null) {
			// ���ϴ�����������.
			// ����ϴ��ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadvideo");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + viaddrfileFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(viaddrfile, diskFile);

			videocount.setCouUrl("uploadvideo/" + viaddrfileFileName);
		}

		videocount.setCouCount(couCount);
		videocount.setCouClick(0);
		videocount.setCouUpload(new Date());
		videocount.setCouDuration(couDuration);

		videocount.setVideoinfo(videoinfo);

		videoinfo.getVideocounts().add(videocount);

		videoinfoservice.savevideoinfo(videoinfo);
		return "saveSuccess";

	}

	// ҳ��
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// ��ҳ����Ƶ
	public String findallvideo() {

		PageBean<Videoinfo> vList = videoinfoservice.findallvideo(page);
		// �����ݴ��뵽ֵջ�б��浽ҳ��
		ActionContext.getContext().getValueStack().set("pageBean", vList);
		// ҳ����ת:
		return "adminfindallvideo";
	}

	// ɾ����Ƶ�ķ���:
	public String deletevideo() {
		// ����id��ѯ��Ƶ��Ϣ
		videoinfo = videoinfoservice.findByvidId(videoinfo.getVidId());
		// ɾ��:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + videoinfo.getVidImage());
		File file = new File(path);
		file.delete();
		Set<Videocount> videocounts = videoinfo.getVideocounts();

		if (videocounts.size() != 0) {
			for (Videocount vc : videocounts) {
				// ɾ��:
				String path2 = ServletActionContext.getServletContext()
						.getRealPath("/" + vc.getCouUrl());
				File file2 = new File(path2);
				file2.delete();
			}

		}

		// ɾ�����ݿ��м�¼:
		videoinfoservice.delete(videoinfo);
		// ҳ����ת
		return "deleteSuccess";
	}

	// �༭��Ƶ�ķ���
	public String editvideoinfo() {
		// ����id��ѯ��Ƶ��Ϣ
		videoinfo = videoinfoservice.findByvidId(videoinfo.getVidId());

		List<Area> areas = areaservice.findaAreas();
		List<Maxtype> maxtypes = maxtypeservice.findMaxtype();
		List<Mintype> mintypes = mintypeservice.findMintype();

		ActionContext.getContext().getValueStack().set("areas", areas);
		ActionContext.getContext().getValueStack().set("maxtypes", maxtypes);
		ActionContext.getContext().getValueStack().set("mintypes", mintypes);
		// ҳ����ת���༭ҳ��:
		return "editSuccess";
	}

	// �޸���Ƶ�ķ���
	public String updatevideoinfo() throws IOException {

		// �ϴ�:
		if (imageaddfiler != null) {
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + videoinfo.getVidImage());
			File file = new File(delPath);
			file.delete();
			// ����ϴ��ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadimage");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + imageaddfilerFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(imageaddfiler, diskFile);

			videoinfo.setVidImage("uploadimage/" + imageaddfilerFileName);
		}

		videoinfoservice.updatevideoinfo(videoinfo);
		// ҳ����ת
		return "updateSuccess";

	}

}
