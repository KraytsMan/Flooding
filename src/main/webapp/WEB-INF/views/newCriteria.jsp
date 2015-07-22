<%-- 
    Document   : newCriteria
    Created on : 02.07.2015, 18:56:20
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div id="addCriteria" class="modal fade" data-focus-on="input:first">
    <div class="modal-dialog" >
        <div class="modal-content" style="max-width: 450px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="clearField('criteriaInp');">×</button>
                <h3>Додати критерій</h3>
            </div>
            <div class="modal-body" >
                <div class="container ">
                    <div class="col-md-4">
                        <label for="criteriaInp" class="control-label">Назва критерію</label>
                        <input type="text" class="form-control" id="criteriaInp">
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-primary" id="thermAddB" onclick="sendNewTherm();
                        clearField('criteriaInp');">Зберегти зміни</button>
                <button type="button" data-dismiss="modal" class="btn" onclick="clearField('criteriaInp');">Закрити</button>
            </div>
        </div>
    </div>
</div>
<script>
    function openCriteria()
    {
        $('#addCriteria').modal();
    }
    function getInsertedCriteria()
    {
        var elem = document.getElementById("criteriaInp");
        var obj = {};
        obj["name"] = elem.value;
        return JSON.stringify(obj);
    }
    function sendNewTherm()
    {
        $.ajax({
            type: 'POST',
            url: '/Waterflooding/admin/newCriteria',
            data: getInsertedCriteria(),
            contentType: 'application/json',
            success: function (result)
            {
                reload();
                clearField("criteriaInp");
            },
            error: function ()
            {
                reload();
                clearField("criteriaInp");
                alert("try again later");
            }
        });
    }
</script>
