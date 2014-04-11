<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container main">
	<div class="row row-centered">
		<c:forEach items="${requestScope.images}" var="image">
			<div class="col-xs-3 thumbnail-box col-centered">
				<div class="thumbnail-status">
					<span class="glyphicon glyphicon-user"></span>${fn:length(image.chatRoom.participants)}
						&nbsp;
					<span class="glyphicon glyphicon-comment"></span>${fn:length(image.chatRoom.messages)}
				</div>
				<a class="thumbnail" href="chat?image=<c:out value='${image.realName}'/>"> 
					<img src="<c:out value='${requestScope.galleryPath}'/>/<c:out value='${image.realName}'/>">
					<div class="caption">
						<span title="<c:out value='${image.name}'/>"><h4><c:out value='${image.name}'/></h4></span>
						<p>
							<fmt:formatNumber type="number" groupingUsed="false" value="${image.resolution.width}"/> x <fmt:formatNumber type="number" groupingUsed="false" value="${image.resolution.height}"/>
							&nbsp;
							<c:out value='${image.readableSize}'/>
						</p>
					</div> 
				</a> 
			</div>
		</c:forEach>
	</div>
</div>
