<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Gallery</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	<div class="sidebar navbar-default container">
		<jsp:include page="chat_info.jsp"/>
	</div>
	<div class="content">
		<jsp:include page="chat_message.jsp"/>
	</div>
	<div class="preview">
		<img src="<c:out value='${galleryPath}'/>/<c:out value='${room.image.realName}'/>">
	</div>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/focus.chat.js"></script>
</body>
</html>