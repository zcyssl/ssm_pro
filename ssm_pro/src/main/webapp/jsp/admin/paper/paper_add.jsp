<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加试题</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/x-admin.css" media="all">

</head>
<body>
	<div class="x-body">
		<form action="addSave" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label"> <span class="x-red">*</span>题号
				</label>
				<div class="layui-input-inline">
					<input type="text" id="questionNum" name="questionNum"
						class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">6到16个字符</div>
			</div>
			<div class="layui-form-item">
				<label for="L_email" class="layui-form-label"> <span
					class="x-red">*</span>题目
				</label>
				<div class="layui-input-inline">
					<input type="text" name="questionContent" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
					<span class="x-red">*</span>必填
				</div>
			</div>


			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> <span
					class="x-red">*</span>答案
				</label>
				<div class="layui-input-inline">
					<select name="questionAnswer" style="width: 80px">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_username" class="layui-form-label"> <span
					class="x-red">*</span>分数
				</label>
				<div class="layui-input-inline">
					<input type="text" id="questionScore" name="questionScore"
						class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label for="L_repass" class="layui-form-label"> </label>
				<button class="layui-btn">添加</button>
			</div>

		</form>

	</div>
	<script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8">
		
	</script>
	<script src="${pageContext.request.contextPath}/js/x-layui.js" charset="utf-8">
		
	</script>
</body>

</html>