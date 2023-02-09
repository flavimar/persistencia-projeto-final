<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Alergia" %>
<%--
  Created by IntelliJ IDEA.
  User: gianc
  Date: 08/02/2023
  Time: 04:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Alergia alergia = (Alergia) request.getAttribute("alergia");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar alergia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>
<body>
<div class="container">
    <a href="listar">
        <button class="btn btn-primary">
            <i class="bi bi-chevron-left"></i>
            <span>Voltar</span>
        </button>
    </a>
    <h1>Editar alergia</h1>
    <div class="card">
        <div class="card-body">
            <form method="post" action="atualizar" class="row g-3">
                <input type="hidden" name="id" class="form-control" id="id" value="<%=alergia.getId()%>">
                <div class="col-md-6">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" class="form-control" id="nome" value="<%=alergia.getNome()%>" >
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
