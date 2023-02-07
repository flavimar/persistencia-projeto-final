<%--
  Created by IntelliJ IDEA.
  User: flavi
  Date: 02/02/2023
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.ufg.persistencia.agendamento_vacinacao.model.Vacina" %>
<%@ page import="java.util.List" %>
<%
    List<Vacina> vacinas = (List<Vacina>) request.getAttribute("vacinas");
%>
<!DOCTYPE html>
<html lang="en">
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
    <h1>Cadastrar Agenda</h1>
    <div class="card">
        <div class="card-body">
            <form method="post" action="inserir" class="row g-3">
                <div class="col-md-6">
                    <label for="situacao" class="form-label">Situação</label>
                    <select class="form-select" onchange="test()" name="situacao" id="situacao" aria-label="Default select example" required>
                        <option selected>Selecione</option>
                        <option value="AGENDADO">Agendado</option>
                        <option value="CANCELADO">Cancelado</option>
                        <option value="REALIZADO">Realizado</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="vacina" class="form-label">Vacina</label>
                    <select class="form-select" name="vacina" id="vacina" aria-label="Default select example" required>
                        <option selected>Selecione</option>
                        <%for(Vacina vacina : vacinas){%>
                        <option value="<%=vacina.getId()%>"><%=vacina.getTitulo()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="data" class="form-label">Data</label>
                    <input type="date" name="data" class="form-control" id="data" placeholder="dd/mm/aaaa" >
                </div>
                <div class="col-md-6">
                    <label for="hora" class="form-label">Hora</label>
                    <input type="time" name="hora" class="form-control" id="hora">
                </div>

                <div class="col-md-12">
                    <label for="obs" class="form-label">Observações</label>
                    <textarea class="form-control" name="obs" id="obs" rows="3"></textarea>
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
        var hora = document.querySelector("#hora");
        var data = document.querySelector("#data");
        //var vacina = document.querySelector("#vacina");
        p = document.getElementById("situacao").value;
        console.log(p)
        if(p !== 'AGENDADO' && p !== 'Selecione'){
            hora.disabled = true;
            data.disabled = true;
            //vacina.disapled = true;
        }else{
           hora.disabled = false;
            data.disabled = false;
            //vacina.disapled = false;
        }
    }


</script>
</html>