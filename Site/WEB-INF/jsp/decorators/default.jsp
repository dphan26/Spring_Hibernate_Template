<%@ include file="/WEB-INF/jsp/include/taglibs_include.jsp"%>
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
<decorator:head />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<decorator:body />
			</div>
		</div>
	</div>
</body>
</html>