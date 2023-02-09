<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Alergia" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: flavi
  Date: 09/02/2023
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Alergia> alergias = (List<Alergia>) request.getAttribute("alergias");
    long usuarioId = Long.parseLong(request.getParameter("id"));
%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>
<body>
<div class="container">
    <div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <h1>Adicionar alergias ao usuario de id <%=usuarioId%></h1>
        <form method="post" action="vincular-alergias" class="row g-3">
            <div class="col-md-12">
                <input type="hidden" name="id" class="form-control" id="id" value="<%=usuarioId%>">
                <input class="form-control" list="datalistOptions" id="alergia" name="alergia">
                <datalist id="datalistOptions">
                    <%for(Alergia alergia : alergias){%>
                    <option value="<%=alergia.getNome()%>"></option>
                    <%}%>
                </datalist>
            </div>
            <div class="col-md-6">
                <a href="listar"><button type="button" class="btn btn-secondary">finalizar</button></a>
            </div>
            <div class="col-md-6">
                <input type="submit" class="btn btn-primary" value="adicionar alergia">
            </div>

        </form>
    </div>
    </div>
</div>
</body>
</html>
