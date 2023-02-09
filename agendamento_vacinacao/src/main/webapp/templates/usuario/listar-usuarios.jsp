<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.TipoSexo" %>
<%--
  Created by IntelliJ IDEA.
  User: gianc
  Date: 08/02/2023
  Time: 04:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("lista");
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
    <h1>Listagem de Usuários</h1>
    <table class="table table-striped">
        <thead class="text-center">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nome</th>
            <th scope="col">Data de Nascimento</th>
            <th scope="col">Sexo</th>
            <th scope="col">Setor</th>
            <th scope="col">Cidade</th>
            <th scope="col">excluir</th>
            <th scope="col">editar</th>
        </tr>
        </thead>
        <tbody class="text-center">
        <% for(Usuario usuario : usuarios) { %>
        <tr>
            <th scope="row"><%=count++ %></th>
            <td><%=usuario.getNome()%></td>
            <td><%=formatter.format(usuario.getDataNasc())%></td>
            <td><%=usuario.getSexo()%></td>
            <td><%=usuario.getSetor()%></td>
            <td><%=usuario.getCidade()%></td>
            <td> <a href="remover?id=<%=usuario.getId()%>"><i class="bi bi-trash"></i> </a> </td>
            <td> <a href="atualizar?id=<%=usuario.getId()%>"> <i class="bi bi-pencil-square"></i> </a> </td>
        </tr>
        <% } %>
        </tbody>
    </table>

</div>
</body>
</html>
