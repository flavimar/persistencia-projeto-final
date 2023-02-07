<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Agenda" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.TipoSituacao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Agenda> agendas = (List<Agenda>) request.getAttribute("lista");
    TipoSituacao tipoSituacao = (TipoSituacao)request.getAttribute("filtro");
    System.out.println(tipoSituacao);
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    int cout = 1;
%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>
<body>
<div class="container">
    <a href="${pageContext.request.contextPath}/index.jsp">
        <button class="btn btn-primary">
            <i class="bi bi-chevron-left"></i>
            <span>Voltar</span>
        </button>
    </a>
    <a href="inserir">
        <button class="btn btn-primary">Cadastrar</button>
    </a>
    <%=request.getAttribute("ms") != null?"<h2 class='text-danger'>"+request.getAttribute("ms")+"</h2":""%>
    <h1>Listagem de Agendamento</h1>
    <div class="row">
        <form method="get" action="listar" class="row g-3">
        <div class="col-md-4">
            <select class="form-select" name="filtro" id="filtro" aria-label="Default select example">
                <option value="" selected>Todas</option>
                <option <%=tipoSituacao != null && tipoSituacao.equals(TipoSituacao.CANCELADO)? "selected" : null %>  value="CANCELADO">Canceladas</option>
                <option <%=tipoSituacao != null && tipoSituacao.equals(TipoSituacao.REALIZADO)? "selected" : null %>  value="REALIZADO">Realizadas</option>
            </select>
        </div>
        <div class="col-md-3">
            <input class="btn btn-danger" type="submit" value="filtrar">
        </div>
    </form>
    </div>
    <table class="table table-striped">
        <thead class="text-center">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Data</th>
            <th scope="col">Hora</th>
            <th scope="col">Situação</th>
            <th scope="col">data da Situação</th>
            <th scope="col">Observações</th>
            <th scope="col">excluir</th>
            <th scope="col">editar</th>
        </tr>
        </thead>
        <tbody class="text-center">
        <% for(Agenda agenda : agendas) { %>
        <tr>
            <th scope="row"><%=cout++ %></th>
            <td><%=agenda.getData() != null?formatter.format(agenda.getData()): "-"%></td>
            <td><%=agenda.getHora() != null? agenda.getHora(): "-"%></td>
            <td><%=agenda.getSituacao()%></td>
            <td><%=agenda.getDataSituacao() != null?formatter.format(agenda.getDataSituacao()):"-"%></td>
            <td><%=agenda.getObservacao() != null? agenda.getObservacao():"-"%></td>
            <td> <a href="remover?id=<%=agenda.getId()%>"><i class="bi bi-trash"></i> </a> </td>
            <td> <a href="atualizar?id=<%=agenda.getId()%>"> <i class="bi bi-pencil-square"></i> </a> </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
