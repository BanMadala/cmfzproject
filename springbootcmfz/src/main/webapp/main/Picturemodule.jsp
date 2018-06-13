<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/5/29
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        //初始化工具栏
        //添加
        $('#addforpicture').linkbutton({
            iconCls: 'icon-add',
            onClick: function () {
                $("#dialogforuploadpicture").dialog("open");
            }
        });
        //删除
        $('#delforpicture').linkbutton({
            iconCls: 'icon-cut',
            onClick: function () {
                var delrowdata = $("#showAllPicture").datagrid("getSelected");
                //alert(rowdata.id+"-------"+rowdata.picturePath);
                if (confirm("确认删除id为: " + delrowdata.id + "文件名为: " + delrowdata.picturePath + "的文件吗,注意此操作不可逆")) {
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/picture/delPicture",
                        data: "pictureId=" + delrowdata.id + "&picturePath=" + delrowdata.picturePath,
                        dataType: "json",
                        success: function (result) {
                            alert(result.message);
                            if (result.message.indexOf("成功") != -1) {
                                $("#showAllPicture").datagrid('reload');
                            }
                        }
                    });
                }
            }
        });
        //修改
        $('#modifyforpicture').linkbutton({
            iconCls: 'icon-edit',
            onClick: function () {
                $("#savemodifyforpicture").linkbutton({disabled:false});
                $('#cancelmodifyforpicture').linkbutton({disabled:false});

                //获取指定行
                rowdata = $("#showAllPicture").datagrid("getSelected");
                //获取当前行的行号
               // var index = $("#showAllPicture").datagrid("getRowIndex", rowdata);
                index = $("#showAllPicture").datagrid("getRowIndex", rowdata);
                //开启对应行的编辑器
                $("#showAllPicture").datagrid("beginEdit", index);
                //修改后失效
                // $("#modifyforpicture").linkbutton("disable");
                $('#modifyforpicture').linkbutton({disabled:true})
            }
        });

        //保存修改
        $("#savemodifyforpicture").linkbutton({
            disabled:true,
            iconCls: 'icon-save',
            onClick: function () {
                //启用修改按钮
                // $("#savemodifyforpicture").linkbutton('enable');
                // //获取指定行
                // var rowdata = $("#showAllPicture").datagrid("getSelected");
                // //获取当前行的行号
                // var index = $("#showAllPicture").datagrid("getRowIndex", rowdata);
                $("#showAllPicture").datagrid("endEdit", index);
                if ($("#showAllPicture").datagrid("validateRow", index)) {
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/picture/updatePicture",
                        data: "id=" + rowdata.id + "&pictureName=" + rowdata.pictureName + "&message=" + rowdata.message + "&status=" + rowdata.status,
                        dataType:"json",
                        success:function(data){
                            if(data.message.indexOf("成功")!=-1){
                                //结束编辑
                                alert(data.message);
                            }else{
                                alert(data.message+"此次修改没有写入系统")
                            }
                            index=null;
                            rowdata=null;
                            $('#modifyforpicture').linkbutton({disabled:false});
                            $("#savemodifyforpicture").linkbutton({disabled:true});
                            $('#cancelmodifyforpicture').linkbutton({disabled:true});
                        }
                    })
                }
            }
        });

        //放弃修改
        $('#cancelmodifyforpicture').linkbutton({
            disabled:true,
            iconCls: 'icon-cancel',
            onClick: function () {
                //启用修改按钮
                // $("#savemodifyforpicture").linkbutton('enable');
                //获取指定行
                // var rowdata = $("#showAllPicture").datagrid("getSelected");
                //获取当前行的行号
                // var index = $("#showAllPicture").datagrid("getRowIndex", rowdata);
                $("#showAllPicture").datagrid("cancelEdit", index);
                $('#modifyforpicture').linkbutton({disabled:false})
                $("#savemodifyforpicture").linkbutton({disabled:true});
                $('#cancelmodifyforpicture').linkbutton({disabled:true});
                index=null;
                rowdata=null;
            }
        });



        //刷新
        $('#refshforpicture').linkbutton({
            iconCls: 'icon-search',
            onClick: function () {
                $("#showAllPicture").datagrid("reload");
            }
        });
        //初始化验证规则
        $.extend($.fn.validatebox.defaults.rules, {
            setstatus: {
                validator: function (value, param) {
                    return value == param[0] || value == param[1];
                },
                message: '请输入状态码:y或者n'
            }
        });


        //初始化数据表格
        $('#showAllPicture').datagrid({
            pageSize:2,
            pagination:true,
            pageList:[2,3,4],
            fit:true,
            fitColumns:true,
            toolbar: "#showallpicturetoolbar",
            title: "轮播图一览",
            remoteSort: false,
            singleSelect: true,
            nowrap: false,
            url: '${pageContext.request.contextPath}/picture/queryAllPicture',
            columns: [[
                {field: 'id', title: 'id',sortable: true},
                {
                    field: 'pictureName', title: 'pictureName',sortable: true, editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            missingMessage: '图片名必填!'
                        }
                    }
                },
                {field: 'picturePath', title: 'picturePath', align: 'right', sortable: true},
                {
                    field: 'message', title: 'message', align: 'right', sortable: true, editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            missingMessage: '图片描述必填!'
                        }
                    }
                },
                {field: 'uploadTime', title: 'uploadTime', sortable: true},
                {
                    field: 'status', title: 'status', align: 'center', editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            validType: ["setstatus['y','n']"],
                            missingMessage: '图片轮播状态必填!'
                        }
                    }
                },
                {field: 'size', title: 'size', width: 60, align: 'center'},
                {field: 'md5Code', title: 'md5Code', width: 60, align: 'center'}
            ]],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    "<td rowspan=3 style='border:0'><img src='http://localhost:8989/cmfzimg/" + rowData.picturePath + "' style='height:50px;'></td>" +
                    '<td style="border:0">' +
                    '<p> uploadTime: ' + rowData.uploadTime + '</p>' +
                    '<p> Status: ' + rowData.status + '</p>' +'<p> PictureName: ' + rowData.pictureName + '</p>'+
                    '</td>' +
                    '</tr></table>';
            }
        });
        //初始化文件上传的弹窗
        $('#dialogforuploadpicture').dialog({
            title: '上传文件',
            width: 400,
            height: 200,
            closed: true,
            cache: false,
            modal: true,
            buttons: [{
                text: '上传',
                handler: function () {
                    $("#formforuploadpicture").form("submit");
                }
            }, {
                text: '关闭',
                handler: function () {
                    // $("#dialogforuploadpicture").dialog("reset");
                    $("#dialogforuploadpicture").dialog("close");
                }
            }]

        });
        //初始化form表单
        $('#formforuploadpicture').form({
            url: "${pageContext.request.contextPath}/picture/uploadPicture",
            onSubmit: function () {
                // do some check
                // return false to prevent submit;
                return $("#formforuploadpicture").form("validate");
            },
            success: function (data) {
                var map = eval("(" + data + ")");
                alert(map.message);
                if (map.message.indexOf("成功") != -1) {
                    // $("#dialogforuploadpicture").dialog("reset");
                    $("#dialogforuploadpicture").dialog("close");
                    $("#showAllPicture").datagrid("reload");
                }
            }
        });
        //初始化表单输入框
        $("#uploadforpictureName").textbox({
            iconCls: 'icon-man',
            iconAlign: 'right',
            multiple: false,
            required: true,
            validateOnBlur: true,
            missingMessage: "请输入图片名"
        });

        $("#uploadformessage").textbox({
            iconCls: 'icon-man',
            iconAlign: 'right',
            multiple: false,
            required: true,
            validateOnBlur: true,
            missingMessage: "请输入图片的描述内容"
        });

        //初始化文件上传框
        $('#uploadpictureFilebox').filebox({
            buttonText: '选择文件',
            buttonAlign: 'right',
            multiple: false,
            required: true,
            validateOnBlur: true,
            missingMessage: "请选择需要上传的文件"
        });


    });
</script>
<table id="showAllPicture"></table>
<!--数据表格工具栏 -->
<div id="showallpicturetoolbar">
    <a id="addforpicture" href="#">添加</a>
    <a id="modifyforpicture" href="#">修改</a>
    <a id="savemodifyforpicture" href="#">保存修改</a>
    <a id="cancelmodifyforpicture" href="#">放弃修改</a>
    <a id="delforpicture" href="#">删除</a>
    <a id="refshforpicture" href="#">刷新</a>
</div>
<!--上传文件的弹窗 -->
<div id="dialogforuploadpicture">
    <form id="formforuploadpicture" method="post" enctype="multipart/form-data">
        <div>
            <!--label是一个绑定样式的标签 -->
            <p><label for="uploadforpictureName">请输入图片名:</label>
                <input id="uploadforpictureName" name="pictureName" style="width:300px">
            </p><label for="uploadformessage">请描述图片:</label>
            &nbsp;&nbsp;&nbsp;<input id="uploadformessage" name="message" style="width:300px">
            <p>
            <p>
                <label for="uploadpictureFilebox">请选择文件:</label>
                &nbsp;&nbsp;&nbsp;<input id="uploadpictureFilebox" name="pictureFile" style="width:300px">
            </p>
        </div>
    </form>
</div>

