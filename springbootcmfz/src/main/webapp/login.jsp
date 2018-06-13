<%@page  isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="script/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <%--<script type="text/javascript" src="script/jquery.js"></script>--%>
    <script type="text/javascript">

        $(function () {
            //初始化文本框
            $("#unameforlogin").textbox({
                iconCls:'icon-man',
                iconAlign:'right',
                type:"text",
                required:true,
                validateOnBlur:true
            });

            $("#upassforlogin").textbox({
                iconCls:'icon-lock',
                iconAlign:'right',
                type:"password",
                required:true,
                validateOnBlur:true,
                validType:['length[6,12]']
            });

            $("#enCode").textbox({
                required:true,
                validateOnBlur:true,
                validType:['length[4,4]']
            });
            //初始化form表单
            $("#loginForm").form({
                onSubmit:function(param){
                    return $("#loginForm").form("validate");
                },
                success:function(data){
                    var map=eval("("+data+")");
                    alert(map.message);
                    if(map.message.indexOf("成功")!=-1){
                        location.href="${pageContext.request.contextPath}/main/main.jsp";
                    };

                },
                url:"${pageContext.request.contextPath}/manager/login"
            });


            //点击更换验证码：
            $("#captchaImage").click(function () {//点击更换验证码
                alert("自己做");
            });

            //  form 表单提交
            $("#loginbutton").bind("click",function(){
                $("#loginForm").form("submit");
            });
        });
    </script>
</head>
<body>

<div class="login">
    <form id="loginForm"  method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input id="unameforlogin"  name="username" class="text"  maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input id="upassforlogin" name="pass" class="text"  maxlength="20"
                           autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="captchaImage" class="captchaImage" src="img/captcha.jpg" title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value=""
                           onclick="location.href='${pageContext.request.contextPath}/main/main.jsp'"><input id="loginbutton" type="button" class="loginButton" value="登录">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>