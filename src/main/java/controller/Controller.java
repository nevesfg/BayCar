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

@WebServlet(urlPatterns = {"/login", "/home"})
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private boolean login = false;
    public Car car = new Car();
    public OlderCar olderCar = new OlderCar();
    public Controller() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
            if(action.equals("/login")){
                response.sendRedirect("login.jsp");
            }
            else if(action.equals("/home")){
                home(request, response);
            }
            else {
                response.sendRedirect("home.jsp");
            }
    }

    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Car> cars = car.listCarDisponible();
        ArrayList<OlderCar> olderCars = olderCar.listOlderCarDisponible();

        request.setAttribute("search", "");
        request.setAttribute("cars", cars);
        request.setAttribute("olderCars", olderCars);
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }
}