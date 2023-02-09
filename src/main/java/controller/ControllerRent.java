package controller;

import model.Car;
import model.Client;
import model.OlderCar;
import model.Rent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;


@WebServlet(urlPatterns = {"/rentCar", "/finalizeRent", "/addRent", "/finalizeRentForm"})
public class ControllerRent extends HttpServlet {

    private static final long serialVersionUID = 1L;
    OlderCar car = new OlderCar();
    Client client = new Client();
    Rent rent = new Rent();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/rentCar")) {
            rentCar(request, response);
        } else if (action.equals("/finalizeRent")) {
            finalizeRent(request, response);
        } else if (action.equals("/addRent")) {
            addRent(request, response);
        } else if (action.equals("/finalizeRentForm")) {
            finalizeRentForm(request, response);
        }
    }

    public void rentCar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        car.recoverOlderCar(request.getParameter("id"));
        ArrayList<Client> clients = new Client().listClients();
        request.setAttribute("car", car);
        request.setAttribute("clients", clients);

        RequestDispatcher rd = request.getRequestDispatcher("rent/addRent.jsp");
        rd.forward(request, response);
    }

    public void finalizeRent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        car.recoverOlderCar(request.getParameter("car"));
        car.getRent().setDateDevolution(new java.sql.Date(System.currentTimeMillis()));
        car.getRent().setParcel(Integer.parseInt(request.getParameter("parcel")));
        car.getRent().setMileage(Float.parseFloat(request.getParameter("mileageRent")));
        rent = car.getRent();
        rent.finalizeRent(car);
        response.sendRedirect("dashboardOlderCar");
    }

    public void finalizeRentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCar = request.getParameter("id");
        car.recoverOlderCar(idCar);
        request.setAttribute("car", car);
        RequestDispatcher rd = request.getRequestDispatcher("rent/finalizeRent.jsp");
        rd.forward(request, response);
    }

    public void addRent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCar = request.getParameter("car");
        client.recoverClient(request.getParameter("client"));
        Date date = new java.sql.Date(System.currentTimeMillis());
        rent.addRent(idCar, client, date);
        response.sendRedirect("home");
    }
}
