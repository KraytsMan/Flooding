<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Waterflooding admin</title>  
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    </head>
    <body style="background-color:#D3D3D3">
        <div class="container">
            <div class="row">
                <div class="navbar navbar-inverse">
                    <div class="container">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="javascript:window.location.reload();">Waterflooding admin</a>                           
                        </div>
                        <div class="collapse navbar-collapse">
                            <form action="" class="navbar-form navbar-right">
                                <button type="submit" class="btn btn-primary">Вихід
                                </button>
                                <button type="button" class="btn btn-primary"><i class="icon-large icon-search"></i>Довідка
                                </button>
                            </form>                                                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="container" >
            <div class="row">
                <div class="col-md-12" >
                    <div class="tabs" style="background-color:#FFFFFF">
                        <ul class="nav nav-tabs nav-justified">
                            <li class="active"><a href="#tab1" data-toggle="tab">Параметри критеріїв</a></li>
                            <li><a href="#tab2" data-toggle="tab" >Таблиця критеріїв застосовності</a></li>
                            <li><a href="#tab3" data-toggle="tab">База знань</a></li>
                        </ul>
                        <br>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab1" data-toggle="tab"> 
                                <div class="container">
                                    <div class="row col-md-12">
                                        <%@include file="criteriaParams.jsp" %>
                                        <%@include file="cellParams.jsp" %>
                                        <%@include file="newTherm.jsp" %>
                                        <%@include file="deleteTherm.jsp" %>
                                        <%@include file="newCriteria.jsp" %>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab2">
                            </div>
                            <div class="tab-pane" id="tab3">
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>                   
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        
        <footer style="height: 50px"></footer>
    </body>
</html>

