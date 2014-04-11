<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<h3><c:out value='${room.image.name}'/></h3>
<div class="thumbnail row-centered"> 
	<div>
		<img src="<c:out value='${galleryPath}'/>/<c:out value='${room.image.realName}'/>">
	</div>
</div>
<p>
	<fmt:formatNumber type="number" groupingUsed="false" value="${room.image.resolution.width}"/> x <fmt:formatNumber type="number" groupingUsed="false" value="${room.image.resolution.height}"/>
	&nbsp;
	<c:out value='${room.image.readableSize}'/>
</p>
<p>
	<fmt:formatDate pattern="yyyy-MM-dd" value="${room.image.lastModifiedDate}" />
</p>
<div>
	<h3>Preferences</h3>
	<div class="me">
		<span>My Name:</span>
		<input type="text" class="name" placeholder="<c:out value='${participant.name}'/>" value="<c:out value='${participant.name}'/>"/>
	</div>
</div>
<div>
	<h3>Participants</h3>
	<div class="participants">
		<ol>
		</ol>
	</div>
</div>