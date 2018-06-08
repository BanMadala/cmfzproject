<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/IconExtension.css">
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echars/echarts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echars/dark.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/echars/china.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        //退出功能
        function exit() {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/manager/existMS",
                success: function (data) {
                    alert(data.message);
                    location.href = "${pageContext.request.contextPath}/login.jsp";
                },
                dataType: "json"
            });
        }


        //添加一个全局变量，用来收集当前表格所选的索引
        var index;
        var rowdata;

        //添加面板
        function addMyTab(title, url, iconCls) {
            if ($("#tt").tabs("exists", title)) {
                $("#tt").tabs("select", title);
            } else {
                $('#tt').tabs('add', {
                    title: title,
                    iconCls: iconCls,
                    href: "${pageContext.request.contextPath}" + url,
                    closable: true,
                    selected: true
                });
            }
        };
        $(function () {
            //初始化菜单,发送ajax获取所有的菜单选项
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/menu/queryAllMenu",
                success: function (result) {
                    //循环生成菜单项
                    for (var me = 0; me < result.length; me++) {
                        var chr = "";
                        for (var i = 0; i < result[me].childs.length; i++) {
                            var child = result[me].childs[i];
                            // chr += "<p><a onclick='" + addMyTab() + "'   href='#' class='easyui-linkbutton' >" +child.title+ "</a></p>";
                            chr += "<p><a href='#' class='easyui-linkbutton' onclick='javascript:(addMyTab(\"" + child.title + "\",\"" + child.url + "\",\"" + child.iconCls + "\"))'>" + child.title + "</a></p>";
                        }
                        ;
                        $('#aa').accordion('add', {
                            id: "accordion" + result[me].id,
                            title: result[me].title,
                            content: chr,
                            selected: false,
                            iconCls: result[me].iconCls
                        });

                    }
                    ;

                },
                error: function () {
                    alert("服务器繁忙，请稍后重试")
                },
                dataType: "json"
            });
        });
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px"><p><c:if
            test="${sessionScope.manager==null}">请登陆后访问本界面</c:if><c:if test="${sessionScope.manager!=null}">
        欢迎您:尊敬的${sessionScope.manager.nickName}<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a><a
                onclick="exit()" href="#"
                class="easyui-linkbutton"
                data-options="iconCls:'icon-01'">退出系统</a></p></c:if>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div id="showallmenu" data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>