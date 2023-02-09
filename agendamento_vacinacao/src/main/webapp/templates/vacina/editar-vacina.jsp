<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Vacina" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Periodicidade" %><%--
  Created by IntelliJ IDEA.
  User: flavi
  Date: 02/02/2023
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Vacina vacina = (Vacina) request.getAttribute("vacina");
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
    <h1>Editar Vacina</h1>
    <div class="card">
        <div class="card-body">
            <form method="post" action="atualizar" class="row g-3">
                <input type="hidden" name="id" class="form-control" id="id" value="<%=vacina.getId()%>">
                <div class="col-md-6">
                    <label for="titulo" class="form-label">Título</label>
                    <input type="text" name="titulo" class="form-control" id="titulo" value="<%=vacina.getTitulo()%>" required>
                </div>
                <div class="col-md-6">
                    <label for="doses" class="form-label">Quantidade de doses</label>
                    <input type="number" name="doses" onchange="test()" class="form-control" id="doses" value="<%=vacina.getDoses()%>">
                </div>
                <div class="col-md-6">
                    <label for="periodicidade" class="form-label">Periodicidade</label>
                    <select class="form-select" name="periodicidade" id="periodicidade" aria-label="Default select example">
                        <option value="" selected>Selecione</option>
                        <option value="DIA" <%=vacina.getPeriodicidade() != null && vacina.getPeriodicidade().equals(Periodicidade.DIA)? "selected" : null %> >Dia</option>
                        <option value="SEMANA" <%=vacina.getPeriodicidade() != null && vacina.getPeriodicidade().equals(Periodicidade.SEMANA)? "selected" : null %>>Semena</option>
                        <option value="MES" <%=vacina.getPeriodicidade() != null && vacina.getPeriodicidade().equals(Periodicidade.MES)? "selected" : null %>>Mês</option>
                        <option value="ANO" <%=vacina.getPeriodicidade() != null && vacina.getPeriodicidade().equals(Periodicidade.ANO)? "selected" : null %>>Ano</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="intervalo" class="form-label">Intervalo</label>
                    <input type="number" name="intervalo" class="form-control" id="intervalo" value="<%=vacina.getIntervalo()%>" required>
                </div>


                <div class="col-md-12">
                    <label for="descricao" class="form-label">Descrição</label>
                    <textarea class="form-control" name="descricao" id="descricao" rows="3"><%=vacina.getDescricao()%></textarea>
                </div>
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    function test(){
        var p = document.querySelector("#periodicidade");
        var i = document.querySelector("#intervalo");
        d = document.getElementById("doses").value;
        console.log(p)
        if(d > 1){
            p.disabled = false;
            i.disabled = false;
        }else{
            p.disabled = true;
            i.disabled = true;
        }
    }
</script>
</html>
