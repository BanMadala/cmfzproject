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
            $('#showAllUser').datagrid({
                pageSize:20,
                pagination:true,
                pageList:[20,25,30,35,40],
                fit:true,
                fitColumns:true,
                 toolbar: [{
                     text:"批量导出用户信息",
                     iconCls: 'icon-save',
                     handler: function () {
                         $('#showallfiledsdialog').dialog("open");
                     }
                 },{
                    text:"批量导入用户信息",
                     iconCls: 'icon-add',
                     handler: function () {
                         $('#showuploadUserMessageFileBox').dialog("open");
                     }
                 }],
                title: "用户一览",
                remoteSort: false,
                singleSelect: true,
                nowrap: false,
                url: '${pageContext.request.contextPath}/user/queryAllUserByPage',
                columns: [[
                    {field: 'id', title: '编号',sortable: true},
                    {field: 'phoneNum', title: '手机号',sortable: true},
                    {field: 'userName', title: '用户名',sortable: true},
                    {field: 'password', title: '密码',sortable: true},
                    {field: 'salt', title: '用户盐',sortable: true},
                    {field: 'dharmaName', title: '法名',sortable: true},
                    {field: 'province', title: '省份',sortable: true},
                    {field: 'city', title: '城市',sortable: true},
                    {field: 'sex', title: '性别',sortable: true},
                    {field: 'sign', title: '签名',sortable: true},
                    {field: 'headPic', title: '头像路径',sortable: true},
                    {field: 'status', title: '用户是否冻结',sortable: true},
                    {field: 'date', title: '生日',sortable: true},
                    {field: 'regDate', title: '注册日期',sortable: true},
                    {field: 'masterId', title: '所属上师',sortable: true}
                ]],
                view: detailview,
                detailFormatter: function (rowIndex, rowData) {
                    return '<table><tr>' +
                        "<td rowspan=3 style='border:0'><img src='http://localhost:8989/cmfzimg/1514862261954.jpg' style='height:50px;'></td>" +
                        '<td style="border:0">' +
                        // '<p> uploadTime: ' + rowData.uploadTime + '</p>' +
                        // '<p> Status: ' + rowData.status + '</p>' +'<p> PictureName: ' + rowData.pictureName + '</p>'+
                        '</td>' +
                        '</tr></table>';
                }
            });


        //初始化自定义导出对话框
        $('#showallfiledsdialog').dialog({
            title: '请选择需要导出的列',
            width: 400,
            height: 200,
            closed: true,
            cache: false,
            // href: 'get_content.php',
            modal: true,
            buttons:[{
                iconCls:"icon-rollback",
                text:'保存',
                handler:function(){
                    // console.info($("#selforattr").combotree("getChecked"));
                    var t=$("#selforattr").combotree("tree");
                    var titles=$("#selforattr").combobox("getText");
                    var sel=t.tree("getChecked");
                    var fields="";
                    for(var i=0;i<sel.length;i++){
                          if(i==sel.length-1){
                              fields+=sel[i].id
                          }else{
                              fields+=sel[i].id+",";
                          }
                    };
                    <%--$.ajax({--%>
                        <%--type:"post",--%>
                        <%--url:"${pageContext.request.contextPath}/user/customExportUserMessage",--%>
                        <%--data:"titles="+titles+"&fields="+fields,--%>
                        <%--success:function(data){--%>
                            <%--alert(data)--%>
                        <%--}--%>
                    <%--});--%>
                    location.href="${pageContext.request.contextPath}/user/customExportUserMessage?"+"titles="+titles+"&fields="+fields;
                    $("#showallfiledsdialog").dialog("close");
                }
            },
                {
                    iconCls:"icon-cancel",
                    text:'关闭',
                    handler:function(){
                        $('#showallfiledsdialog').dialog("close");
                    }
                }
            ]
        });

        //初始化树形下拉列表
            $("#selforattr").combotree({
                multiple:true,
                onlyLeafCheck:true
            })

            //发送ajax加载需要选择的属性
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/user/queryAllFiledAndAnnotations",
                success:function(data){
                    $('#selforattr').combotree('loadData', [{
                        id: 1,
                        text: '请选择需要导出的列',
                        children:data
                    }]);
                },
                dataType:"json"
            });


            //初始化导入用户信息导入对话框
            $('#showuploadUserMessageFileBox').dialog({
                title: '请选择需要上传的用户信息文件',
                width: 250,
                height: 200,
                closed: true,
                cache: false,
                // href: 'get_content.php',
                modal: true,
                buttons:[{
                    iconCls:"icon-rollback",
                    text:"上传",
                    handler:function(){
                        $('#formForUploadUserMessage').form("submit");
                    }
                },
                    {
                        iconCls:"icon-cancel",
                        text:'关闭',
                        handler:function(){
                            $('#showuploadUserMessageFileBox').dialog("close");
                        }
                    }
                ]
            });

            //初始化用户信息文件上传form表单
            $('#formForUploadUserMessage').form({
                url: "${pageContext.request.contextPath}/user/inportUserMessage",
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                    return $("#formForUploadUserMessage").form("validate");
                },
                success: function (data) {
                    var map = eval("(" + data + ")");
                    alert(map.message);
                    if (map.message.indexOf("成功") != -1) {
                        // $("#dialogforuploadpicture").dialog("reset");
                        $("#showuploadUserMessageFileBox").dialog("close");
                        $("#showAllUser").datagrid("reload");
                    }
                }
            });
            //初始化文件上传框
            $('#uploaduserFilebox').filebox({
                buttonText: '选择文件',
                buttonAlign: 'right',
                multiple: false,
                required: true,
                validateOnBlur: true,
                missingMessage: "请选择需要上传的文件"
            });



        });
    </script>
<table id="showAllUser"></table>
<div id="showallfiledsdialog"><select id="selforattr"  style="width:200px;"></select></div>

<div id="showuploadUserMessageFileBox">
    <form id="formForUploadUserMessage" method="post" enctype="multipart/form-data">
        <div>
            <!--label是一个绑定样式的标签 -->
            <p>
                <label for="uploaduserFilebox">请选择文件:</label>
                &nbsp;&nbsp;&nbsp;<input id="uploaduserFilebox" name="userMessaageFile" style="width:300px">
            </p>
        </div>
    </form>


</div>



