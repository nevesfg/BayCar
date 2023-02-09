<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Manager" %>
<%@ page import="dao.DAO" %>
<html lang="en">
    <head>
        <link rel="icon" href="img/logo.png">
        <!--Importações do BOOTSTRAP-->

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous" />
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        
    </head>
    <body class="bg-black">
        <div class="vh-100 d-flex justify-content-center align-items-center ">
            <div class="col-md-5 p-5 shadow-sm rounded-5 bg-login">
                <div class="container">
                    <div class="container col-md-6 col-lg-6 col-sm-8 col-xl-5 col-8">
                        <img src="img/logo.png" class="centered col-md-12 col-lg-12 col-sm-12 col-xl-12 col-12">
                    </div>
                </div>
                <form>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label fw-bold" style="color:  rgb(177, 13, 13);">Email</label>
                        <input name="email" required type="email" class="form-control border border-primary" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Digite o email">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label fw-bold" style="color:  rgb(177, 13, 13);">Senha</label>
                        <input name="password" required type="password" class="form-control border border-primary" id="exampleInputPassword1" style="border: 1px solid red;" placeholder="Digite a senha">
                    </div>
                    <div class="col-sm-12 form-group mb-0 mt-3 flex d-flex justify-content-around">
                        <button class="btn btn-primary col-md-6 col-10">Login</button>
                  </div>
                </form>
                <%
                    String email = request.getParameter("email");
                    String pass = request.getParameter("password");
                    Manager manager = new Manager(email, pass);
                    DAO managerDAO = new DAO();
                    managerDAO.verify(manager);
                    if (manager.getId() != null) {
                        System.out.println("Redirecting");
                        response.sendRedirect("home");
                        session.setAttribute("manager", manager.getEmail());
                        session.setAttribute("pass", manager.getPassword());
                    }
                %>
            </div>
        </div>
    </body>

</html>

