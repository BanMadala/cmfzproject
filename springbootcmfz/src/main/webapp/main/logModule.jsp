<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/6/4
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
    <script type="text/javascript">
        $(function(){
            //初始化数据表格
            $('#showAlllog').datagrid({
                pageSize:10,
                pagination:true,
                pageList:[10,20,30,35,40],
                fit:true,
                fitColumns:true,
                title: "日志一览",
                remoteSort: false,
                singleSelect: true,
                nowrap: false,
                url: '${pageContext.request.contextPath}/log/queryLogs',
                columns: [[
                    {field: 'id', title: '编号',sortable: true},
                    {field: 'createTime', title: '创建时间',sortable: true},
                    {field: 'text', title: '日志信息',sortable: true},
                    {field: 'managerAccount', title: '管理员',sortable: true}

                ]]
            });
        });

    </script>
<table id="showAlllog"></table>


