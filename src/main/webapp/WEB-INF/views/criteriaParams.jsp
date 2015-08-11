<%-- 
    Document   : criteriaParams
    Created on : 29.06.2015, 13:57:06
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-3" style="padding-bottom: 20px">
    <H4 class="text-center">Операції над критеріями</H4>
    <div class=" btn-group-vertical" role="group" aria-label="...">
        <button type="button" class="btn btn-default" onclick="openCriteria();">Додати критерій</button>
        <button type="button" class="btn btn-default">Видалити критерій</button>
        <button type="button" class="btn btn-default">Обновити критерій</button>
        <button type="button" class="btn btn-default">Додати систему заводнення</button>
        <button type="button" class="btn btn-default">Видалити систему заводнення</button>
        <button type="button" class="btn btn-default">Обновити систему заводнення</button>
        <button type="button" class="btn btn-default" onclick="openTherm();">Додати терм-множину</button>
        <button type="button" class="btn btn-default" onclick="openDeleteTherm();">Видалити терм-множину</button>
    </div>
</div>
<div class="row col-md-2">
    <table class="table table-bordered text-center" id="floodParams"  style="background-color:#9e9e9e">
        <caption class="text-center"><h4 >Альтернативи</h4></caption>
        <thead>
        <th height="60px" class="text-center">Система заводнення</th>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<div class="row col-md-7">
    <div class="table-responsive">
        <table class="table table-bordered text-center" id="criteriaParams">
            <caption class="text-center"><h4>Критерії</h4></caption>
            <thead>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
    $(function () {
        $.ajax({
            type: 'POST',
            url: '/com/admin/criterias',
            contentType: 'application/json',
            success: function (result)
            {
                setCaptions(result.captions, "criteriaParams");
                setCriterias(result.criterias, "criteriaParams");
                setFloodings(result.floodings, "floodParams");
            },
            error: function ()
            {
                alert("try again later");
            }
        });
    });
    function setFloodings(data, string)
    {
        for (var i = 0; i < data.length; i++) {
            var row = $("<tr />")
            $("#" + string).append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($("<td height='58px'>" + data[i].name + "</td>"));
        }
    }
    function setCaptions(data, string)
    {
        var row = $("<tr />")
        for (var i = 0; i < data.length; i++) {
            $("#" + string + " thead").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($("<th height='60px' class='text-center'>" + data[i].name + "</th>"));
        }
    }
    function setCriterias(data, string)
    {
        for (var i = 0; i < data.length; i++) {
            var row = $("<tr />")
            $("#" + string + " tbody").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
            row.append($(data[i].criteria));
        }
    }
    function reload()
    {
        $.ajax({
            type: 'POST',
            url: '/com/admin/criterias',
            contentType: 'application/json',
            success: function (result)
            {
                $('#floodParams td').remove();
                $('#criteriaParams th').remove();
                $('#criteriaParams td').remove();
                setCaptions(result.captions, "criteriaParams");
                setCriterias(result.criterias, "criteriaParams");
                setFloodings(result.floodings, "floodParams");
            },
            error: function ()
            {
                alert("try again later");
            }
        });
    }
</script>
