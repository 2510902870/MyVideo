package com.etc.myvideo.userupload.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.userupload.entity.Userupload;
import com.etc.myvideo.userupload.service.IUseruploadInfoService;
import com.etc.myvideo.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UseruploadInfoAction extends ActionSupport implements
		ModelDriven<Userupload> {

	private Userupload userupload = new Userupload();

	private IUseruploadInfoService useruploadinfoservice;

	// ͼƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File imageaddfile;// ͼƬ�ļ�
	private String imageaddfileFileName;// ͼƬ�ļ���
	private String imageaddfileContentType;// ͼƬ�ļ�����

	// ӰƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File uldaddrfile; // ӰƬ��ַ��
	private String uldaddrfileFileName;// ӰƬ�ļ���
	private String uldaddrfileContentType;// ӰƬ�ļ�����

	// ����
	private Integer uld;
	private String name;// ӰƬ��
	private String type;
	private String duration;// ����
	private String info;// ӰƬ��Ϣ
	private String url;// ����
	private Timestamp upload;// �ϴ�ʱ��
	private String image;// ͼƬ·��
	private Integer status;// ӰƬ״̬

	// ҳ��
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getUld() {
		return uld;
	}

	public void setUld(Integer uld) {
		this.uld = uld;
	}

	public Userupload getUserupload() {
		return userupload;
	}

	public void setUserupload(Userupload userupload) {
		this.userupload = userupload;
	}

	public IUseruploadInfoService getUseruploadinfoservice() {
		return useruploadinfoservice;
	}

	public void setUseruploadinfoservice(
			IUseruploadInfoService useruploadinfoservice) {
		this.useruploadinfoservice = useruploadinfoservice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getUpload() {
		return upload;
	}

	public void setUpload(Timestamp upload) {
		this.upload = upload;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public File getUldaddrfile() {
		return uldaddrfile;
	}

	public void setUldaddrfile(File uldaddrfile) {
		this.uldaddrfile = uldaddrfile;
	}

	public String getUldaddrfileFileName() {
		return uldaddrfileFileName;
	}

	public void setUldaddrfileFileName(String uldaddrfileFileName) {
		this.uldaddrfileFileName = uldaddrfileFileName;
	}

	public String getUldaddrfileContentType() {
		return uldaddrfileContentType;
	}

	public void setUldaddrfileContentType(String uldaddrfileContentType) {
		this.uldaddrfileContentType = uldaddrfileContentType;
	}

	// ������Ƶ�ķ���:
	public File getImageaddfile() {
		return imageaddfile;
	}

	public void setImageaddfile(File imageaddfile) {
		this.imageaddfile = imageaddfile;
	}

	public String getImageaddfileFileName() {
		return imageaddfileFileName;
	}

	public void setImageaddfileFileName(String imageaddfileFileName) {
		this.imageaddfileFileName = imageaddfileFileName;
	}

	public String getImageaddfileContentType() {
		return imageaddfileContentType;
	}

	public void setImageaddfileContentType(String imageaddfileContentType) {
		this.imageaddfileContentType = imageaddfileContentType;
	}

	public String savevideoinfo() throws IOException {
		// ���ύ��������ӵ����ݿ���.
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (user != null) {

			userupload.setUid(user.getUid());

		}

		if (imageaddfile != null) {
			// ���ϴ�����������.
			// ��õķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadimage");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + imageaddfileFileName);
			// �ļ��ϴ�:������ļ����Ƶ������ļ�
			FileUtils.copyFile(imageaddfile, diskFile);
			// �����ͼƬ��·��
			userupload.setImage("uploadimage/" + imageaddfileFileName);
		}

		if (uldaddrfile != null) {
			// ���ϴ�����������.
			// ����ϴ��ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadvideo");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + uldaddrfileFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(uldaddrfile, diskFile);
			// �����ϴ���Ƶ�ļ���·��
			userupload.setUrl("uploadVideo/" + uldaddrfileFileName);
		}

		// ����ø���Ϣ
		useruploadinfoservice.save(userupload);

		return "saveSuccess";

	}

	// ɾ����Ƶ�ķ���:
	public String deletevideo() {
		// ����id��ѯ��Ƶ��Ϣ
		Userupload useruploaddelete = useruploadinfoservice
				.findUseruploaByUld(userupload.getUld().toString());
		System.out.println(useruploaddelete);
		// ɾ��:
		// String path = ServletActionContext.getServletContext().getRealPath(
		// "/" + userupload.getUrl());
		// File file = new File(path);
		// file.delete();

		// ɾ�����ݿ��м�¼:
		useruploadinfoservice.deleteUseruploadVideoInfo(userupload);
		// ҳ����ת
		return "deleteSuccess";
	}

	// �༭��Ƶ�ķ���
	public String editvideoinfosave() {
		// ����id��ѯ��Ƶ��Ϣ
		Userupload useruploadVideoInfo = useruploadinfoservice
				.findUseruploaByUld(userupload.getUld().toString());
		useruploadVideoInfo.setName(userupload.getName());
		useruploadVideoInfo.setStatus(0);
		useruploadVideoInfo.setInfo(userupload.getInfo());
		useruploadVideoInfo.setType(userupload.getType());

		useruploadinfoservice.updateUseruploadVideoInfo(useruploadVideoInfo);
		// ҳ����ת���༭ҳ��:
		return "editSuccess";
	}

	// �޸���Ƶ�ķ���
	public String updatevideoinfo() throws IOException {

		// �ϴ�:
		if (imageaddfile != null) {
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + userupload.getImage());
			File file = new File(delPath);
			file.delete();
			// ����ϴ��ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadimage");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + imageaddfileFileName);
			// �ļ��ϴ�:
			FileUtils.copyFile(imageaddfile, diskFile);

			userupload.setImage("uploadimage/" + imageaddfileFileName);
		}

		useruploadinfoservice.updateUseruploadVideoInfo(userupload);
		// ҳ����ת
		return "updateSuccess";

	}

	// ���Ҷ��õ���Ƶ��Ϣ��
	public String getUseruploadVideoinfo() {
		// ����id��ѯ��Ƶ��Ϣ
		Userupload useruploadVideoInfo = useruploadinfoservice
				.findUseruploaByUld(userupload.getUld().toString());
		ServletActionContext.getContext().getValueStack()
				.set("userupload", useruploadVideoInfo);
		// ҳ����ת���༭ҳ��:
		return "edituseruploadinfo";
	}

	/**
	 * ���ǻ�ö����û����ϴ�����Ƶ��Ϣ
	 * 
	 * adminUserupload_getUserVideoInfo
	 * 
	 * @return
	 */
	public String getUserVideoInfo() {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (user != null) {
			PageBean<Userupload> pageBean = useruploadinfoservice
					.findUseruploadforPageBean(user.getUid().toString(), page);
			// �����ݴ��뵽ֵջ�б��浽ҳ��
			ActionContext.getContext().getValueStack()
					.set("pageBean", pageBean);

			ServletActionContext.getRequest().getSession()
					.setAttribute("pagebean", pageBean);
			return "userVideoInfo";
		}
		return null;

	}

	@Override
	public Userupload getModel() {
		return userupload;
	}

}
