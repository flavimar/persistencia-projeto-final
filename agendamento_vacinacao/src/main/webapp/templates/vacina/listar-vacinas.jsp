<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Vacina" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: flavi
  Date: 02/02/2023
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Vacina> vacinas = (List<Vacina>) request.getAttribute("lista");
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
    <%if(vacinas.size() > 0){%>
    <h1>Listagem de Vacinas</h1>
    <table class="table table-striped">
        <thead class="text-center">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Titulo</th>
            <th scope="col">Doses</th>
            <th scope="col">Periodiciade</th>
            <th scope="col">Intervalo</th>
            <th scope="col">Descrição</th>
            <th scope="col">excluir</th>
            <th scope="col">editar</th>
        </tr>
        </thead>
        <tbody class="text-center">
        <% for(Vacina vacina : vacinas) { %>
        <tr>
            <th scope="row"><%=cout++ %></th>
            <td><%=vacina.getTitulo()%></td>
            <td><%=vacina.getDoses()%></td>
            <td><%=vacina.getPeriodicidade() != null? vacina.getPeriodicidade():"-"%></td>
            <td><%=vacina.getIntervalo() != 0? vacina.getIntervalo():"-"%></td>
            <td><%=vacina.getDescricao() != null? vacina.getDescricao():"-"%></td>
            <td> <a href="remover?id=<%=vacina.getId()%>"><i class="bi bi-trash"></i> </a> </td>
            <td> <a href="atualizar?id=<%=vacina.getId()%>"> <i class="bi bi-pencil-square"></i> </a> </td>
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
