<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
</head>
<body>
	<script type="text/javascript">
		document.location.href = "login.jsp";
	</script>
</body>
</html>