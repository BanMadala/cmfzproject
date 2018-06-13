<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2018/6/1
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function () {
        //goeasy
        var goEasy = new GoEasy({
            appkey: 'BS-10cba57cc2164472be426c45a084f59b'
        });
        //GoEasy-OTP可以对appkey进行有效保护，详情请参考：GoEasy-Reference
        goEasy.subscribe({
            channel:'mychanel',
            onMessage: function(message){
                alert('收到：'+message.content);
            }
        });

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('statistics_main'));


        $.post("${pageContext.request.contextPath}/user/queryUserInTheNearThreadWeek", function (data) {
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '持名法州App活跃用户'
                },
                tooltip: {},
                legend: {
                    data: ['数量', '数量2']
                },
                xAxis: {
                    data: data.week
                },
                yAxis: {},
                series: [{
                    name: '数量',
                    type: 'bar',
                    data: data.counts
                }, {
                    name: '数量2',
                    type: 'line',
                    data: data.counts
                }]
            };
            myChart.setOption(option);
        }, "json")
    })
</script>
<div id="statistics_main" style="width: 600px;height: 400px;;margin-top: 30px;margin-left: 30px"></div>
</body>
