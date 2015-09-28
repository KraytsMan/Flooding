<%-- 
    Document   : mainPart
    Created on : 18.05.2015, 20:44:22
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-4 left">
    <form class="form-horizontal" id="floodingform">       
        <h4>Геолого-фізичні умови покладу</h4>
        <c:forEach var="caption" items="${listCaption}" varStatus="status">
            <div class="form-group">
                <label for="${caption.id}" class="col-sm-5 control-label">${caption.name}</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="${caption.id}">
                </div>
            </div>
        </c:forEach>  
        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-10">
                <button type="submit" class="btn btn-default" id="go">Розрахувати</button>
            </div>
        </div>
    </form>
</div>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script charset="utf-8" type="text/javascript">
    $(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Ефективність застосування систем заводнення у заданих геологічних умовах'
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Коефіцієнт ефективності застосування'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
        });
        document.getElementById("go").onclick = makePost;
        var chart = $('#container').highcharts();
        function setDiagram(category, koef)
        {
            while(chart.series.length > 0) chart.series[0].remove(true);
            chart.xAxis[0].setCategories(category);
            for(var i=0;i<koef.length;i++)
            chart.addSeries({
                name: koef[i].name,
                data: koef[i].data
            });
        }
        function getFormValues() {
            item = new Array();
            var ff = document.getElementById("floodingform");
            for (var i = 0; i < ff.elements.length - 1; i++) {
                var args = {id: ff.elements[i].id, value: ff.elements[i].value};
                item.push(args);
            }
            
            var string = JSON.stringify(item);
            return string;
        }
        function makePost() {
            if (checkDecimal() == false) {
                alert("Введіть правильні значення(дробові значення вводяться через крапку(.))");
            }
            else {
                $.ajax({
                    type: 'POST',
                    url: '/Flooding/computewaterflooding',
                    contentType: 'application/json',
                    data: getFormValues(),
                    success: function (result)
                    {
                        setDiagram(result.category, result.series);
                    },
                    error: function ()
                    {
                        alert("try again later");
                    }
                });
            }
        }
        function checkDecimal() {
            var ff = document.getElementById("floodingform");
            var flag = true;
            for (var i = 0; i < ff.elements.length - 1; i++) {
                if (isNaN(ff.elements[i].value) == true) {
                    return false;
                }
            }
            return flag;
        }
    });
</script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<div class="col-md-8">
    <div id="container" style="min-width: 500px; max-width:800px; height: 500px; margin: 0 auto"></div>
</div>
