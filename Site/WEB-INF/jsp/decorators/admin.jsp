<%@ include file="/WEB-INF/jsp/include/admin/taglibs_include.jsp"%>
<core:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>CoffeeWeb | Dashboard</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="description" content="">
    <meta name="author" content="">
    <title><decorator:title /></title>
    <!-- Bootstrap 3.3.2 -->
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />    
    <!-- FontAwesome 4.3.0 -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons 2.0.0 -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />   
    <!-- Theme style -->
    <link href="${contextPath}/css/admin/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="${contextPath}/css/admin/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <decorator:head />
  </head>
<!-- Begin -->
<body class="skin-blue sidebar-mini">
    <div class="wrapper">
        <%@ include file="/WEB-INF/jsp/include/admin/header.jsp"%>
        <%@ include file="/WEB-INF/jsp/include/admin/sidebar.jsp"%>
		<decorator:body />
      <%@ include file="/WEB-INF/jsp/include/admin/footer.jsp"%>
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class='control-sidebar-bg'></div>
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.3 -->
    <script src="${contextPath}/js/admin/jQuery-2.1.3.min.js"></script>
    <!-- jQuery UI 1.11.2 -->
    <script src="${contextPath}/js/admin/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>    
    <!-- Morris.js charts -->
    <script src="${contextPath}/js/admin/raphael-min.js"></script>
    <script src="${contextPath}/js/admin/morris/morris.min.js" type="text/javascript"></script>
    <!-- Sparkline -->
    <script src="${contextPath}/js/admin/jquery.sparkline.min.js" type="text/javascript"></script>
    <!-- jvectormap -->
    <script src="${contextPath}/js/admin/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
    <script src="${contextPath}/js/admin/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
    <!-- jQuery Knob Chart -->
    <script src="${contextPath}/js/admin/jquery.knob.js" type="text/javascript"></script>
    <!-- daterangepicker -->
    <script src="${contextPath}/js/admin/moment.min.js" type="text/javascript"></script>
    <script src="${contextPath}/js/admin/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <!-- datepicker -->
    <script src="${contextPath}/js/admin/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="${contextPath}/js/admin/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
    <!-- Slimscroll -->
    <script src="${contextPath}/js/admin/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src="${contextPath}/js/admin/fastclick/fastclick.min.js"></script>
    
    
    <!-- AdminLTE App -->
    <script src="${contextPath}/js/admin/app.min.js" type="text/javascript"></script>    
    
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="${contextPath}/js/admin/pages/dashboard.js" type="text/javascript"></script>    
    
    <!-- AdminLTE for demo purposes -->
    <script src="${contextPath}/js/admin/demo.js" type="text/javascript"></script>
  </body>
<!-- End -->
</html>