<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<core:set var="contextPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Ajax</title>

</head>
<body>
	<h1>Demo Ajax</h1>
	<input id="num1" type="text"/> +
	<input id="num2" type="text"/>
	<input type="submit" value="Add" onclick="add()"/>
	Sum: <span id="sum">Result show here</span>
	
	
	<script type="text/javascript">
	function add() {
		// Retrieve value of text inputs
		var num1 = dwr.util.getValue("num1");
		var num2 = dwr.util.getValue("num2");
		
		// Pass two numbers, a callback function, and error function
		dwrService.add(num1, num2, {
			callback : handleAddSuccess,
			errorHandler : handleAddError
		});
	}

	// data contains the returned value
	function handleAddSuccess(data) {
		// Assigns data to result id
		dwr.util.setValue("sum", data);
	}

	function handleAddError() {
		// Show a popup message
		alert("We can't add those values!");
	}

	</script>
	
</body>
</html>