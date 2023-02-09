<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.Car" %>
<%@ page import="model.OlderCar" %>
<%@ page import="java.util.ArrayList" %>

<%
        String manager = (String) session.getAttribute("manager");
        if(manager == null){
            response.sendRedirect("login.jsp");
        }

        ArrayList<Car> listCars = (ArrayList<Car>) request.getAttribute("cars");
        ArrayList<OlderCar> listOlderCars = (ArrayList<OlderCar>) request.getAttribute("olderCars");
  %>
<!DOCTYPE html>
<html lang="en">
<head >
    <meta charset="UTF-8"/>
    <link rel="icon" href="img/logo.png">
    <!--Importações do BOOTSTRAP-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous" />
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/6a5dd2730c.js"></script>
    <title>BayCar</title>
</head>
<body class=" border border-0 bg-black">
<div class="logo-container p-2 d-flex">
    <div class="nav-line" id="logo">
          <span class="">
            <a class="home" href="home">
              <img width="115px" height="110px" src="img/logo.png" class="rounded-circle" />
            </a>
          </span>
    </div>
    <div class="flex-shrink-1 mt-4 d-flex justify-content-end col-md-6 col-12 pe-3">
        <form class="d-flex m-auto col-8 mt-0 me-3" action="search" name="campoBusca">
            <input class="form-control mr-2 ml-4" value="<%= request.getAttribute("search") %>" type="search" placeholder="Pesquisar" aria-label="Search" name="searchCamp" required>
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
        <div class="d-inline-block p-2 col-md-12 m-auto bg-red roundend-3 mt-2"> <h3 class="text-light text-center">Carros Novos  <a href="car/addCar.jsp"><button class="btn-lg p-1 pe-2 ps-2 me-2 fs-4 rounded-2"><i class="fa-solid fa-circle-plus"></i></button></a> </h3></div>
        <%if(listCars.isEmpty() == true){%>
             <div class="d-inline-block p-2  col-md-12 m-auto bg-red roundend-3 mb-2"> <h3 class="fs-3 text-light text-center">Não há carros disponiveis no momento!</h3></div>
        <%}else{%>
        <% for(int i = 0; i < listCars.size(); i++){%>
        <div class=" card mt-2 col-12 p-1 border-top-0 pt-0 border-2 border-dark mb-4" style="background-color: #444d55">
            <div class="row g-0 mt-2 bg-login">
                <div class="mt-0 p-2 m-auto bg-red row">
                    <div class="card-title d-flex m-auto col-4 fw-bold text-light" style="color: white; font-size: large;"><%= listCars.get(i).getName()%></div>
                    <div class="d-flex justify-content-start m-auto col-5" style="color: white; font-size: x-large;">R$ <%= listCars.get(i).getValue()%></div>
                    <div class="col-3">
                        <%if(listCars.get(i).getState().equals("Disponivel")) {%>
                        <div class="d-flex justify-content-end m-auto">
                            <a href="saleCar?id=<%=listCars.get(i).getId()%>">
                                <button class="btn-lg p-1 pe-2 ps-2 me-2 fs-4 rounded-2"><i class="fa-solid fa-cart-shopping"></i></button>
                            </a>
                        </div>
                        <%}%>
                    </div>
                </div>
                <div class="col-12 col-md-4 mt-2">
                   <a href="viewCar?id=<%=listCars.get(i).getId()%>">
                       <img
                               class="card-img-top p-4 bg-dark border-top border-end border-start border-2 border-dark"
                               width="100%"
                               height="100%"
                               src="imgCars/<%= listCars.get(i).getPathImage()%>"
                       />
                   </a>
                </div>

                <div class="d-flex col-md-8 col-12">
                    <div class="card-body bg-dark col-12 p-2 border-2 border-dark">
                        <div class="container">
                            <div class="row">
                                <div class="col-6 justify-content-center">
                                    <div class="text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Ano</span> <span class="d-block"><%= listCars.get(i).getYear()%></span></div>
                                </div>
                                <div class="col-6 justify-content-center">
                                    <div class="text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Marca</span> <span class="d-block"><%= listCars.get(i).getMark()%></span></div>
                                </div>
                                <div class="col-6 justify-content-center">
                                    <div class="text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Modelo</span> <span class="d-block"><%= listCars.get(i).getModel()%></span></div>
                                </div>
                                <div class="col-6 justify-content-center col-2">
                                    <div class=" text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Status</span> <span class="d-block"><%=listCars.get(i).getState()%></span></div>
                                </div>

                                <div>
                                    <p class=" fw-bold col-md-12 text-light m-2  p-1" >Descrição</p>
                                    <div disabled style="height: 100px; max-height: 100px; min-height: 100px;" class="form-control m-2  border border-light">
                                        <%= listCars.get(i).getDesc() %> </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%}
        }%>
        <div class="d-inline-block col-md-12 p-2 m-auto bg-red roundend-3 mt-2"> <h3 class="text-light text-center">Carros Usados <a href="olderCar/addOlderCar.jsp"><button class="btn-lg p-1 pe-2 ps-2 me-2 fs-4 rounded-2"><i class="fa-solid fa-circle-plus"></i></button></a> </h3> </div>
        <%if(listOlderCars.isEmpty() == true){%>
        <div class="d-inline-block p-2 col-md-12 m-auto bg-red roundend-3 mb-2"> <h3 class="fs-3 text-light text-center">Não há carros disponiveis no momento!</h3></div>
        <%}else{
         for(int i = 0; i < listOlderCars.size(); i++){%>
        <div class="card mt-2 col-12 p-1 bg-red border-top-0 pt-0 border-2 border-dark mb-4" style="background-color: #444d55">
            <div class="row g-0 mt-2 bg-login">
                <div class="mt-0 p-2 m-auto bg-red row">
                    <div class="card-title d-flex m-auto col-3 fw-bold text-light" style="color: white; font-size: large;"> <%= listOlderCars.get(i).getName() %></div>
                    <div class="d-flex justify-content-start m-auto col-6" style="color: white; font-size: x-large;">R$ <%= listOlderCars.get(i).getValue() %></div>
                    <div class="col-3">
                        <div class="d-flex justify-content-end m-auto">
                            <%if(listOlderCars.get(i).getState().equals("Disponivel")) {%>
                            <a href="saleOlderCar?id=<%= listOlderCars.get(i).getId() %>">
                                <button class="btn-lg p-1 pe-2 ps-2 me-2 fs-4 rounded-2"><i class="fa-solid fa-cart-shopping"></i></button>
                            </a>
                            <a href="rentCar?id=<%= listOlderCars.get(i).getId() %>">
                                <button class="btn-lg p-1 fs-4 pe-2 ps-2 rounded-2"><i class="fa-solid fa-key"></i></button>
                            </a>
                            <%}%>


                        </div>

                    </div>
                </div>
                <div class="col-12 col-md-4 mt-2">
                    <a href="viewOlderCar?id=<%= listOlderCars.get(i).getId() %>">
                        <img
                                class="card-img-top p-4 bg-dark bg-dark border-top border-end border-start border-2 border-dark"
                                width="100%"
                                height="100%"
                                src="imgCars/<%= listOlderCars.get(i).getPathImage()%>"
                        />
                    </a>
                </div>

                <div class="d-flex col-md-8 col-12">
                    <div class="card-body col-12 p-2 border-2 border-dark">
                        <div class="container">
                        <div class="row">
                            <div class="col-6 justify-content-center">
                                <div class="text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Ano</span> <span class="d-block"><%= listOlderCars.get(i).getYear() %></span></div>
                            </div>
                            <div class="col-6 justify-content-center">
                                <div class="text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Quilometragem</span> <span class="d-block"><%= listOlderCars.get(i).getMileage() %> </span></div>
                            </div>
                            <div class="col-6 col-md-4 justify-content-center">
                                <div class="text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Modelo</span> <span class="d-block"><%= listOlderCars.get(i).getModel() %></span></div>
                            </div>
                            <div class="col-6 col-md-4 justify-content-center">
                                <div class="text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Status</span> <span class="d-block"><%= listOlderCars.get(i).getState() %></span></div>
                            </div>
                            <div class="col-6 col-md-4 justify-content-center col-2">
                                <div class=" text-center col-md-12 text-light m-2  p-1 border border-light border-top-0 border-start-0 border-end-0 border-bottom-2" style="font-size: large;"> <span class="d-block fw-bold">Marca</span> <span class="d-block"><%= listOlderCars.get(i).getMark() %></span></div>
                            </div>

                            <div>
                                <p class=" fw-bold col-md-12 text-light m-2  p-1" >Descrição</p>
                                <div disabled style="height: 100px; max-height: 100px; min-height: 100px;" class="form-control m-2  border border-light">
                                <%= listOlderCars.get(i).getDesc() %>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%}
        }%>
    </div>
</div>
</body>
</html>