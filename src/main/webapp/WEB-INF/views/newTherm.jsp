<%-- 
    Document   : newTherm
    Created on : 02.07.2015, 14:16:34
    Author     : Володя
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div id="addTherm" class="modal fade" data-focus-on="input:first">
    <div class="modal-dialog" >
        <div class="modal-content" style="max-width: 450px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clearField('thermInp');">×</button>
                <h3>Додати терм-множину</h3>
            </div>
            <div class="modal-body" >
                <div class="container ">
                    <div class="col-md-4">
                        <label for="thermaddb" class="control-label">Назва терм-множини</label>
                        <input type="text" class="form-control" id="thermInp">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-primary" id="thermAddB" onclick="sendNewTherm();clearField('thermInp');">Зберегти зміни</button>
                <button type="button" data-dismiss="modal" class="btn" onclick="clearField('thermInp');">Закрити</button>
            </div>
        </div>
    </div>
</div>
<script>
    function openTherm()
    {
        $('#addTherm').modal();
    }
    function getInsertedTherm()
    {
        var elem = document.getElementById("thermInp");
        var obj = {};
        obj["name"] = elem.value;
        return JSON.stringify(obj);
    }
    function sendNewTherm()
    {
        $.ajax({
            type: 'POST',
            url: '/Waterflooding/admin/addTherm',
            data: getInsertedTherm(),
            contentType: 'application/json',
            success: function (result)
            {
                clearField("thermInp");
            },
            error: function ()
            {
                clearField("thermInp");
                alert("try again later");
            }
        });
    }
    function clearField(fieldName)
    {
        var elem = document.getElementById(fieldName);
        elem.value = "";
    }
</script>