package com.etc.myvideo.comment.action;


import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;






import javax.servlet.http.HttpServletResponse;






import org.apache.struts2.ServletActionContext;






import com.etc.myvideo.comment.entity.Comment;
import com.etc.myvideo.comment.service.Commentservice;
import com.etc.myvideo.user.entity.User;
import com.etc.myvideo.util.PageBean;
import com.etc.myvideo.videocount.entity.Videocount;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class Commentaction extends ActionSupport implements ModelDriven<Comment> {

	private Comment comment = new Comment();

	private Commentservice commentservice;

	public void setCommentservice(Commentservice commentservice) {
		this.commentservice = commentservice;
	}

	// ���յ�ǰҳ��:
	private int page;

	// ����vidId
	private Integer couId;
	private Integer uid;
	

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	

	public Integer getCouId() {
		return couId;
	}

	public void setCouId(Integer couId) {
		this.couId = couId;
	}

	// ���ݷ�couId��ѯ����:
	public String findByPagecouId() throws Exception  {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		StringBuffer sb=new StringBuffer();
		
		PageBean<Comment> pageBean = commentservice.findByPagecouId(couId, page);// ,����ҳ��ѯ
	
		for (Comment comment:pageBean.getList()) {
	
			
			
			
			String str = "�û���:&nbsp;&nbsp;" + comment.getUser().getuName()+"&nbsp;&nbsp;&nbsp;"+"<font color='red'>"+comment.getComSubtance()+"</font>"+ "<br>";
			sb.append(str);
		}
		
      
	
		
		response.getWriter().print(sb.toString()); //
		return NONE;
	}
	
	
	// �������۵ķ���:
	public String savecomment() throws Exception {
		// ���ύ��������ӵ����ݿ���.
		// ���response����,��ҳ�����:
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				User user=new User();
				user.setUid(uid);
				Videocount videocount=new Videocount();
				videocount.setCouId(couId);
				
		comment.setTime(new Date());
		comment.setUser(user);
		comment.setVideocount(videocount);
	comment.setVideocount(videocount);
		comment.setComSubtance(URLDecoder.decode(comment.getComSubtance(), "utf-8"));
	
		commentservice.savecomment(comment);
		response.getWriter().print(1);
		return NONE;
		
	}
	
	//��ѯ����
	public String chadianzan() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		Comment curr_comment=commentservice.chadianzan(uid, couId);
		if (curr_comment!=null) {
			
			response.getWriter().print(-1);
		} else {
			User user=new User();
			user.setUid(uid);
			Videocount videocount=new Videocount();
			videocount.setCouId(couId);
			comment.setVideocount(videocount);
			comment.setTime(new Date());
			comment.setUser(user);
			comment.setIsdianzan(1);
			commentservice.savecomment(comment);
			
			int i=commentservice.findallzan(couId);
			
			response.getWriter().print(i);
		}
		
		return NONE;
		
	}
	
	
	
	
	

	@Override
	public Comment getModel() {

		return comment;
	}

}
