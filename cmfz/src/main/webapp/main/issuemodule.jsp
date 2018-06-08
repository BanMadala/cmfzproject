<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/5/30
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        //拓展验证规则
        $.extend($.fn.validatebox.defaults.rules, {
            minLength: {
                validator: function (value, param) {
                    return !isNaN(value) && value >= param[0];
                },
                message: '请输入小于5的值'
            }
        });


        //
        $('#showAllIssue').treegrid({
            onDblClickRow:function(row){
                if(isNaN(row.id)){
                    $("#playoneaudiodialog").html("<audio id=\"audioplaer\" autoplay  controls>\n" +
                        "\n" +
                        "<source id=\"audioplaersrc\" src=\"http://192.168.0.157:8989/cmfzaudio/modeleide/"+row.url+"\" type=\"audio/mpeg\"></audio>");
                    $("#playoneaudiodialog").dialog("open");
                }
            },
            toolbar: [{
                iconCls: 'icon-search',
                handler: function () {
                    var row = $('#showAllIssue').treegrid("getSelected");
                    if (row == null) {
                        alert("请选择一个专辑进行查看");
                    } else {
                        if (!isNaN(row.id)) {
                            $("#uptissueimg").prop("src", "http://192.168.0.157:8989/cmfzaudio/" + row.img)
                            $("#uptformforissue").form("load", row);
                            $("#showoneIssuedialog").dialog("open");
                        }else{
                            var par=$("#showAllIssue").treegrid("getParent", row.id);
                            $("#uptissueimg").prop("src", "http://192.168.0.157:8989/cmfzaudio/" + par.img)
                            $("#uptformforissue").form("load", par);
                            $("#showoneIssuedialog").dialog("open");
                        }
                    }
                }
            }, {
                iconCls: 'icon-add',
                handler: function () {
                    $("#addoneIssuedialog").dialog("open");
                }
            }, '-',
                {
                    iconCls: 'icon-cancel',
                    handler: function () {
                        var thenodeid = $('#showAllIssue').treegrid("getSelected").id;
                        if (isNaN(thenodeid)) {
                            if (confirm("是否删除该     " + thenodeid + "   章节音频")) {
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.request.contextPath}/audio/removeAudio",
                                    data: "id=" + thenodeid+"&url="+$('#showAllIssue').treegrid("getSelected").url,
                                    dataType: "json",
                                    success: function (result) {
                                        alert(result.message);
                                        if (result.message.indexOf("成功") != -1) {
                                            $('#showAllIssue').treegrid("load", {
                                                "data": new Date()
                                            });
                                        }
                                    }
                                });
                            }
                        } else {
                            if (confirm("是否删除该专辑   " + thenodeid + "   以及该专辑下的所有章节音频")) {
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.request.contextPath}/issue/delIssue",
                                    data: "id=" + thenodeid,
                                    dataType: "json",
                                    success: function (result) {
                                        alert(result.message);
                                        if (result.message.indexOf("成功") != -1) {
                                            $('#showAllIssue').treegrid("load", {
                                                "data": new Date()
                                            });
                                        }
                                    }
                                });
                            }
                        }
                    }
                },
                {
                    iconCls: 'icon-edit',
                    handler: function () {
                        //添加专辑
                        $("#selectissueId").combobox("reload");
                        $("#addoneaudiodialog").dialog("open");
                    }
                },
                {
                    iconCls: 'icon-save',
                    handler: function () {
                        var thenode = $('#showAllIssue').treegrid("getSelected");

                        if(isNaN(thenode.id)){
                            console.info(thenode);
                            <%--$.ajax({--%>
                                <%--type:"post",--%>
                                <%--url:"${pageContext.request.contextPath}/audio/downloadAudio",--%>
                                <%--data: "id=" + thenodeid+"&url="+$('#showAllIssue').treegrid("getSelected").url+"&name="+$('#showAllIssue').treegrid("getSelected").name--%>
                            <%--})--%>
                            location.href="${pageContext.request.contextPath}/audio/downloadAudio?id="+thenode.id+"&name="+thenode.name+"&url="+thenode.url;
                        }
                    }
                },
                {
                    iconCls: 'icon-ok',
                    handler: function () {
                        var r=window.setInterval(function(){
                            $('#showAllIssue').treegrid("load", {
                                "data": new Date()
                            },3000);
                            clearInterval(r)
                        })

                    }
                }],
            maximized: true,
            fit: true,
            fitColumns: true,
            // pageList:[10,20,30],
            // pageSize:10,
            // pagination: true,
            url: "${pageContext.request.contextPath}/issue/test",
            idField: "id",
            treeField: 'name',
            columns: [[
                {title: 'Task ID', field: 'id'},
                {title: 'Task Name', field: 'name'},
                {field: 'size', title: '音频大小', align: 'right'},
                {
                    field: 'url', title: 'url'
                    // , formatter: function (value, row, index) {
                    //     return "<audio controls>\n" +
                    //         "  <source src=\"http://localhost:8989/cmfzaudio/modeleide/" + row.url + "\" type=\"audio/mpeg\">\n" +
                    //         "  您的浏览器不支持 audio 元素。\n" +
                    //         "</audio>";
                    //     ;
                    // }

                },
                {field: 'audioTime', title: '文件时长', width: 80},
                {field: 'md5Code', title: '文件指纹', width: 80}
            ]]
        });
        //初始化弹窗
        //修改
        $('#showoneIssuedialog').dialog({
            title: '专辑详情',
            width: 400,
            height: 400,
            closed: true,
            cache: false,
            modal: true
        });
        //添加
        $('#addoneIssuedialog').dialog({
            title: '创建专辑',
            width: 400,
            height: 400,
            closed: true,
            cache: false,
            modal: true
        });
        //添加章节弹窗
        $('#addoneaudiodialog').dialog({
            title: '添加章节音频',
            width: 400,
            height: 400,
            closed: true,
            cache: false,
            modal: true
        });



        //初始化弹窗按钮
        //保存修改
        $('#saveuptissuedailog').linkbutton({
            iconCls: 'icon-save',
            onClick: function () {
                $("#uptformforissue").form("submit");
            }
        });
        //关闭弹窗
        $('#closeupissuetdailog').linkbutton({
            iconCls: 'icon-cancel',
            onClick: function () {
                $("#showoneIssuedialog").dialog("close");
            }
        });
        //初始化添加弹窗按钮
        //保存
        $('#saveaddissuedailog').linkbutton({
            iconCls: 'icon-save',
            onClick: function () {
                $("#addformforissue").form("submit");
            }
        });
        //关闭弹窗
        $('#closeaddissuetdailog').linkbutton({
            iconCls: 'icon-cancel',
            onClick: function () {
                $("#addoneIssuedialog").dialog("close");
            }
        });
        //初始化添加添加章节弹窗按钮
        //保存
        $('#saveaddaudiodailog').linkbutton({
            iconCls: 'icon-save',
            onClick: function () {
                $("#addformforaudio").form("submit");
            }
        });
        //关闭弹窗
        $('#closeaddaudiodailog').linkbutton({
            iconCls: 'icon-cancel',
            onClick: function () {
                $("#addoneaudiodialog").dialog("close");
            }
        });



        //初始化修改的form表单
        $('#uptformforissue').form({
            onSubmit: function () {
                // do some check
                // return false to prevent submit;
                return $("#uptformforissue").form("validate");
            },
            success: function (data) {
                var map = eval("(" + data + ")");
                alert(map.message)
                if (map.message.indexOf("成功") !== -1) {
                    $("#showoneIssuedialog").dialog("close");
                    $('#showAllIssue').treegrid("load", {
                        "data": new Date()
                    });
                }
            }
        });
        //初始化添加form表单
        $('#addformforissue').form({
            onSubmit: function () {
                // do some check
                // return false to prevent submit;
                return $("#addformforissue").form("validate");
            },
            success: function (data) {
                var map = eval("(" + data + ")");
                alert(map.message)
                if (map.message.indexOf("成功") !== -1) {
                    $("#addoneIssuedialog").dialog("close");
                    $('#showAllIssue').treegrid("load", {
                        "data": new Date()
                    });
                }
            }
        });

        //初始化添加章节form表单
        $('#addformforaudio').form({
            onSubmit: function () {
                // do some check
                // return false to prevent submit;
                return $("#addformforaudio").form("validate");
            },
            success: function (data) {
                var map = eval("(" + data + ")");
                alert(map.message);
                if (map.message.indexOf("成功") !== -1) {
                    $("#addoneaudiodialog").dialog("close");
                    $('#showAllIssue').treegrid("load", {
                        "data": new Date()
                    });
                }
            }
        });



        //初始化输入框
        $("#uptissueid").textbox({
            required: true,
            width: 200,
            validateOnBlur: true,
            readonly: true
        });
        $("#uptissuename").textbox({
            required: true,
            width: 200
        });
        // $("#uptissuecredate").datebox({
        //     required:true,
        //     width:135,
        //     validateOnBlur:true,
        //     readonly:true
        // });
        $("#uptissuescore").textbox({
            required: true,
            width: 195
        });
        $("#uptissueauthore").textbox({
            required: true,
            width: 195
        });
        $("#uptissuebrief").textbox({
            required: true,
            width: 220
        });
        $("#uptissuecounts").textbox({
            required: true,
            width: 180
        })
        //添加表单
        //初始化文件上传框
        $('#addissuePicture').filebox({
            buttonText: '选择文件',
            buttonAlign: 'right',
            multiple: false,
            required: true,
            validateOnBlur: true,
            missingMessage: "请选择需要上传的文件"
        });

        //初始化输入框
        $("#addissueid").textbox({
            required: true,
            width: 200,
            validateOnBlur: true,
            readonly: true
        });
        $("#addissuename").textbox({
            required: true,
            width: 200
        });
        // $("#uptissuecredate").datebox({
        //     required:true,
        //     width:135,
        //     validateOnBlur:true,
        //     readonly:true
        // });
        $("#addissuescore").textbox({
            required: true,
            width: 195
        });
        $("#addissueauthore").textbox({
            required: true,
            width: 195
        });
        $("#addissuebrief").textbox({
            required: true,
            width: 220
        });
        $("#addissuecounts").textbox({
            required: true,
            width: 180
        })
        //初始化添加章节form表单的元素
        $("#addaudioname").textbox({
            required: true,
            width: 180
        });

        $("#addaudioTime").textbox({
            required: true,
            width: 180
        });

        $('#addaudio').filebox({
            buttonText: '选择文件',
            buttonAlign: 'right',
            multiple: false,
            required: true,
            validateOnBlur: true,
            missingMessage: "请选择需要上传的文件"
        });
        $('#selectissueId').combobox({
            url:"${pageContext.request.contextPath}/issue/getAllIssueNameAndId",
            valueField:'id',
            textField:'name'
        });
        //播放
        $("#playoneaudiodialog").dialog({
            closable:false,
            title: '播放章节音频',
            width: 400,
            height: 150,
            closed: true,
            cache: false,
            modal: true,
            buttons:[{
                text:'关闭',
                handler:function(){
                    $("#playoneaudiodialog").dialog("clear");
                    $("#playoneaudiodialog").dialog("close");
                }

            }]
        });;
    });
</script>
<table id="showAllIssue" style="width:600px;height:400px"></table>
<div id="showoneIssuedialog">
    <form id="uptformforissue" action="${pageContext.request.contextPath}/issue/updateIssue" method="post">
        <div>
            <label for="uptissueid">专辑id:</label>
            <input type="text" id="uptissueid" name="id"/>
        </div>
        <div>
            <label for="uptissuename">专辑名:</label>
            <input type="text" id="uptissuename" name="name"/>
        </div>
        <div>
            <label for="uptissuecredate">专辑创建时间:</label>
            <input type="text" readonly id="uptissuecredate" name="createDate"/>
        </div>
        <div>
            <label for="uptissuescore">专辑评分:</label>
            <input type="text" maxlength="3" id="uptissuescore" name="score"/>
        </div>
        <div>
            <label for="uptissueauthore">专辑作者:</label>
            <input type="text" id="uptissueauthore" name="author"/>
        </div>
        <div>
            <label for="uptissuebrief">播音:</label>
            <input type="text" id="uptissuebrief" name="brief"/>
        </div>
        <div>
            <label for="uptissueimg">专辑图片:</label>
            <img width="100" height="100" id="uptissueimg" src="">
        </div>
        <div>
            <label for="uptissuecounts">章节数量:</label>
            <input type="text" id="uptissuecounts" name="counts"/>
        </div>
        <a href="#" id="saveuptissuedailog">保存</a>
        <a href="#" id="closeupissuetdailog">关闭</a>


    </form>

</div>

<div id="addoneIssuedialog">
    <form id="addformforissue" action="${pageContext.request.contextPath}/issue/createIssue"
          enctype="multipart/form-data" method="post">
        <div>
            <label for="uptissuename">专辑名:</label>
            <input type="text" id="addissuename" name="name"/>
        </div>
        <div>
            <label for="addissuescore">专辑评分:</label>
            <input type="text" maxlength="3" id="addissuescore" name="score"/>
        </div>
        <div>
            <label for="addissueauthore">专辑作者:</label>
            <input type="text" id="addissueauthore" name="author"/>
        </div>
        <div>
            <label for="addissuebrief">播音:</label>
            <input type="text" id="addissuebrief" name="brief"/>
        </div>
        <div>
            <label for="addissuePicture">请选择文件:</label>
            <input id="addissuePicture" name="issueImg" style="width:300px">

        </div>
        <div>
            <label for="addissuecounts">章节数量:</label>
            <input type="text" id="addissuecounts" name="counts"/>
        </div>
        <a href="#" id="saveaddissuedailog">保存</a>
        <a href="#" id="closeaddissuetdailog">关闭</a>
    </form>

</div>

<div id="addoneaudiodialog">
    <form id="addformforaudio" action="${pageContext.request.contextPath}/audio/uploadAudio"
          enctype="multipart/form-data" method="post">
        <div>
            <label for="addaudioname">章节名:</label>
            <input type="text" id="addaudioname" name="name"/>
        </div>
        <div>
            <label for="addaudioTime">播放时长:</label>
            <input type="text" id="addaudioTime" name="audioTime"/>
        </div>
        <div>
            <label for="addaudio">请选择文件:</label>
            <input id="addaudio" name="audioFile" style="width:300px">
        </div>
        <%--<div>--%>
            <%--<label for="addaudioparentId">专辑id:</label>--%>
            <%--<input type="text" id="addaudioparentId" name="issueId"/>--%>
        <%--</div>--%>
        <div>
            <label for="selectissueId">请选择所属专辑:</label>
        <select id="selectissueId" name="issueId"  style="width:200px;">
        </select>
        </div>

        <a href="#" id="saveaddaudiodailog">保存</a>
        <a href="#" id="closeaddaudiodailog">关闭</a>
    </form>
</div>
<div id="playoneaudiodialog">
    <%--<audio id="audioplaer" autoplay  controls>--%>

    <%--</audio>;--%>
</div>

