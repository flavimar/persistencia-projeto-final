<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Agenda" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.TipoSituacao" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Vacina" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: flavi
  Date: 26/01/2023
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Agenda agenda = (Agenda) request.getAttribute("agenda");
    List<Vacina> vacinas = (List<Vacina>) request.getAttribute("vacinas");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    <h1>Cadastrar Agenda</h1>
    <div class="card">
        <div class="card-body">
            <form method="post" action="atualizar" class="row g-3">
                <input type="hidden" name="id" class="form-control" id="id" value="<%=agenda.getId()%>">
                <div class="col-md-6">
                    <label for="usuario" class="form-label">Usuario do agendamento</label>
                    <input class="form-control" id="usuario" name="usuario" value="<%=agenda.getUsuario().getNome()%>">
                </div>
                <div class="col-md-6">
                    <label for="vacina" class="form-label">Vacina</label>
                    <select class="form-select" name="vacina" id="vacina" aria-label="Default select example" required>
                        <option selected value="<%=agenda.getVacina().getId()%>"><%=agenda.getVacina().getTitulo()%></option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="situacao" class="form-label">Situação</label>
                    <select class="form-select" name="situacao" id="situacao" aria-label="Default select example">
                        <option>Situacao</option>
                        <option  <%=agenda.getSituacao().equals(TipoSituacao.AGENDADO)? "selected" : null %> value="AGENDADO">Agendado</option>
                        <option <%=agenda.getSituacao().equals(TipoSituacao.CANCELADO)? "selected" : null %> value="CANCELADO">Cancelado</option>
                        <option <%=agenda.getSituacao().equals(TipoSituacao.REALIZADO)? "selected" : null %> value="REALIZADO">Realizado</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="data" class="form-label">Data</label>
                    <input type="date" name="data" class="form-control" id="data" placeholder="dd/mm/aaaa" value="<%=agenda.getData() != null?sdf.format(agenda.getData()):""%>">
                </div>
                <div class="col-md-4">
                    <label for="hora" class="form-label">Hora</label>
                    <input type="time" name="hora" class="form-control" id="hora" value="<%=agenda.getHora() != null?agenda.getHora():""%>">
                </div>
                <div class="col-md-12">
                    <label for="obs" class="form-label">Observações</label>
                    <textarea class="form-control" name="obs" id="obs" rows="3"><%=agenda.getObservacao() != null? agenda.getObservacao():""%></textarea>
                </div>
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">Editar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
        var hora = document.querySelector("#hora");
        var data = document.querySelector("#data");
        var vacina = document.querySelector("#vacina");
        var usuario = document.querySelector("#usuario");

        hora.disabled = true;
        data.disabled = true;
        vacina.disabled = true;
        usuario.disabled = true;
</script>
</html>
