<%-- 
    Document   : aboutCell
    Created on : 27.05.2015, 17:01:54
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div id="Mymodal" class="modal fade" data-focus-on="input:first">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="$('#params label').remove();
                        $('#params input').remove();$('#therm option').remove();">×</button>
                <h3>Параметри критерію</h3>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row col-md-8">
                        <div class="col-md-4 left">
                            <label for="formula" class="col-md-12 control-label">Тип функції належності</label>
                            <select class="form-control" id="formula">
                                <option id="Трикутна" value="3">Трикутна</option>
                                <option id="Трапецієвидна" value="4">Трапецієвидна</option>
                                <option id="Z-подібна" value="2">Z-подібна</option>
                                <option id="S-подібна" value="2">S-подібна</option>
                                <option id="Лінійна z-подібна" value="2">Лінійна z-подібна</option>
                                <option id="Лінійна s-подібна" value="2">Лінійна s-подібна</option>
                            </select>
                            <label for="therm" class="col-sm-10 control-label">Нечітке значення</label>
                            <select class="form-control" id="therm">
                            </select>
                            <label for="weight_factor" class="col-sm-10 control-label">Ваговий коефіціент</label>
                            <input type="text" class="form-control" id="weight_factor">
                            <input type="hidden" class="form-control" id="id">
                        </div>
                        <form class="col-md-4" id="params2">
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-primary" id="demo">Зберегти зміни</button>
                <button type="button" data-dismiss="modal" class="btn" onclick="$('#params label').remove();
                        $('#params input').remove();$('#therm option').remove();">Закрити</button>
            </div>
        </div>
    </div>
</div>
<script charset="utf-8" type="text/javascript" >
    $('#formula').change(function () {
        $("select option:selected").each(function () {
            var k = $(this).val();
            if (k == 2) {
                setNullParametersForTwo();
            }
            if (k == 3) {
                setNullParametersForThree();
            }
            if (k == 4) {
                setNullParametersForFour();
            }
        });
    });
            function setNullParametersForTwo(one = 0, two = 1)
            {
            $('#params2 label').remove();
                    $('#params2 input').remove();
                    $('#params2').append('<label for="Параметр А" class="col-sm-7 control-label">Параметр А</label>');
                    $('#params2').append('<input type="text" class="form-control" id="Параметр А" value="' + one + '">');
                    $('#params2').append('<label for="Параметр В" class="col-sm-7 control-label">Параметр В</label>');
                    $('#params2').append('<input type="text" class="form-control" id="Параметр В" value="' + two + '">');
            }
    function setNullParametersForThree(one = 0, two = 1, three = 2)
    {
    $('#params2 label').remove();
            $('#params2 input').remove();
            $('#params2').append('<label for="Параметр А" class="col-sm-7 control-label">Параметр А</label>');
            $('#params2').append('<input type="text" class="form-control" id="Параметр А" value="' + one + '">');
            $('#params2').append('<label for="Параметр В" class="col-sm-7 control-label">Параметр В</label>');
            $('#params2').append('<input type="text" class="form-control" id="Параметр В" value="' + two + '">');
            $('#params2').append('<label for="Параметр C" class="col-sm-7 control-label">Параметр C</label>');
            $('#params2').append('<input type="text" class="form-control" id="Параметр C" value="' + three + '">');
    }
    function setNullParametersForFour(one = 0, two = 1, three = 2, four = 3)
    {
        $('#params2 label').remove();
        $('#params2 input').remove();
        $('#params2').append('<label for="Параметр А" class="col-sm-7 control-label">Параметр А</label>');
        $('#params2').append('<input type="text" class="form-control" id="Параметр А" value="' + one + '">');
        $('#params2').append('<label for="Параметр В" class="col-sm-7 control-label">Параметр В</label>');
        $('#params2').append('<input type="text" class="form-control" id="Параметр В" value="' + two + '">');
        $('#params2').append('<label for="Параметр C" class="col-sm-7 control-label">Параметр C</label>');
        $('#params2').append('<input type="text" class="form-control" id="Параметр C" value="' + three + '">');
        $('#params2').append('<label for="Параметр D" class="col-sm-7 control-label">Параметр D</label>');
        $('#params2').append('<input type="text" class="form-control" id="Параметр D" value="' + four + '">');
    }
    function criteriaParamsClick(id) {
        var obj = {};
        obj["id"] = id;
        var string = JSON.stringify(obj);
        $.ajax({
            type: 'POST',
            url: '/com/admin/criteria',
            contentType: 'application/json; charset=utf-8',
            data: string,
            dataType: 'json',
            success: function (result)
            {
                
                document.getElementById(result.formula).selected = "true";
                
                if (result.parameters.length === 2) {
                    setNullParametersForThree(result.parameters[0].value, result.parameters[1].value);
                }
                if (result.parameters.length === 3) {
                    setNullParametersForThree(result.parameters[0].value, result.parameters[1].value, result.parameters[2].value);
                }
                if (result.parameters.length === 4) {
                    setNullParametersForThree(result.parameters[0].value, result.parameters[1].value, result.parameters[2].value, result.parameters[3].value);
                }
                
                for (var i = 0; i < result.thermlist.length; i++)
                {
                    $('#therm').append('<option id="' + result.thermlist[i].id + '">' + result.thermlist[i].name + '</option>');
                }               
                document.getElementById(result.therms).selected = "true";
                var elem = document.getElementById("weight_factor");
                elem.value = result.weighFactor;
                elem = document.getElementById("id");
                elem.value = result.id;
                document.getElementById("demo").addEventListener("click", sendNewCriterias);            
                $('#Mymodal').modal();
            },
            error: function ()
            {
                alert("try again later");
            }
        });

    }
    function getChangedValues()
    {
        var elem = document.getElementById("id");
        var obj = {};
        obj["id"] = elem.value;
        elem = document.getElementById("weight_factor");
        obj["weight_factor"] = elem.value;
        elem = document.getElementById("formula");
        obj["formula"] = elem.options[elem.selectedIndex].text;
        elem = document.getElementById("therm");
        obj["therm"] = elem.options[elem.selectedIndex].id;
        item = new Array();
        var ff = document.getElementById("params2");
        for (var i = 0; i < ff.elements.length; i++) {
            var args = {name: ff.elements[i].id, value: ff.elements[i].value};
            item.push(args);
        }
        obj["params"] = item;
        return JSON.stringify(obj);
    }
    function sendNewCriterias() {
        $.ajax({
            type: 'POST',
            url: '/com/admin/updateCriteria',
            contentType: 'application/json',
            data: getChangedValues(),
            success: function(result)
            {
                $('#therm option').remove();
                reload();
            },
            error: function ()
            {
                $('#therm option').remove();
                reload();
                alert("try again later");
            }
        });
    }
</script>

