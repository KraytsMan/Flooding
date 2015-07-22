<%-- 
    Document   : criteriaPart
    Created on : 03.06.2015, 19:19:30
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container ">
    <div class="row col-md-2">
        <table class="table table-bordered text-center" id="floodTable"  style="background-color:#9e9e9e">
            <caption class="text-center"><h4 style="height:30px;" >Альтернативи</h4></caption>
            <thead>
            <th height="60px" class="text-center">Система заводнення</th>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div class="row col-md-8">
        <div class="table-responsive">
            <table class="table table-bordered text-center" id="criteriaTable">
                <caption class="text-center"><h4 style="height:30px;">Критерії</h4></caption>
                <thead>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row col-md-2">
        <table class="table table-bordered text-center" id="resultTable"  style="background-color:#9e9e9e">
            <caption class="text-center"><h4 style="height:30px;">Оцінка альтернатив</h4></caption>
            <thead>
            <th height="60px" class="text-center">Kmin</th>
            <th height="60px" class="text-center">Kav</th>
            </thead>
            <tbody >
            </tbody>
        </table>
    </div>
</div>
<script>
    function loadCriterias() {
        $.ajax({
            type: 'POST',
            url: '/Waterflooding/allCriterias',
            contentType: 'application/json; charset=utf-8',
            success: function (result)
            {
                setFloodings(result.floodings,"floodTable");
                setResults(result.results);
                setCaptions(result.captions,"criteriaTable");
                setCriterias(result.criterias,"criteriaTable");
            },
            error: function ()
            {
                alert("try again later");
            }
        });
    }
    function setFloodings(data, string)
    {
        for (var i = 0; i < data.length; i++) {
            var row = $("<tr />")
            $("#"+string).append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($("<td>" + data[i].name + "</td>"));
        }
    }
    function setResults(data)
    {
        for (var i = 0; i < data.length; i++) {
            var row = $("<tr />")
            $("#resultTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($("<td >" + data[i].minimum + "</td>"));
            row.append($("<td >" + data[i].average + "</td>"));
        }
    }

    function setCaptions(data, string)
    {
        var row = $("<tr />")
        for (var i = 0; i < data.length; i++) {
            $("#"+string+" thead").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($("<th height='60px' class='text-center'>" + data[i].name + "</th>"));
        }
    }
    function setCriterias(data,string)
    {
        for (var i = 0; i < data.length; i++) {
            var row = $("<tr />")
            $("#"+string+" tbody").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($(data[i].criteria));
        }
    }
    function cleanCriterias()
    {
        $('#floodTable td').remove();
        $('#resultTable td').remove();
        $('#criteriaTable th').remove();
        $('#criteriaTable td').remove();
    }
</script>