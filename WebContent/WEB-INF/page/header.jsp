<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default navbar-fixed-top">

	<div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Focus Image Gallery</a>
        </div>
		<div class="collapse navbar-collapse">
	        <ul class="nav navbar-nav navbar-right">
	          <c:if test="${not empty images}">
					<jsp:include page="gallery_upload.jsp"></jsp:include>
			    </c:if>
			    <c:if test="${empty images}">
					<li><a class="btn btn-default navbar-btn" href="gallery">Back</a></li>
			    </c:if>
	        </ul>
        </div>
      </div>
</nav>
