package com.etc.myvideo.user.action;

import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.user.service.IUserService;
import com.etc.myvideo.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	
	@Override
	public User getModel() {
		
		return user;
	}
	
	private IUserService userService;
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IUserService getUserService() {
		return userService;
	}
	
	//ҳ��
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
	//��ҳ��ѯ�û�
	public String findAllUser(){
		
		PageBean<User> userList = userService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", userList);
//		ServletActionContext.getRequest().getSession().setAttribute("userList", userList);
		return "adminFindAllUser";
	}
	
//	//�û���ϸ��Ϣ
//	public String userDetail(){
//		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("uid"));
//		
//		
//		ServletActionContext.getRequest().getSession().setAttribute("detailId", id);
//		return "userDetail";	
//	}
//	
	//ɾ���û�
//	public String deleteUser(){
//		int id = user.getUid();
//		userService.delete(id);
//		return "adminFindAllUser";
//	}

}
