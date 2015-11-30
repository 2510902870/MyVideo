package com.etc.myvideo.user.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.user.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	// ģ������ʹ�õĶ���
	private User user = new User();
	private String email; // ������
	private String password;// ����
	private String userName;// �û���
	private int accumulate;// ����
	private String sex;// �Ա�
	private int level;// �ȼ�
	private String nickName;
	private String birthday;
	private String checkcode;
	
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	private static String PAGE;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAccumulate() {
		return accumulate;
	}

	public void setAccumulate(int accumulate) {
		this.accumulate = accumulate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public IUserService getUserService() {
		return userService;
	}


	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	// ע��UserService
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * AJAX�����첽У���û�����ִ�з���
	 * 
	 * @throws IOException
	 */
	public String findByName() throws IOException {
		// ����Service���в�ѯ:
		User existUser = userService.findByUsername(getUserName());
		// ���response����,��ҳ�����:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// �ж�
		if (existUser != null) {
			// ��ѯ�����û�:�û����Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		} else {
			// û��ѯ�����û�:�û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}

	/**
	 * �û�ע��ķ���:
	 */
	public String register() {

		// ��session�л����֤������ֵ:
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");

		// User user = new User();
		user.setuMail(getEmail());
		user.setuPassword(getPassword());
		user.setuName(getNickName());
		user.setuSex(getSex());
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = fmt.parse(getBirthday());
			user.setuBirthday(date); 
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		if (!getCheckcode().equalsIgnoreCase(checkcode1)) {
			this.addActionError("��֤���������!");
			return "fail";
		}

		userService.save(user);
		this.addActionMessage("ע��ɹ�!��ȥ���伤��!");
		return "msg";

	}

	/**
	 * �û�����ķ���
	 */
	public String active() {
		// ���ݼ������ѯ�û�:
		User existUser = userService.findByCode(user.getCode());
		// �ж�
		if (existUser == null) {
			// ����������
			this.addActionMessage("����ʧ��:���������!");
		} else {
			// ����ɹ�
			// �޸��û���״̬
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("����ɹ�:��ȥ��¼!");
		}
		return "msg";
	}

	/**
	 * ��ת��ע��ҳ���ִ�з���
	 */
	public String registPage() {
		return "registPage";
	}

	/**
	 * ��ת����¼ҳ��
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * ��¼�ķ���
	 */
	public String login() {
       
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!getCheckcode().equalsIgnoreCase(checkcode1)) {
			
			ServletActionContext.getRequest().getSession().setAttribute("result", false);
			ServletActionContext.getRequest().getSession().setAttribute("msg", "��֤�����");
			return "fail";
			

		} 
		user.setuMail(getEmail());
		user.setuPassword(getPassword());
		User existUser = userService.login(user);
		// �ж�
		if (existUser == null) {
			// ��¼ʧ��
			ServletActionContext.getRequest().getSession().setAttribute("result", false);
			ServletActionContext.getRequest().getSession().setAttribute("msg", "�û��������������û�δ���");
			PAGE="login";
			return PAGE;
		} else {
			// ��¼�ɹ�
			// ���û�����Ϣ���뵽session��
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			// ҳ����ת
			PAGE="loginSuccess";
			ServletActionContext.getRequest().getSession().removeAttribute("result");
			ServletActionContext.getRequest().getSession().removeAttribute("msg");
			return PAGE;
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

	public void isExitUserEmail() {
		String flag = ServletActionContext.getRequest().getParameter("flag");

		String email = ServletActionContext.getRequest().getParameter("uMail");
		
		String name=ServletActionContext.getRequest().getParameter("nickName");
		

		AjaxServerRegister(email, flag);

	}

	private void AjaxServerRegister(String email, String flag) {
		User userEmail = userService.findByEmail(email);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			// ע��
			if (flag.equals(0)) {
				// ˵�����ڸ��û�
				if (userEmail != null) {

					response.getWriter().println(
							"<font color='red'>�����Ѿ�����</font>");
				}
				// ˵���������û����������ע��
				else {
					response.getWriter().println(
							"<font color='green'>�������ʹ��</font>");
				}

			}
			// �û���¼
			else if (flag.equals(1))

			{
				if (userEmail != null) {

					response.getWriter()
							.println(
									"<a id='lab1' style='font-size: 8px;text-decoration: none;'href='register.jsp'>ע���˺�</a>");
				}
				// ˵���������û����������ע��
				else {

					response.getWriter().println(
							"<font color='blue'>������ڣ����¼</font>");
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * AJAXlogin��¼�ķ���
	 * 
	 * @throws Exception
	 */
	public String AJAXlogin() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(user.getuPassword() + " �����û�����");
		String email = ServletActionContext.getRequest().getParameter("uMail");
		String pass = ServletActionContext.getRequest().getParameter(
				"uPassword");
		user.setuMail(email);
		user.setuPassword(pass);
		User existUser = userService.login(user);
		// �ж�
		if (existUser == null) {

			response.getWriter().print("0"); //
		} else {
			// ��¼�ɹ�
			// ���û�����Ϣ���뵽session��
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			response.getWriter().print("1");
		}
		return NONE;

	}

	@Override
	public User getModel() {
		return user;
	}

	/**
	 * �û�������Ϣ�鿴 gao
	 * @return
	 */
	public String findUserInfo(){

		return "personInfo";
	}

	/**
	 * �û�������Ϣ�޸�
	 * gao
	 * @return
	 * @throws ParseException
	 */

	public String update() throws ParseException {
		String name = ServletActionContext.getRequest().getParameter("uName");
		String nickName = ServletActionContext.getRequest().getParameter(
				"uNickName");
		String sex = ServletActionContext.getRequest().getParameter("uSex");
		String birthday = ServletActionContext.getRequest().getParameter(
				"uBirthday");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(birthday);
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		user.setuName(name);
		user.setuNickName(nickName);
		user.setuSex(sex);
		user.setuBirthday(date);

		userService.update(user);
		int userId = user.getUid();
		User pUser = userService.findByUserId(userId);
		ServletActionContext.getRequest().getSession().setAttribute("existUser", pUser);
		
		return "personInfo";
	}
	
	/**
	 * ת���������ҳ
	 * @return
	 */
	public String tonewpass(){
		User findUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("finduser");
		
		
		return "newpassword";
	}
    /**
     * �ҵ��������û�
     * @return
     */
	public String findpassactive() {

		// ���ݼ������ѯ�û�:
		User existUser = userService.findByCode(user.getCode());
		// �ж�
		if (existUser == null) {
			// ����������
			System.out.println("û�и��û�");
		} else {
			// ����ɹ�
			// �޸��û���״̬
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			ServletActionContext.getRequest().getSession()
					.setAttribute("finduser", existUser);
		}
		return "tonewpass";

	}
    /**
     * �ҵ�����
     * @return
     */
	public String findpassword() {

		User findUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("finduser");
		findUser.setuPassword(getPassword());
		userService.update(findUser);
		return "login";

	}
    /**
     * ת������
     * @return
     */
	public String findPassToEmail() {

		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		String checkCode2 = ServletActionContext.getRequest().getParameter(
				"checkcode");

		if (!checkCode2.equalsIgnoreCase(checkcode1)) {
			return "findError";

		}
		System.out.println(getEmail());
		user.setuMail(getEmail());
		User findEmail = userService.findByEmail(email);
		if (findEmail != null) {
			userService.findPass(findEmail);
			return "tofindpass";
		}
		return null;

	}
}
