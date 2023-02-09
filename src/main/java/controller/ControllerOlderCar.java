package controller;

import model.Car;
import model.OlderCar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/dashboardOlderCar", "/addOlderCar",
        "/editOlderCar", "/viewOlderCar", "/deleteOlderCar",
        "/selectOlderCar"})
public class ControllerOlderCar extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private boolean login = false;
    public OlderCar olderCar = new OlderCar();

    public ControllerOlderCar() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if(action.equals("/dashboardOlderCar")) {
            dashboardOlderCar(request, response);
        } else if (action.equals("/addOlderCar")) {
            addOlderCar(request, response);
        } else if (action.equals("/editOlderCar")) {
            editOlderCar(request, response);
        } else if (action.equals("/viewOlderCar")) {
            viewOlderCar(request, response);
        } else if (action.equals("/deleteOlderCar")) {
            deleteOlderCar(request, response);
        } else if (action.equals("/selectOlderCar")) {
            selectOlderCar(request, response);
        }
    }

    public void selectOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        olderCar.recoverOlderCar(id);
        request.setAttribute("car", olderCar);
        RequestDispatcher rd = request.getRequestDispatcher("olderCar/editOlderCar.jsp");
        rd.forward(request, response);
    }

    public void deleteOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        olderCar.recoverOlderCar(request.getParameter("id"));
        olderCar.deleteOlderCar(request.getParameter("id"));

        if(olderCar.getState().equals("Disponivel")) {
            response.sendRedirect("home");
        } else {
            response.sendRedirect("dashboardOlderCar");
        }
    }

    public void viewOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        olderCar.recoverOlderCar(id);
        request.setAttribute("car", olderCar);

        RequestDispatcher rd = request.getRequestDispatcher("olderCar/viewOlderCar.jsp");
        rd.forward(request, response);
    }

    public void editOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        float value = Float.parseFloat(request.getParameter("value"));
        float mileage = Float.parseFloat(request.getParameter("mileage"));
        String desc = request.getParameter("desc");
        String model = request.getParameter("model");
        String mark = request.getParameter("mark");
        String pathImage = request.getParameter("imgPath");
        String state = request.getParameter("state");

        olderCar.editOlderCar(name, mark, pathImage, model, desc, year, value, mileage, state, id);
        response.sendRedirect("viewOlderCar?id="+id);
    }

    public void dashboardOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<OlderCar> olderCarsSold = olderCar.listOlderCarSold();
        ArrayList<OlderCar> olderCarsRented = olderCar.listOlderCarRented();

        request.setAttribute("olderCarsSold", olderCarsSold);
        request.setAttribute("olderCarsRented", olderCarsRented);

        RequestDispatcher rd = request.getRequestDispatcher("olderCar/dashboardOlderCar.jsp");
        rd.forward(request, response);
    }

    public void addOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        float value = Float.parseFloat(request.getParameter("value"));
        float mileage = Float.parseFloat(request.getParameter("mileage"));
        String desc = request.getParameter("desc");
        String model = request.getParameter("model");
        String mark = request.getParameter("mark");
        String pathImg = request.getParameter("imgPath");

        olderCar.addOlderCar(name, mark, pathImg, model, desc, year, value, mileage);
        response.sendRedirect("home");
    }
}
