<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.Client" %>
<%@ page import="java.util.ArrayList" %>

<%
    String manager = (String) session.getAttribute("manager");
    if(manager == null){
        response.sendRedirect("login.jsp");
    }

    ArrayList<Client> clients = (ArrayList<Client>) request.getAttribute("clients");

%>
<!DOCTYPE html>
<html lang="en">
<head >
    <meta charset="UTF-8"/>
    <link rel="icon" href="img/logo.png">
    <!--Importações do BOOTSTRAP-->

    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous" />
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/6a5dd2730c.js"></script>
    <title>BayCar</title>
</head>
<body class="bg-black border border-0">
<div class="logo-container p-2 d-flex">
    <div class="nav-line" id="logo">
          <span class="">
            <a class="home" href="home">
              <img width="115px" height="110px" src="img/logo.png" class="rounded-circle" />
            </a>
          </span>
    </div>
    <div class="flex-shrink-1 mt-4 d-flex justify-content-end col-md-6 col-12 pe-3">
        <form class="d-flex m-auto col-8 mt-0 me-3" action="searchClient" name="campoBusca">
            <input class="form-control mr-2 ml-4" type="search" placeholder="Pesquisar Cliente" aria-label="Search" name="searchCamp" required>
            <button class="btn" style="background-color: rgb(177, 13, 13);" type="submit"><i class="fa-sharp fa-solid fa-magnifying-glass" style="color: white;"></i></button>
        </form>
        <span class="col-3 col-md-2 mt-1">
            <a class="text-red p-2 col-4 border border-1 border-red rounded-pill" href="logout.jsp">Logout</a>
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
                        <a class="nav-link text-light" aria-current="page" href="home" style="margin-left: 10px"
                        >Home</a
                        >
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="dashboardCar" style="margin-left: 10px"
                        >Carros Novos</a
                        >
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="dashboardOlderCar" style="margin-left: 10px"
                        >Carros Usados</a
                        >
                    </li>
                    <li class="nav-item">
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="dashboardClient" style="margin-left: 10px"
                        >Clientes</a
                        >
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="row d-flex rounded">
            <div class="container mt-2 pt-3 background">
                <div class="row">
                    <div class="col-12 col-sm-12 col-md-12 col-lg-12 m-auto">

                        <div class="card shadow bg-dark p-3 container scroll">
                            <div class="container">
                                <div class="col-12 col-md-12 col-lg-12 col-sm-12">

                                    <div class="data_table">
                                        <div class="col-sm-12 mx-t3 mb-4">
                                            <div class="col-8 col-md-4 m-auto bg-red-gradient p-1 rounded-2">
                                                <h2 class="text-center text-light"> Clientes  <a href="client/addClient.jsp"><button class="btn-lg p-1 pe-2 ps-2 me-2 fs-4 rounded-2"><i class="fa-solid fa-circle-plus"></i></button></a></h2>
                                            </div>
                                        </div>
                                        <table id="table" class="table table-striped">
                                            <thead class="table-lig">
                                            <tr class="bg-red text-light border-bottom-0">
                                                <th class="table-cell col-11 col-sm-3">Nome</th>
                                                <th class="d-none d-sm-table-cell col-3">Telefone</th>
                                                <th class="d-none d-md-table-cell col-3">Email</th>
                                                <th class="d-none d-md-table-cell col-2">CPF</th>
                                                <th class="col-1">Ver</th>
                                            </tr>
                                            </thead>
                                            <tbody >

                                        <% for(int i =0; i < clients.size(); i++){ %>
                                            <tr class=" table-dark">
                                                <td class="text-light"> <%= clients.get(i).getName()%></td>
                                                <td class="text-light d-none d-sm-table-cell"> <%= clients.get(i).getPhone()%></td>
                                                <td class="text-light d-none d-md-table-cell"> <%= clients.get(i).getEmail() %></td>
                                                <td class="text-light d-none d-md-table-cell"> <%= clients.get(i).getCpf()%></td>
                                                <td>
                                                    <a href="viewClient?id=<%=clients.get(i).getId()%>"><button class="btn-lg p-1 pe-2 ps-2 me-2 fs-5 rounded-2"><i class="fa-sharp fa-solid fa-eye"></i></button></a>
                                                </td>
                                            </tr>
                                        <% } %>
                                            </tbody>
                                        </table>
                                        <script src="js/scripts.js"></script>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>

</div>
</body>
</html>
