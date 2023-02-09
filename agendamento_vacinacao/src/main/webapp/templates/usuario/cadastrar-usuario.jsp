<%--
  Created by IntelliJ IDEA.
  User: gianc
  Date: 08/02/2023
  Time: 04:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Alergia" %>

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
    <h1>Cadastrar Usuário</h1>
    <div class="card">
        <div class="card-body">
            <form method="post" action="inserir" class="row g-3">
                <div class="col-md-6">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" class="form-control" id="nome"  >
                </div>
                <div class="col-md-3">
                    <label for="data_nasc" class="form-label">Data de Nascimento</label>
                    <input type="date" name="data_nasc" class="form-control" id="data_nasc"  >
                </div>
                <div class="col-md-3">
                    <label for="sexo" class="form-label">Sexo</label>
                    <select name="sexo" class="form-control" id="sexo" aria-label="Default select example" >
                        <option selected>Selecione</option>
                        <option value="F">Feminino</option>
                        <option value="M">Masculino</option>
                    </select>
                </div>

                <div class="form-group col-md-6">
                    <label for="logradouro" class="form-label">Logradouro</label>
                    <input type="text" name="logradouro" class="form-control" id="logradouro"  >
                </div>
                <div class="form-group col-md-2">
                    <label for="numero" class="form-label">Número</label>
                    <input type="text" name="numero" class="form-control" id="numero"  >
                </div>

                <div class="form-group col-md-4">
                    <label for="setor" class="form-label">Setor</label>
                    <input type="text" name="setor" class="form-control" id="setor"  >
                </div>

                <div class="form-group col-md-6">
                    <label for="cidade" class="form-label">Cidade</label>
                    <input type="text" name="cidade" class="form-control" id="cidade"  >
                </div>
                <div class="form-group col-md-6">
                    <label for="uf" class="form-label">Estado(UF)</label>
                    <select name="uf" class="form-select"  id="uf" aria-label="Default select example">
                    <option selected>Selecione</option>
                        <option value="AC">Acre</option>
                        <option value="AL">Alagos</option>
                        <option value="AM">Amazonas</option>
                        <option value="AP">Amapá</option>
                        <option value="BA">Bahia</option>
                        <option value="CE">Ceará</option>
                        <option value="DF">Distrito Federal</option>
                        <option value="ES">Espírito Santo</option>
                        <option value="GO">Goiás</option>
                        <option value="MA">Maranhão</option>
                        <option value="MG">Minas Gerais</option>
                        <option value="MS">Mato Grosso do Sul</option>
                        <option value="MT">Mato Grosso</option>
                        <option value="PA">Pará</option>
                        <option value="PB">Paraíba</option>
                        <option value="PE">Pernambuco</option>
                        <option value="PI">Piauí</option>
                        <option value="PR">Paraná</option>
                        <option value="RJ">Rio de Janeiro</option>
                        <option value="RN">Rio Grande do Norte</option>
                        <option value="RO">Rondônia</option>
                        <option value="RR">Roraima</option>
                        <option value="RS">Rio Grande do Sul</option>
                        <option value="SC">Santa Catarina</option>
                        <option value="SP">São Paulo</option>
                        <option value="SE">Sergipe</option>
                        <option value="TO">Tocantins</option>
                        <option value="99">99-Estrangeiro</option>
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
