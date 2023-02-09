<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Alergia" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: gianc
  Date: 08/02/2023
  Time: 04:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Alergia> alergias = (List<Alergia>) request.getAttribute("lista");
    int count = 1;
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
    <%if(alergias.size() > 0){%>
    <h1>Listagem de Alergias</h1>
    <table class="table table-striped">
        <thead class="text-center">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nome da Alergia</th>
            <th scope="col">excluir</th>
            <th scope="col">editar</th>
        </tr>
        </thead>
        <tbody class="text-center">
            <% for(Alergia alergia : alergias) { %>
            <tr>
                <th scope="row"><%=count++ %></th>
                <td><%=alergia.getNome()%></td>
                <td> <a href="remover?id=<%=alergia.getId()%>"><i class="bi bi-trash"></i> </a> </td>
                <td> <a href="atualizar?id=<%=alergia.getId()%>"> <i class="bi bi-pencil-square"></i> </a> </td>
            </tr>
            <% } %>
        </tbody>

    </table>
    <%}else{%>
    <div class="center">
        <h1>Não há itens</h1>
    </div>
    <%}%>
</div>
</body>
</html>
