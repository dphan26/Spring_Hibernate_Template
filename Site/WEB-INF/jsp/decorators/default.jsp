<%@ include file="/WEB-INF/jsp/include/site/taglibs_include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title><decorator:title /></title>
<core:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<decorator:head />
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="row">
				<%@ include file="/WEB-INF/jsp/include/site/header.jsp"%>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<decorator:body />
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<hr>
				<%@ include file="/WEB-INF/jsp/include/site/footer.jsp"%>
			</div>
		</div>
	</div>

	<!-- /.container -->

	<!-- jQuery Version 1.11.1 -->
	<script src="${contextPath}/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${contextPath}/js/bootstrap.min.js"></script>
</body>
</html>