<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/6/4
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function(){
           $("#selforattr").combotree({
               multiple:true,
               onlyLeafCheck:true
           })
            $('#cc').combotree('loadData', [{
                id: 1,
                text: 'Languages',
                children: [{
                    id: 11,
                    text: 'Java'
                },{
                    id: 12,
                    text: 'C++'
                }]
            }]);


        })
    </script>
</head>
<body>
<select id="selforattr"  style="width:200px;"></select>
</body>
</html>
