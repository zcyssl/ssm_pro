<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新生信息化能力评价系统登录界面</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery2.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<script language="javascript" src="js/js.js"></script>
<script type="text/javascript">
function login() {
	  var user_name=document.getElementById("studentNum").value;
	  var user_pass=document.getElementById("studentPassword").value;
	  if(null==user_name || user_name==''){
		  alert("请输入学号");
		  return;
	  }
	  if(null==user_pass || user_pass==''){
		  alert("请输入密码 ");
		  return;
	  }
	  var form=document.getElementById("login_form");
	  form.submit();
}
</script>
</head>
<body style="background-color:#1c77ac; background-image: url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

<form name="studentlogin" id="login_form" action="${pageContext.request.contextPath}/student_login" method="post">
<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop"> <span>欢迎登录新生信息化能力评价系统</span>
</div>
<div class="loginbody"> <span class="systemlogo"></span>
  <div class="loginbox">
  
    <ul>
      <li>
      ${tip_info}
        <input name="studentNum" id="studentNum" type="text" class="loginuser" value="学号" onclick="JavaScript:this.value=''"/>
      </li>
      <li>
        <input name="studentPassword" id="studentPassword" type="password" class="loginpwd" value="" onclick="JavaScript:this.value=''"/>
      </li>
      <li>
        <input name="" type="button" class="loginbtn" value="登录"  onclick="javascript:login()"/>
        <!-- <label><a href="#">注册</a></label>  -->
      </li>
    </ul>
  </div>
</div>
<div class="loginbm">版权所有：  学佳澳软件  © Copyright 2015-2019.</div>
</form>
</body>
</html>
