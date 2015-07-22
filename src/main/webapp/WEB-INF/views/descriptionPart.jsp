<%-- 
    Document   : descriptionPart
    Created on : 12.06.2015, 20:26:41
    Author     : Володя
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
      <div class="row col-md-12">

  <!-- Navigation Buttons -->
  <div class="col-md-2">
    <ul class="nav nav-tabs nav-stacked" id="KnowledgeTabs" >
      <li class="active"><a href="#intab1">Уточнення по вибору системи</a></li>
      <li><a href="#intab2" onclick="cleanTherms();loadTherms();">Матриця нечітких знань</a></li>
      <li><a href="#intab3">Опис систем заводнення</a></li>
    </ul>
  </div>

  <!-- Content -->
  <div class="col-md-10">
    <div class="tab-content">
      <div class="tab-pane active" id="intab1"></div>
      <div class="tab-pane" id="intab2"><%@ include file="unclearPart.jsp" %></div>
      <div class="tab-pane" id="intab3"></div>
    </div>
  </div>

</div>
</div><!-- /container -->
<script charset="utf-8" type="text/javascript" >
$('#KnowledgeTabs a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
  });
</script>
