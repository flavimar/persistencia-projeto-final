<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Usuario" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.TipoSexo" %><%--
  Created by IntelliJ IDEA.
  User: gianc
  Date: 08/02/2023
  Time: 04:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    TipoSexo tipoSexo = (TipoSexo) request.getAttribute("sexo");
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
    <h1>Editar Usuário</h1>
    <div class="card">
        <div class="card-body">
            <form method="post" action="inserir" class="row g-3">
                <div class="col-md-4">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" class="form-control" id="nome" value="<%=usuario.getNome()%>" >
                </div>
                <div class="col-md-4">
                    <label for="data_nasc" class="form-label">Data de Nascimento</label>
                    <input type="text" name="data_nasc" class="form-control" id="data_nasc" value="<%=usuario.getDataNasc()%>" >
                </div>
                <div class="col-md-4">
                    <label for="sexo" class="form-label">Sexo</label>
                    <select name="sexo" class="form-control" id="sexo" aria-label="Default select example" >
                        <option value="" selected>Todas</option>
                        <option <%=tipoSexo != null && tipoSexo.equals(tipoSexo.M)? "selected" : null %>  value="M">Masculino</option>
                        <option <%=tipoSexo != null && tipoSexo.equals(tipoSexo.F)? "selected" : null %>  value="F">Feminino</option>
                    </select>
                </div>

                <div class="form-group col-md-9">
                    <label for="logradouro" class="form-label">Logradouro</label>
                    <input type="text" name="logradouro" class="form-control" id="logradouro" value="<%=usuario.getLogradouro()%>">
                </div>
                <div class="form-group col-md-3">
                    <label for="numero" class="form-label">Número</label>
                    <input type="text" name="numero" class="form-control" id="numero" value="<%=usuario.getNumero()%>" >
                </div>

                <div class="form-group col-md-4">
                    <label for="setor" class="form-label">Setor</label>
                    <input type="text" name="setor" class="form-control" id="setor" value="<%=usuario.getSetor()%>" >
                </div>
                <div class="form-group col-md-4">
                    <label for="cidade" class="form-label">Cidade</label>
                    <input type="text" name="cidade" class="form-control" id="cidade" value="<%=usuario.getCidade()%>" >
                </div>
                <div class="form-group col-md-4">
                    <label for="uf" class="form-label">Estado</label>
                    <select name="uf" class="form-select"  id="uf" aria-label="Default select example">
                        <option selected>Selecione</option>
                        <option value="AC" <%=usuario.getUf() != null && usuario.getUf().equals("AC")? "selected" : null %>>Acre</option>
                        <option value="AL" <%=usuario.getUf() != null && usuario.getUf().equals("AL")? "selected" : null %>>Alagoas</option>
                        <option value="AM" <%=usuario.getUf() != null && usuario.getUf().equals("AM")? "selected" : null %>>Amazonas</option>
                        <option value="AP" <%=usuario.getUf() != null && usuario.getUf().equals("AP")? "selected" : null %>>Amapá</option>
                        <option value="BA" <%=usuario.getUf() != null && usuario.getUf().equals("BA")? "selected" : null %>>Bahia</option>
                        <option value="CE" <%=usuario.getUf() != null && usuario.getUf().equals("CE")? "selected" : null %>>Ceará</option>
                        <option value="DF" <%=usuario.getUf() != null && usuario.getUf().equals("DF")? "selected" : null %>>Distrito Federal</option>
                        <option value="ES" <%=usuario.getUf() != null && usuario.getUf().equals("ES")? "selected" : null %>>Espírito Santo</option>
                        <option value="GO" <%=usuario.getUf() != null && usuario.getUf().equals("GO")? "selected" : null %>>Goiás</option>
                        <option value="MA" <%=usuario.getUf() != null && usuario.getUf().equals("MA")? "selected" : null %>>Maranhão</option>
                        <option value="MG" <%=usuario.getUf() != null && usuario.getUf().equals("MG")? "selected" : null %>>Minas Gerais</option>
                        <option value="MS" <%=usuario.getUf() != null && usuario.getUf().equals("MS")? "selected" : null %>>Mato Grosso do Sul</option>
                        <option value="MT" <%=usuario.getUf() != null && usuario.getUf().equals("MT")? "selected" : null %>>Mato Grosso</option>
                        <option value="PA" <%=usuario.getUf() != null && usuario.getUf().equals("PA")? "selected" : null %>>Pará</option>
                        <option value="PB" <%=usuario.getUf() != null && usuario.getUf().equals("PB")? "selected" : null %>>Paraíba</option>
                        <option value="PE" <%=usuario.getUf() != null && usuario.getUf().equals("PE")? "selected" : null %>>Pernambuco</option>
                        <option value="PI" <%=usuario.getUf() != null && usuario.getUf().equals("PI")? "selected" : null %>>Piauí</option>
                        <option value="PR" <%=usuario.getUf() != null && usuario.getUf().equals("PR")? "selected" : null %>>Paraná</option>
                        <option value="RJ" <%=usuario.getUf() != null && usuario.getUf().equals("RJ")? "selected" : null %>>Rio de Janeiro</option>
                        <option value="RN" <%=usuario.getUf() != null && usuario.getUf().equals("RN")? "selected" : null %>>Rio Grande do Norte</option>
                        <option value="RO" <%=usuario.getUf() != null && usuario.getUf().equals("RO")? "selected" : null %>>Rondônia</option>
                        <option value="RR" <%=usuario.getUf() != null && usuario.getUf().equals("RR")? "selected" : null %>>Roraima</option>
                        <option value="RS" <%=usuario.getUf() != null && usuario.getUf().equals("RS")? "selected" : null %>>Rio Grande do Sul</option>
                        <option value="SC" <%=usuario.getUf() != null && usuario.getUf().equals("SC")? "selected" : null %>>Santa Catarina</option>
                        <option value="SP" <%=usuario.getUf() != null && usuario.getUf().equals("SP")? "selected" : null %>>São Paulo</option>
                        <option value="SE" <%=usuario.getUf() != null && usuario.getUf().equals("SE")? "selected" : null %>>Sergipe</option>
                        <option value="TO" <%=usuario.getUf() != null && usuario.getUf().equals("TO")? "selected" : null %>>Tocantins</option>
                        <option value="99" <%=usuario.getUf() != null && usuario.getUf().equals("99")? "selected" : null %>>Estrangeiro</option>
                    </select>
                </div>

                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
