<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<html>
	<head>
		<title>酷睿天下集团信息平台</title>
		<link href="_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="_js/jquery-1.3.1.min.js"></script>
		<script src="_js/common.js" type="text/javascript"></script>
<script>
	function isSimplePass(){
		var a = "<c:out value="${user.userPassword}"/>";
		if(a=="123456"){
			alert("您的密码过于简单，请修改");
			document.location.href="user/user.do?thisAction=editMyPassword&userId=<c:out value='${URI.user.userId}'/>";
		}
	}
</script>
	</head>
	<body onload = "isSimplePass();">
		<div id="mainContainer">
			<div id="container">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr>						 
						<td valign="top" colspan="3">
							<c:import url="_jsp/mainTitle.jsp" charEncoding="UTF-8">
								<c:param name="title1" value="系统公告" />
							</c:import>
						</td>
					</tr>
					<tr>
						<td  colspan="3">
							<p>
								里程碑
							</p>
							<p>
							1、Common JMS
							</p>
							<p>
							2、JBOSS MQ
							   ACTIVE MQ
							   WEBSPERE MQ
							</p>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>
