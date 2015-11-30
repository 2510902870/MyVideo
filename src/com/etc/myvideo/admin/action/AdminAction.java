package com.etc.myvideo.admin.action;

import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.admin.entity.Admin;
import com.etc.myvideo.admin.service.AdminService;
import com.etc.myvideo.admin.service.IAdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends ActionSupport implements ModelDriven<Admin> {

	private Admin admin = new Admin();

	private IAdminService adminService;

	private String userName;
	private String password;
	private String email;
	private String phone;
	private String state;
	private String checkcode;

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public Admin getModel() {
		return admin;
	}

	public String login() {
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!getCheckcode().equalsIgnoreCase(checkcode1)) {
			this.addActionMessage("��֤����д����");
			return "fail";

		}
		Admin adminLogin = adminService.login(admin);
		if (adminLogin == null) {
			// ��¼ʧ��
			this.addActionError("��¼ʧ��:�û��������������û�δ����!");
			return "fail";
		} else {
			// ��¼�ɹ�
			// ���û�����Ϣ���뵽session��
			ServletActionContext.getRequest().getSession().setAttribute("adminLogin",
					adminLogin);

			// ҳ����ת
			return "toadmainframe";
		}

	}

	/**
	 * �û��˳��ķ���
	 */
	public String quit() {
		// ����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	/**
	 * ����Ա��Ϣ�޸�
	 * @return
	 */
	public String update(){
		Admin adm = (Admin) ServletActionContext.getRequest().getSession().getAttribute("adminLogin");
		admin.setPassword(adm.getPassword());
		adminService.update(admin);
		ServletActionContext.getRequest().getSession().setAttribute("adminLogin", admin);
		return "admininfo";
	}
}
