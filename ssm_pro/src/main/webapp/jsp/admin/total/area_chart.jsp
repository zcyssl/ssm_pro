<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.xja.util.*, java.io.*,java.nio.charset.*"%>
<%
AreaChartUtil util=new AreaChartUtil();
	String filename = util.generateBarChart(session,
			new PrintWriter(out));
	String graphURL = request.getContextPath()
			+ "/DisplayChart?filename=" + filename;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生成绩统计图</title>
</head>
<body leftmargin=10 topmargin=10>
	<img src="<%=graphURL%>" width=900 height=450 border=0>
</body>
</html>