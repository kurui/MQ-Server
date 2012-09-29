<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<html>
	<head>
		<title>消息服务平台</title>
		<link href="_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript"
			src="./_js/jquery-1.3.2.min.js"></script>
	</head>
	<body>
		<div id="header">
			<div class="logo">
				<!-- -->
				<img src="./_img/kurui/kuruiLogo.png" alt="消息服务平台" class="imgLogo" />
				<span class="sysTitle">消息服务平台</span>
			</div>
			<div class="mainNav">
				<ul class="navContent">
					<li>
						<a href="left.jsp" target="leftFrame">首页</a>
					</li>
					<li>
						<a href="message/left.jsp" target="leftFrame">消息管理</a>
					</li>
				</ul>
				<ul class="userPanel">
					<li>
						欢迎：
						<FONT color="red"> <c:out value="${URI.user.userName}" />
						</FONT>
					</li>
					<li>
						<a href="user/user.do?thisAction=exit">退出</a>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>
