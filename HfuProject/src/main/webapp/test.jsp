<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--引入JSTL核心標記庫的taglib指令-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>顯示留言</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<a href="success.jsp">返回</a>
	<table border="1">
		<tr>
			<th width="150">留言數</th>
			<th width="150">主題</th>
			<th width="150">內容</th>
			<th width="150">留言時間</th>
			<th width="150">留言人</th>
			<th width="150">刪除選項</th>
		</tr>
		<c:forEach items="${requestScope.messages}" var="message">
			<tr>
				<td width="100">${message.messageId}</td>
				<td width="100">${message.title}</td>
				<td width="500">${message.content}</td>
				<td width="200">${message.time}</td>
				<td width="100">${message.userName}</td>
				<td width="100">
					<form action="MessageServlet?status=deleteMessage" method="post">
						<input type="hidden" value="${message.messageId}" name="messageId">
						<input type="submit"  value="刪除" onclick="return confirm('確定刪除嗎？')">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	
	<center>
	<div>
		第${requestScope.currentPage}頁/共${requestScope.countPage}頁 <a
			href="${pageContext.request.contextPath}/MessageServlet?status=getMessage&currenttPage=1">首頁</a><span> </span>
		<c:choose>
			<c:when test="${requestScope.currentPage==1}">
				上一頁
			</c:when>
			<c:otherwise>
				<a
					href="${pageContext.request.contextPath}/MessageServlet?status=getMessage<span style="font-family: Arial, Helvetica, sans-serif;">&currenttPage</span>=${requestScope.currentPage-1}">上一頁</a>
			</c:otherwise>
		</c:choose>
		<%--計算begin和end --%>
		<c:choose>
			<%--如果總頁數不足10，那麼就把所有的頁都顯示出來 --%>
			<c:when test="${requestScope.countPage<=10}">
				<c:set var="begin" value="1" />
				<c:set var="end" value="${requestScope.countPage}" />
			</c:when>
			<c:otherwise>
				<%--如果總頁數大於10，通過公式計算出begin和end --%>
				<c:set var="begin" value="${requestScope.currentPage-5}" />
				<c:set var="end" value="${requestScope.currentPage+4}" />
				<%--頭溢位 --%>
				<c:if test="${begin<1}">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="10"></c:set>
				</c:if>
				<%--尾溢位 --%>
				<c:if test="${end>requestScope.countPage}">
					<c:set var="begin" value="${requestScope.countPage - 9}"></c:set>
					<c:set var="end" value="${requestScope.countPage}"></c:set>
				</c:if>
			</c:otherwise>
		</c:choose>
		<%--迴圈顯示頁碼列表 --%>
		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i == requestScope.currentPage}">
				[${i}]
				</c:when>
				<c:otherwise>
					<a href="<c:url value ='/MessageServlet?status=getMessage
					&currentPage=${i}'/>">[${i}]</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${requestScope.currentPage==requestScope.countPage}">
				  下一頁
			</c:when>
			<c:otherwise>
				<a
					href="${pageContext.request.contextPath}/MessageServlet?status=getMessage&currentPage=${requestScope.currentPage+1}"> 下一頁</a>
			</c:otherwise>
		</c:choose>
		<span> </span><a
			href="${pageContext.request.contextPath}/MessageServlet?status=getMessage&currentPage=${requestScope.countPage}">尾頁</a>
	</div>
</center>
</body>
</html>