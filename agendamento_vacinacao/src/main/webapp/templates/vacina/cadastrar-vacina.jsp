<%--
  Created by IntelliJ IDEA.
  User: flavi
  Date: 03/02/2023
  Time: 00:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>
<body>
<div class="container">
    <a href="../index.jsp">
        <button class="btn btn-primary">
            <i class="bi bi-chevron-left"></i>
            <span>Voltar</span>
        </button>
    </a>
    <h1>Cadastrar Vacina</h1>
    <div class="card">
        <div class="card-body">
            <form method="post" action="inserir" class="row g-3">
                <div class="col-md-6">
                    <label for="titulo" class="form-label">Título</label>
                    <input type="text" name="titulo" class="form-control" id="titulo" placeholder="exemplo" >
                </div>
                <div class="col-md-6">
                    <label for="doses" class="form-label">Quantidade de doses</label>
                    <input type="number" name="doses" class="form-control" id="doses">
                </div>
                <div class="col-md-6">
                    <label for="periodicidade" class="form-label">Periodicidade</label>
                    <select class="form-select" name="periodicidade" id="periodicidade" aria-label="Default select example">
                        <option selected>Selecione</option>
                        <option value="DIA">Dia</option>
                        <option value="SEMANA">Semena</option>
                        <option value="MES">Mês</option>
                        <option value="ANO">Ano</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="intervalo" class="form-label">Intervalo</label>
                    <input type="number" name="intervalo" class="form-control" id="intervalo">
                </div>


                <div class="col-md-12">
                    <label for="descricao" class="form-label">Descrição</label>
                    <textarea class="form-control" name="descricao" id="descricao" rows="3"></textarea>
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
