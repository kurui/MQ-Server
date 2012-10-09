<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>消息</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script>
    function executeBaseCase(method) { 
    	document.forms[0].method.value=method;
       	document.forms[0].thisAction.value="executeBaseCase";    	
       	document.forms[0].submit();
    }
    </script>
	</head>
	<body>
		<html:form action="/message/message.do" method="post">
			<html:hidden property="thisAction" />
			<html:hidden property="method" />
			<div id="mainContainer">
				<div id="container">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">

								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											目标地址
										</td>
										<td style="text-align: left">
											<html:text property="providerIp" value="192.168.150.1" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											消息内容
										</td>
										<td style="text-align: left">
											<html:text property="messageText" />
										</td>
									</tr>
									<tr>
										<td class="lef">
											Topic消息
										</td>
										<td style="text-align: left">

											<input name="label" type="button" class="button1" value="发布"
												onclick="executeBaseCase('topicPublisher');">
											<input name="label" type="button" class="button1" value="监听"
												onclick="executeBaseCase('topicSublisher');">

										</td>
									</tr>
									<tr>
										<td class="lef">
											Queue消息
										</td>
										<td style="text-align: left">
											<input name="label" type="button" class="button1" value="发布"
												onclick="executeBaseCase('queuePublisher');">
											<input name="label" type="button" class="button1" value="监听"
												onclick="executeBaseCase('queueSublisher');">
										</td>
									</tr>

								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</html:form>
	</body>
</html>