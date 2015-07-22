<%-- 
    Document   : unclearPart
    Created on : 17.06.2015, 15:17:41
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="row col-md-12">
        <div class="row col-md-11">   
            <table class="table table-bordered" id="criteriaTable2">
                <caption class="text-center"><h4>Якщо</h4></caption>
                <thead>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class="row col-md-1">   
            <table class="table table-bordered" id="floodTable2" style="background-color:#9e9e9e">
                <caption class="text-center"><h4>То</h4></caption>
                <thead>
                <th>Система заводнення</th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
</div>
<script>
    function loadTherms() {
        $.ajax({
            type: 'POST',
            url: '/Waterflooding/allTherms',
            contentType: 'application/json; charset=utf-8',
            success: function (result)
            {
                setFloodings(result.floodings, "floodTable2");
                setCaptions(result.captions, "criteriaTable2");
                setCriterias(result.therms, "criteriaTable2");
            },
            error: function ()
            {
                alert("try again later");
            }
        });
    }
    function cleanTherms()
    {
        $('#floodTable2 td').remove();
        $('#criteriaTable2 th').remove();
        $('#criteriaTable2 tr').remove();
        $('#criteriaTable2 td').remove();
    }
</script>

