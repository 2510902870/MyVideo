<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style2.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
function addvideoinfo(){
	window.location.href = "${pageContext.request.contextPath}/adminvideoinfo_toaddview.action";
}
			function showDetail(oid){
				var but = document.getElementById("but"+oid);
				var div1 = document.getElementById("div"+oid);
				if(but.value == "视频详情"){
					// 1.创建异步对象
					var xhr = createXmlHttp();
					// 2.设置监听
					xhr.onreadystatechange = function(){
						if(xhr.readyState == 4){
							if(xhr.status == 200){
								
								div1.innerHTML = xhr.responseText;
							}
						}
					}
					// 3.打开连接
					xhr.open("GET","${pageContext.request.contextPath}/adminOrder_findOrderItem.action?oid="+oid+"&time="+new Date().getTime(),true);
					// 4.发送
					xhr.send(null);
					but.value = "关闭";
				}else{
					div1.innerHTML = "";
					but.value="视频详情";
				}
				
			}
			function createXmlHttp(){
				   var xmlHttp;
				   try{ // Firefox, Opera 8.0+, Safari
				        xmlHttp=new XMLHttpRequest();
				    }
				    catch (e){
					   try{// Internet Explorer
					         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
					      }
					    catch (e){
					      try{
					         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					      }
					      catch (e){}
					      }
				    }

					return xmlHttp;
				 }
		</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			class="ta" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><h1>
							<strong>管理员视频列表</strong>
						</h1></TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr scrolling="auto"
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">序号</td>
								<td align="center" width="10%">用户编号</td>
								<td align="center" width="10%">视频编号</td>
								<td align="center" width="10%">视频名称</td>
								<td align="center" width="10%">视频类型</td>
								<td align="center" width="10%">视频时长</td>
								<td align="center" width="10%">上传时间</td>
								<td align="center" width="10%">视频简介</td>
								<td align="center" width="10%">点击数</td>
								<td align="center" width="10%">状态</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">删除</td>
								<td align="center" width="10%">视频详情</td>
							</tr>
							<s:iterator var="v" value="userupload.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%"><s:property value="#status.count" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.uid" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.uld" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.type" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.duration" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.upload" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.info" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#v.click" /></td>
									<s:if test="#v.status==1">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">审核通过</td>
									</s:if>
									<s:elseif test="#v.status==2">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">审核未通过</td>
									</s:elseif>
									<s:else>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">待审核</td>
									</s:else>

									<td align="center" style="HEIGHT: 22px"><a
										href="${ pageContext.request.contextPath }/adminUserupload_editUseruploadVideoInfo.action?uld=<s:property value="#v.uld"/>">
											<img src="${pageContext.request.contextPath}/img/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>

									<td align="center" style="HEIGHT: 22px"><a
										href="${ pageContext.request.contextPath }/adminUserupload_deletevideo.action?uld=<s:property value="#v.uld"/>">
											<img src="${pageContext.request.contextPath}/img/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a></td>
									<td align="center" style="HEIGHT: 22px"><input
										type="button" value="视频详情"
										id="but<s:property value="#v.vidId"/>"
										onclick="showDetail(<s:property value="#v.vidId"/>)" />
										<div id="div<s:property value="#v.vidId"/>"></div></td>

								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td colspan="7">第<s:property value="userupload.page" />/<s:property
							value="userupload.totalPage" />页 <s:if test="userupload.page >1">
							<a
								href="${ pageContext.request.contextPath }/adminUserupload_getAllUserVideoInfo.action?page=1">首页</a>|
								<a
								href="${ pageContext.request.contextPath }/adminUserupload_getAllUserVideoInfo.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if> <s:if test="pageBean.page != pageBean.totalPage">
							<a
								href="${ pageContext.request.contextPath }/adminUserupload_getAllUserVideoInfo.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a
								href="${ pageContext.request.contextPath }/adminUserupload_getAllUserVideoInfo.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

