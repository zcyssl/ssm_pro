<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title>选项管理-编辑</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/x-admin.css" media="all">
   <script type="text/javascript">
function modify() {
	  var option_content=document.getElementById("optionContent").value;

	  if(null==option_content || option_content==''){
		  alert("请输入选项内容");
		  return;
	  }
	  var form=document.getElementById("modifyform");
	  form.submit();
}
</script>
</head>    
<body>
   <div class="x-body">
       <form action="update_save" method="post">

		 <input type="hidden" name="id" value="${option_info.id}"/>
			
                <div class="layui-form-item">
                    <label class="layui-form-label">题号 </label>
                    <div class="layui-input-inline layui-input">
                    	${option_info.questionNumId}
                        <input type="hidden" name="questionNumId" value="${option_info.questionNumId}">
                    </div> 
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选项 </label>
                    <div class="layui-input-inline layui-input">
                    ${option_info.optionType}
                        <input type="hidden" name="optionType" value="${option_info.optionType}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选项内容</label>
                    <div class="layui-input-inline">
                        <input type="text" name="optionContent" id="optionContent" value="${option_info.optionContent}" class="layui-input">
                    </div> 
                </div>
                <div class="layui-form-item">
<%--                    <label for="L_sign" class="layui-form-label"></label>--%>
                    <button class="layui-btn" key="set-mine" onclick="javascript:modify()"> 提交</button>
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="${pageContext.request.contextPath}/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer; 
            });
        </script>
    </body>

</html>