<%-- 
    Document   : deleteTherm
    Created on : 02.07.2015, 15:42:47
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div id="removeTherm" class="modal fade" data-focus-on="input:first">
    <div class="modal-dialog" >
        <div class="modal-content" style="max-width: 450px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="$('#deleteTherm option').remove();">×</button>
                <h3>Видалити терм-множину</h3>
            </div>
            <div class="modal-body" >
                <div class="container ">
                    <div class="col-md-4">
                        <label for="thermaddb" class="control-label">Назва терм-множини</label>
                        <select class="form-control" id="deleteTherm">
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-primary" id="thermDeleteB" onclick="deleteTherm();
                        $('#deleteTherm option').remove();">Зберегти зміни</button>
                <button type="button" data-dismiss="modal" class="btn" onclick="$('#deleteTherm option').remove();">Закрити</button>
            </div>
        </div>
    </div>
</div>
<script>
    function openDeleteTherm()
    {
        $.ajax({
            type: 'POST',
            url: '/Waterflooding/admin/allTherms',
            contentType: 'application/json',
            success: function (result)
            {
                $('#deleteTherm option').remove();
                for (var i = 0; i < result.thermlist.length; i++)
                {
                    $('#deleteTherm').append('<option id="' + result.thermlist[i].id + '">' + result.thermlist[i].name + '</option>');
                }
                document.getElementById("thermDeleteB").addEventListener("click", deleteTherm);
                $('#removeTherm').modal();
            },
            error: function ()
            {
                $('#deleteTherm option').remove();
                alert("try again later");
            }
        });
    }
    function getChosenOption()
    {
        var elem = document.getElementById("deleteTherm");
        var obj = {};
        obj["id"] = elem.options[elem.selectedIndex].id;
        return JSON.stringify(obj);
    }
    function deleteTherm()
    {

        $.ajax({
            type: 'POST',
            url: '/Waterflooding/admin/deleteTherm',
            data: getChosenOption(),
            contentType: 'application/json',
            success: function (result)
            {
            },
            error: function ()
            {
                alert("try again later");
            }
        });
    }
</script>
