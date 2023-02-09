<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String manager = (String) session.getAttribute("manager");
    if(manager == null){
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head >
    <meta charset="UTF-8"/>
    <link rel="icon" href="../img/logo.png">
    <!--Importações do BOOTSTRAP-->

    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous" />
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/6a5dd2730c.js"></script>
    <title>BayCar</title>
</head>
<body class="bg-black border border-0">
<div class="logo-container p-2 d-flex">
    <div class="nav-line" id="logo">
          <span class="">
            <a class="home" href="../home">
              <img width="115px" height="110px" src="../img/logo.png" class="rounded-circle" />
            </a>
          </span>
    </div>
    <div class="flex-shrink-1 mt-4 d-flex justify-content-end col-md-6 col-12 pe-3">
        <form class="d-flex m-auto col-8 mt-0 me-3" action="searchCar" name="campoBusca">
            <input class="form-control mr-2 ml-4" type="search" placeholder="Pesquisar" aria-label="Search" name="searchCamp" required>
            <button class="btn" style="background-color: rgb(177, 13, 13);" type="submit"><i class="fa-sharp fa-solid fa-magnifying-glass" style="color: white;"></i></button>
        </form>
        <span class="col-3 col-md-2 mt-1">
            <a class="text-red p-2 col-4 border border-1 border-red rounded-pill" href="../logout.jsp">Logout</a>
          </span>
    </div>
</div>
<div class="container mt-3 ">
    <nav class="navbar navbar-dark navbar-expand-lg rounded-3 mb-3 bg-red-gradient pb-2">
        <div class="container-fluid">
            <a class="navbar-brand text-light" style="margin-left: 10px">BayCar</a>
            <button
                    class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <span class="navbar-toggler-icon my-toggle"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="../home" style="margin-left: 10px"
                        >Home</a
                        >
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="../dashboardCar" style="margin-left: 10px"
                        >Carros Novos</a
                        >
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="../dashboardOlderCar" style="margin-left: 10px"
                        >Carros Usados</a
                        >
                    </li>
                    <li class="nav-item">
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="../dashboardClient" style="margin-left: 10px"
                        >Clientes</a
                        >
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <form class="bg-dark" action="../addCar">
        <div class="row jumbotron box8 p-4 border-form m-0">
            <div class="col-sm-12 mx-t3 mb-4">
                <div class="col-10 col-md-6 m-auto bg-red-gradient p-1 rounded-2">
                    <h2 class="text-center text-light"> Adicionar Carro</h2>
                </div>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="name">Nome do Carro</label>
                <input type="text" maxlength="30" class="form-control" name="name" id="name" placeholder="Digite o nome do carro." required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="value">Valor do Carro</label>
                <input type="text" maxlength="10" class="form-control border-form" name="value" id="value" placeholder="Digite o valor do carro." required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="year">Ano de Fabricação</label>
                <input type="number"  min="1900" class="form-control" name="year" id="year" placeholder="Digite o ano de fabricação." required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="imgPath">Imagem</label>
                <input type="text" class="form-control"  id="imgPath" name="imgPath" placeholder="Digite o nome da imagem." required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="mark"> Marca </label>
                <input type="text" maxlength="30" class="form-control" name="mark" id="mark" placeholder="Digite a marca." required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="model"> Modelo </label>
                <input type="text" maxlength="30" class="form-control" name="model" id="model" placeholder="Digite o modelo." required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="desc"> Descrição</label>
                <textarea class="form-control" maxlength="400" name="desc" id="desc" placeholder="Digite a descrição." required style="max-height: 120px; height: 120px"> </textarea>
            </div>
            <div class="col-sm-12 form-group mb-0 mt-3 flex d-flex justify-content-end">
                <button class="btn btn-primary float-right">Adicionar</button>
            </div>
        </div>
    </form>
    <script>
        const input = document.getElementById("value");

        input.addEventListener("keyup", formatMoed);

        function formatMoed(e) {
            var v = e.target.value.replace(/\D/g,"");
            v = (v/100).toFixed(2) + "";
            v = v.replace(".", ".");
            v = v.replace(/(\d)(\d{3})(\d{3}),/g, "$1.$2.$3,");
            v = v.replace(/(\d)(\d{3}),/g, "$1.$2,");
            e.target.value = v;
        }
    </script>
</div>
</body>