<%-- 
    Document   : aboutCell
    Created on : 27.05.2015, 17:01:54
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div id="stack1" class="modal fade" data-focus-on="input:first">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="$('#params label').remove();
                        $('#params input').remove();$('#stack1').modal(':hidden');">×</button>
                <h3>Інформація про критерій</h3>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row col-md-8">
                        <div class="col-md-4 left">
                            <label for="formula" class="col-sm-10 control-label">Тип функції належності</label>
                            <input type="text" class="form-control" id="formula">
                            <label for="argument" class="col-sm-10 control-label">Введене значення параметру</label>
                            <input type="text" class="form-control" id="argument">
                            <label for="function" class="col-sm-10 control-label">Ступінь належності критерію</label>
                            <input type="text" class="form-control" id="function">
                            <label for="weight_factor" class="col-sm-10 control-label">Ваговий коефіціент критерію</label>
                            <input type="text" class="form-control" id="weight_factor">
                        </div>
                        <div class="col-md-4" id="params">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn" onclick="$('#params label').remove();
                        $('#params input').remove(); $('#stack1').modal(':hidden');">Закрити</button>
            </div>
        </div>
    </div>
</div>
<script charset="utf-8" type="text/javascript" >
        function criteriaClick(id) {
        var obj = {};
        obj["id"] = id;
        var string = JSON.stringify(obj);
        $.ajax({
            type: 'POST',
            url: '/com/criteria',
            contentType: 'application/json; charset=utf-8',
            data: string,
            dataType: 'json',
            success: function (result)
            {

                var elem = document.getElementById("formula");
                elem.value = result.formula;
                elem = document.getElementById("argument");
                elem.value = result.argument;
                elem = document.getElementById("function");
                elem.value = result.value;
                elem = document.getElementById("weight_factor");
                elem.value = result.weighFactor;
                for (var i = 0; i < result.parameters.length; i++)
                {
                    $('#params').append('<label for="' + result.parameters[i].name + '" class="col-sm-7 control-label">' + result.parameters[i].name + '</label>');
                    $('#params').append('<input type="text" class="form-control" id="' + result.parameters[i].name + '" value="' + result.parameters[i].value + '"></input>');
                }
                $('#stack1').modal('show');
            },
            error: function ()
            {
                alert("try again later");
            }
        });

    };
</script>

