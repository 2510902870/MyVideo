package com.etc.myvideo.switchimage.entity;
//--������ͼƬȴ����
public class SwitchImage {
	private int  sId ;			  //--����������Զ�����	
	private String sAddr;		  // --ͼƬ��ַ
	private String	stype;		  // --ӰƬ  ��  ���
	private String	vidName;	  // --ӰƬ��    ��   �����
	private String	sText;			//	 --��ʾ�ı� 
	private String	sUrl;	 		//  --���ӵ�ַ
	private Integer	couId;	 		//  --���ӵ�ַ
	
	
	public Integer getCouId() {
		return couId;
	}
	public void setCouId(Integer couId) {
		this.couId = couId;
	}
	@Override
	public String toString() {
		return "SwitchImage [sId=" + sId + ", sAddr=" + sAddr + ", stype="
				+ stype + ", vidName=" + vidName + ", sText=" + sText
				+ ", sUrl=" + sUrl + "]";
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsAddr() {
		return sAddr;
	}
	public void setsAddr(String sAddr) {
		this.sAddr = sAddr;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getVidName() {
		return vidName;
	}
	public void setVidName(String vidName) {
		this.vidName = vidName;
	}
	public String getsText() {
		return sText;
	}
	public void setsText(String sText) {
		this.sText = sText;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	
	
	
}
