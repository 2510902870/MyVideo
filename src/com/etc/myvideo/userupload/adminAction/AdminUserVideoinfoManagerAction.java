package com.etc.myvideo.userupload.adminAction;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.userupload.entity.Userupload;
import com.etc.myvideo.userupload.service.IUseruploadInfoService;
import com.etc.myvideo.userupload.service.Useruploadinfoservice;
import com.etc.myvideo.util.PageBean;
import com.etc.myvideo.videocount.entity.Videocount;
import com.etc.myvideo.videoinfo.entity.Videoinfo;
import com.etc.myvideo.videoinfo.service.Videoinfoservice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ���ǹ���Ա�����û��ϴ���Ƶ��action
 * 1.��Ҫ������û��ϴ�����Ƶ�Ƿ���ȷ����Ҫʹ��ʹ�÷�ҳ���ң��޸Ķ�Ӧ��״̬��״̬Ϊ1��ʾ���ͨ�����������ܲ��Ÿ���Ƶ
 * 2.����漰����Ƶ���Ϸ�������Ա���Խ���Ƶɾ�� 3.����Ա���Ը����û������ϴ�����Ƶ��Ҳ���Բ����ϴ���������Ƶ 4.
 * 
 * @author Administrator
 * 
 */
public class AdminUserVideoinfoManagerAction extends ActionSupport implements
		ModelDriven<Userupload> {

	private Userupload userupload = new Userupload();

	private IUseruploadInfoService useruploadinfoservice;



	// ͼƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File imagefile;// ͼƬ�ļ�
	private String imagefileFileName;// ͼƬ�ļ���
	private String imagefileContentType;// ͼƬ�ļ�����

	// ӰƬ�ϴ��ļ��ϴ���Ҫ����������:
	private File uldaddrfile; // ӰƬ��ַ��
	private String uldaddrfileFileName;// ӰƬ�ļ���
	private String uldaddrfileContentType;// ӰƬ�ļ�����

	// ����
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
	
    
	public File getImagefile() {
		return imagefile;
	}

	public void setImagefile(File imagefile) {
		this.imagefile = imagefile;
	}

	public String getImagefileFileName() {
		return imagefileFileName;
	}

	public void setImagefileFileName(String imagefileFileName) {
		this.imagefileFileName = imagefileFileName;
	}

	public String getImagefileContentType() {
		return imagefileContentType;
	}

	public void setImagefileContentType(String imagefileContentType) {
		this.imagefileContentType = imagefileContentType;
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



	/**
	 * ������Ƶ
	 * 
	 * @return
	 * @throws IOException
	 */
	public String savevideoinfo() throws IOException {
		// ���ύ��������ӵ����ݿ���.
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (user != null) {

			userupload.setUid(user.getUid());

			if (imagefile != null) {
				// ���ϴ�����������.
				// ��õķ�������·��.
				String path = ServletActionContext.getServletContext()
						.getRealPath("/uploadimage");
				// �����ļ����Ͷ���:
				File diskFile = new File(path + "//" + imagefileFileName);
				// �ļ��ϴ�:������ļ����Ƶ������ļ�
				FileUtils.copyFile(imagefile, diskFile);
				// �����ͼƬ��·��
				userupload.setImage("uploadimage/" + imagefileFileName);
			}

			if (uldaddrfile != null) {
				// ���ϴ�����������.
				// ����ϴ��ķ�������·��.
				String path = ServletActionContext.getServletContext()
						.getRealPath("/uploadvideo");
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
		return null;

	}

	// ɾ����Ƶ�ķ���:
	public String deletevideo() throws IOException {
		// ����id��ѯ��Ƶ��Ϣ
		Userupload useruploaddelete = useruploadinfoservice
				.findUseruploaByUld(userupload.getUld().toString());
		// ɾ��:
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + userupload.getUrl());
		File file = new File(path);
		file.delete();

		// ɾ�����ݿ��м�¼:
		useruploadinfoservice.deleteUseruploadVideoInfo(userupload);
		// ҳ����ת
		return "deleteSuccess";
	}

	// �༭��Ƶ�ķ���
	public String editvideoinfo() {

		// ����id��ѯ��Ƶ��Ϣ

		Userupload useruploadVideoInfo = useruploadinfoservice
				.findUseruploaByUld(userupload.getUld().toString());
		useruploadVideoInfo.setName(userupload.getName());

		useruploadVideoInfo.setDuration(userupload.getDuration());
		useruploadVideoInfo.setInfo(userupload.getInfo());
		useruploadVideoInfo.setClick(userupload.getClick());
		if (userupload.getStatus() == 1) {

			useruploadVideoInfo.setStatus(userupload.getStatus());
			// ����Ƶ��Ϣ�浽��Ƶ�����б�
			userUploadToVideoInfo(useruploadVideoInfo);

		}

		useruploadVideoInfo.setType(userupload.getType());
		useruploadinfoservice.updateUseruploadVideoInfo(useruploadVideoInfo);
		// ҳ����ת���༭ҳ��:
		return "editSuccess";
	}

	// ����Ƶ״̬�ı�ú���Ƶ�浽�����б�
	public void userUploadToVideoInfo(Userupload userupload) {

		Videoinfo videoinfo = new Videoinfo();
		videoinfo.setVidImage(userupload.getImage());
		System.out.println(userupload.getImage());
		videoinfo.setVidInfo(userupload.getInfo());
		videoinfo.setVidName(userupload.getName());

		videoinfo.setVidState("1");
		// videoinfo.setVidYear(userupload.getUpload().toString());
		useruploadinfoservice.addUseruploadToVideoInfo(videoinfo);

		Videocount videocount = new Videocount();
		videocount.setCouClick(0);
		videocount.setCouDuration(userupload.getDuration());
		videocount.setCouUpload(userupload.getUpload());
		videocount.setCouUrl(userupload.getUrl());
		videocount.setCouCount(1);
		videocount.setVideoinfo(videoinfo);
		useruploadinfoservice.addUseruploadToVideoCount(videocount);

	}

	// �޸���Ƶ�ķ���
	public String updatevideoinfo() throws IOException {

		// �ϴ�:
		if (imagefile != null) {
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + userupload.getImage());
			File file = new File(delPath);
			file.delete();
			// ����ϴ��ķ�������·��.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/uploadimage");
			// �����ļ����Ͷ���:
			File diskFile = new File(path + "//" + imagefileFileName);
		
			// �ļ��ϴ�:
			FileUtils.copyFile(imagefile, diskFile);

			userupload.setImage("uploadimage/" + imagefileFileName);
		}

		useruploadinfoservice.updateUseruploadVideoInfo(userupload);
		// ҳ����ת
		return "updateSuccess";

	}

	/**
	 * ����û��ϴ�����Ƶ,������Ƶ ��Ž��в��Һ��޸�
	 * 
	 * @return
	 */
	public String editUseruploadVideoInfo() {
		Userupload uploadVideoInfo = useruploadinfoservice
				.findUseruploaByUld(userupload.getUld().toString());

		ServletActionContext.getContext().getValueStack()
				.set("uploadVideoInfo", uploadVideoInfo);

		return "toedituseruploadlist";

	}

	/**
	 * ���û���Ų��Ҷ�Ӧ������
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
			return "userVideoInfo";
		}
		return null;

	}

	/**
	 * ����Ա����û��ϴ�����Ƶ��Ϣ
	 * 
	 * @return
	 */
	public String getAllUserVideoInfo() {

		PageBean<Userupload> pageBean = useruploadinfoservice
				.getUseruploadVideoInfo(page);
		System.out.println(pageBean.getList().get(0).getName()+"----------------------------------------------------------------");

		if (pageBean != null) {

			ServletActionContext.getContext().getValueStack()
					.set("userupload", pageBean);
			return "getalluservideoinfo";

		}
		return null;

	}

	@Override
	public Userupload getModel() {
		return userupload;
	}

}
