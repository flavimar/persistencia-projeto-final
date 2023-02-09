<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html charset="UTF-8">
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">

</head>
<body>
<div class="container">

    <div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <h1><%= "Menu" %> </h1>
        <ul>
            <li><a href="agenda/listar">Listar agenda</a></li>
            <li><a href="vacina/listar">Listar vacina</a></li>
            <li><a href="usuario/listar">Listar usuário</a></li>
            <li><a href="alergia/listar">Listar alergia</a></li>
        </ul>
    </div>
</div>
</div>
</body>
</html>