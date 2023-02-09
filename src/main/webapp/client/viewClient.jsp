<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.Sale" %>
<%@ page import="model.Rent" %>
<%@ page import="model.Car" %>
<%@ page import="model.OlderCar" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.CarDAO" %>
<%@ page import="dao.OlderCarDAO" %>
<%@ page import="model.Client" %>

<%
    String manager = (String) session.getAttribute("manager");
    if(manager == null){
        response.sendRedirect("login.jsp");
    }
    Client client = (Client) request.getAttribute("client");
    ArrayList<Sale> sales = (ArrayList<Sale>) request.getAttribute("sales");
    ArrayList<Rent> rents = (ArrayList<Rent>) request.getAttribute("rents");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <!--Importações do BOOTSTRAP-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous" />
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
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
        <form class="d-flex m-auto col-8 mt-0 me-3" action="searchCar" name="campoBusca">
            <input class="form-control mr-2 ml-4" type="search" placeholder="Pesquisar" aria-label="Search" name="searchCamp" required>
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
    <div class="bg-dark">
        <div class="row jumbotron box8 p-4 border-form m-0">
            <div class="col-sm-12 mx-t3 mb-4">
                <div class="col-12 col-md-7 m-auto d-flex bg-red-gradient p-1 rounded-2">
                    <h2 class="text-center  m-auto col-sm-6 justify-content-center text-light"> Ver Cliente </h2>
                    <% String id = client.getId(); %>
                    <div class="col-sm-6 form-group m-auto flex d-flex justify-content-center">
                        <a href="selectClient?id=<%= client.getId() %>" class="m-2">
                            <button class="btn btn-light  float-right " style="color: #8f0d0d; font-weight: bold;"><i class="fa-solid fa-pen"></i> Editar</button>
                        </a>

                        <a href="javascript: deleteClient(<%=id%>)" class="m-2">
                            <button class="btn btn-light  float-right " style="color: #8f0d0d; font-weight: bold;"><i class="fa-solid fa-trash"></i> Deletar</button>
                        </a>
                        <script>
                            function deleteClient(id){
                                let resposta = confirm("Confirmar a exclusão desse cliente?");
                                if(resposta === true){
                                    window.location.href = "deleteClient?id=" + id
                                }
                            }
                        </script>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 form-group mb-0 mt-3 flex d-flex justify-content-center">

            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="name">Nome do Cliente</label>
                <input type="text" readonly value="<%= client.getName()  %>" maxlength="30"  max="30" class="form-control" name="name" id="name" placeholder="Digite o nome do Cliente." required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="value">Email do Cliente</label>
                <input type="email" readonly maxlength="30" value="<%= client.getEmail()  %>" class="form-control border-form" name="email" id="value" placeholder="exemplo@gmail.com" required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="phone">Telefone do Cliente</label>
                <input type="text" readonly class="form-control" value="<%= client.getPhone()  %>" name="phone" id="phone" placeholder="(99) 99999-9999"  minlength="14" maxlength="14"  required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="cpf">CPF do Cliente</label>
                <input type="text" readonly class="form-control" value="<%= client.getCpf() %>" id="cpf" name="cpf" placeholder="999.999.999-99" minlength="14" maxlength="14" required>
            </div>
            <div class="col-sm-6 form-group text-light mt-3">
                <label for="desc"> Endereço do Cliente</label>
                <input type="text" readonly class="form-control" value="<%= client.getAdress() %>" id="desc" name="desc" placeholder="Digite o endereço" maxlength="40" required>
            </div>
            <% if(sales.isEmpty() != true){ %>
            <div class="data_table mt-3">
                <div class="col-sm-12 mx-t3 mb-4">
                    <div class="col-8 col-md-4 m-auto bg-red-gradient p-1 rounded-2">
                        <h2 class="text-center text-light fs-4"> Compras </h2>
                    </div>
                </div>
                <table id="table" class="table table-striped">
                    <thead class="table-lig">
                    <tr class="bg-red text-light border-bottom-0">
                        <th class="table-cell col-11 col-sm-3">Carro</th>
                        <th class="d-none d-sm-table-cell col-3">Data</th>
                        <th class="d-none d-md-table-cell col-3">Valor da Venda</th>
                        <th class="d-none d-md-table-cell col-2">Parcelamento</th>
                        <th class="col-1">Ver</th>
                    </tr>
                    </thead>
                    <tbody >

                    <% for (int i = 0; i < sales.size(); i++) { %>
                    <tr class=" table-dark">
                        <% if (new CarDAO().recoverIdCarBySale(sales.get(i).getId()) != null) {%>
                        <td>
                            <% Car car = new Car();
                            car.recoverCar(new CarDAO().recoverIdCarBySale(sales.get(i).getId()));%>
                            <%= car.getName() %>
                        </td>
                        <% }else {%>
                        <td>
                            <% OlderCar olderCar = new OlderCar();
                                olderCar.recoverOlderCar(new OlderCarDAO().recoverIdOlderCarBySale(sales.get(i).getId()));%>
                            <%= olderCar.getName() %>
                        </td>
                        <%}%>
                        <td class="text-light d-none d-sm-table-cell"> <%= sales.get(i).getDate() %></td>
                        <td class="text-light d-none d-md-table-cell"><%= sales.get(i).getValue() %></td>
                        <td class="text-light d-none d-md-table-cell"><%= sales.get(i).getParcel() %></td>
                        <% if (new CarDAO().recoverIdCarBySale(sales.get(i).getId()) != null) {%>
                        <td>
                            <a href="viewCar?id=<%= new CarDAO().recoverIdCarBySale(sales.get(i).getId()) %>"> <button class="btn-lg p-1 pe-2 ps-2 me-2 fs-5 rounded-2"><i class="fa-sharp fa-solid fa-eye"></i></button></a>
                        </td>
                        <% }else {%>
                        <td>
                            <a href="viewOlderCar?id=<%= new OlderCarDAO().recoverIdOlderCarBySale(sales.get(i).getId()) %>"><button class="btn-lg p-1 pe-2 ps-2 me-2 fs-5 rounded-2"><i class="fa-sharp fa-solid fa-eye"></i></button></a>
                        </td>
                        <%}%>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <%} %>

            <% if(rents.isEmpty() != true){ %>
            <div class="data_table mt-3">
                <div class="col-sm-12 mx-t3 mb-4">
                    <div class="col-8 col-md-4 m-auto bg-red-gradient p-1 rounded-2">
                        <h2 class="text-center text-light fs-4"> Alugueis </h2>
                    </div>
                </div>
                <table class="table table-striped">
                    <thead class="table-lig">
                    <tr class="bg-red text-light border-bottom-0">
                        <th class="table-cell col-6 col-sm-2">Carro</th>
                        <th class="d-none d-md-table-cell col-2">Data</th>
                        <th class="table-cell col-6 col-sm-2">Valor do Aluguel</th>
                        <th class="d-none d-md-table-cell col-2">Parcelamento</th>
                        <th class="d-none d-md-table-cell col-2">Data de Entrega</th>
                        <th class="d-none d-md-table-cell col-2">Quilometragem</th>
                    </tr>
                    </thead>
                    <tbody >

                    <% for (int i = 0; i < rents.size(); i++) { %>
                    <tr class=" table-dark">
                        <td>
                            <% OlderCar olderCar = new OlderCar();
                                olderCar.recoverOlderCar( new OlderCarDAO().recoverIdOlderCarByRent(rents.get(i).getId()));
                            %>
                            <%= olderCar.getName() %>
                        </td>
                        <%
                            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(rents.get(i).getDate().toString());
                            String dataFormatada = new SimpleDateFormat("dd-MM-yyyy").format(data);
                            data = new SimpleDateFormat("yyyy-MM-dd").parse(rents.get(i).getDateDevolution().toString());
                            String dataFormatada2 = new SimpleDateFormat("dd-MM-yyyy").format(data);
                        %>
                        <td class="text-light d-none d-md-table-cell"> <%= dataFormatada %></td>
                        <td class="text-light d-md-table-cell"><%= rents.get(i).getValue() %></td>
                        <td class="text-light d-none d-md-table-cell"><%= rents.get(i).getParcel() %></td>
                        <% if(rents.get(i).getDateDevolution() != null){ %>
                        <td class="text-light d-none d-md-table-cell"><%= dataFormatada2 %></td>
                        <% }else{%>
                        <td class="text-light d-none d-md-table-cell">Não devolvido</td>
                        <%}%>
                        <td class="text-light d-none d-md-table-cell"><%= rents.get(i).getMileage()%></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>

            </div>
            <%} %>
        </div>

    <script>
        const input = document.getElementById("cpf");

        input.addEventListener("keyup", formatarCPF);

        function formatarCPF(e){

            var v=e.target.value.replace(/\D/g,"");

            v=v.replace(/(\d{3})(\d)/,"$1.$2");

            v=v.replace(/(\d{3})(\d)/,"$1.$2");

            v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2");

            e.target.value = v;

        }

        const inputPhone = document.getElementById("phone");

        inputPhone.addEventListener("keyup", formatarTelefone);

        function formatarTelefone(e){

            var v=e.target.value.replace(/\D/g,"");

            v=v.replace(/^(\d\d)(\d)/g,"($1) $2");

            v=v.replace(/(\d{5})(\d)/,"$1-$2");

            e.target.value = v;

        }
    </script>
</div>
</body>