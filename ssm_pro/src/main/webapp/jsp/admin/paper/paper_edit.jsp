<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title>试题管理-编辑</title>
   
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/x-admin.css" media="all" />
   <script type="text/javascript">
        function modify() {
        	  var question_num=document.getElementById("questionNum").value;

        	  if(null==question_num || question_num==''){
        		  alert("请输入题号");
        		  return;
        	  }
        	  var form=document.getElementById("modifyform");
        	  form.submit();
        }

        function option_add() {
            window.location.href ="";
        }


</script>
</head>    
<body>
   <div class="x-body">
       <form action="updateSave" method="post">
		 <input type="hidden" name="id" value="${paper_info.id}"/>
			
                <div class="layui-form-item">
                    <label class="layui-form-label"> 题号 </label>
                    <div class="layui-input-inline">
                        <input type="text" name="questionNum" value="${paper_info.questionNum}" class="layui-input">
                    </div> 
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">题目</label>
                    <div class="layui-input-inline">
                        <input type="text" name="questionContent" value="${paper_info.questionContent}" class="layui-input">
                    </div> 
                </div>
               
                <div class="layui-form-item">
                    <label class="layui-form-label">得分 </label>
                    <div class="layui-input-inline">
                        <input type="text" name="questionScore" value="${paper_info.questionScore}" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">答案 </label>
                    <div class="layui-input-inline">
                    	<select name="questionAnswer" style="width: 80px">
						<option value="A" 
						<c:if test="${paper_info.questionAnswer eq 'A'}">
						selected="selected"
						</c:if>
						>A</option>
						<option value="B" 
						<c:if test="${paper_info.questionAnswer eq 'B'}">
						selected="selected"
						</c:if>
						>B</option>
						<option value="C"
						<c:if test="${paper_info.questionAnswer eq 'C'}">
						selected="selected"
						</c:if>
						>C</option>
						<option value="D" 
						<c:if test="${paper_info.questionAnswer eq 'D'}">
						selected="selected"
						</c:if>
						>D</option>
					</select>
            
                    </div>
                </div>

                <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label"> <span
                            class="x-red">*</span>本题选项
                    </label>

                    <div class="layui-form-item">
                        <label for="L_repass" class="layui-form-label"> </label>
                        <button onclick="option_add()" class="layui-btn">编辑选项</button>
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="L_sign" class="layui-form-label"></label>
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