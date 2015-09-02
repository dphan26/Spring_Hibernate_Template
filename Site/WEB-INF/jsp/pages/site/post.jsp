<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
<core:set var="contextPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post</title>
<script type='text/javascript' src='${contextPath}/dwr/engine.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/util.js'></script>
<script type='text/javascript' src='${contextPath}/dwr/interface/DwrPostServiceImpl.js'></script>
<script type='text/javascript' src='${contextPath}/js/post/post.js'></script>
</head>
<body>
	<ol id="results">
	</ol>
	<div class="animation_image" style="display: none" align="center">
		<img src="${pageContext.request.contextPath}/img/site/loadmore/ajax-loader.gif">
	</div>
</body>
</html>