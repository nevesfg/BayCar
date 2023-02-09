package controller;

import model.Car;
import model.Client;
import model.OlderCar;
import model.Sale;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/saleCar", "/saleOlderCar", "/addSaleCar", "/addSaleOlderCar"})
public class ControllerSale extends HttpServlet {

    private static final long serialVersionUID = 1L;
    OlderCar olderCar = new OlderCar();
    Car car = new Car();
    Client client = new Client();
    Sale sale = new Sale();
    public ControllerSale() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if(action.equals("/saleCar")) {
            saleCar(request, response);
        } else if(action.equals("/addSaleCar")) {
            addSaleCar(request, response);
        } else if(action.equals("/saleOlderCar")) {
            saleOlderCar(request, response);
        } else if(action.equals("/addSaleOlderCar")) {
            addSaleOlderCar(request, response);
        }
    }

    public void saleOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        olderCar.recoverOlderCar(request.getParameter("id"));
        ArrayList<Client> clients = client.listClients();

        request.setAttribute("car", olderCar);
        request.setAttribute("clients", clients);

        RequestDispatcher rd = request.getRequestDispatcher("sale/addSale.jsp");
        rd.forward(request, response);
    }

    public void saleCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        car.recoverCar(request.getParameter("id"));
        ArrayList<Client> clients = client.listClients();
        request.setAttribute("car", car);
        request.setAttribute("clients", clients);

        RequestDispatcher rd = request.getRequestDispatcher("sale/addSale.jsp");
        rd.forward(request, response);
    }

    public void addSaleCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCar = request.getParameter("car");
        client.recoverClient(request.getParameter("client"));
        float valueCar = Float.parseFloat(request.getParameter("value"));
        int parcel = Integer.parseInt(request.getParameter("parcel"));
        Date date = new java.sql.Date(System.currentTimeMillis());

        sale.addSaleCar(date, parcel, idCar, client, valueCar);
        response.sendRedirect("home");
    }

    public void addSaleOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCar = request.getParameter("car");
        client.recoverClient(request.getParameter("client"));
        float valueCar = Float.parseFloat(request.getParameter("value"));
        int parcel = Integer.parseInt(request.getParameter("parcel"));
        Date date = new java.sql.Date(System.currentTimeMillis());

        sale.addSaleOlderCar(date, parcel, idCar, client, valueCar);
        response.sendRedirect("home");
    }
}
