package controller;

import model.Car;
import model.OlderCar;
import model.Sale;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/dashboardCar", "/addCar", "/viewCar", "/selectCar", "/editCar", "/deleteCar", "/search", "/searchCar", "/searchOlderCar"})
public class ControllerCar extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private boolean login = false;
    public Car car = new Car();
    public Sale sale = new Sale();

    public ControllerCar() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if(action.equals("/dashboardCar")){
            dashboardCar(request, response);
        } else if(action.equals("/addCar")){
            addCar(request, response);
        } else if(action.equals("/selectCar")){
            selectCar(request, response);
        }else if(action.equals("/editCar")){
            editCar(request, response);
        } else if(action.equals("/deleteCar")){
            deleterCar(request, response);
        } else if(action.equals("/viewCar")){
            viewCar(request, response);
        } else if(action.equals("/search")) {
            search(request, response);
        } else if(action.equals("/searchCar")) {
            searchCar(request, response);
        } else if(action.equals("/searchOlderCar")) {
            searchOlderCar(request, response);
        }
    }

    private void searchOlderCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<OlderCar> olderCars = new OlderCar().listOlderCarSearch(request.getParameter("searchCamp"));

        ArrayList<OlderCar> listOlderCarsSolds = new ArrayList<OlderCar>();
        ArrayList<OlderCar> listOlderCarsRented = new ArrayList<OlderCar>();

        for(int i = 0; i < olderCars.size(); i++) {
            if(olderCars.get(i).getState().equals("Vendido")) {
                listOlderCarsSolds.add(olderCars.get(i));
            } else if (olderCars.get(i).getState().equals("Alugado")){
                listOlderCarsRented.add(olderCars.get(i));
            }
        }

        request.setAttribute("olderCarsSold", listOlderCarsSolds);
        request.setAttribute("olderCarsRented", listOlderCarsRented);
        request.setAttribute("search", request.getParameter("searchCamp"));

        RequestDispatcher rd = request.getRequestDispatcher("olderCar/dashboardOlderCar.jsp");
        rd.forward(request, response);
    }

    private void searchCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Car> cars = car.listCarSearch(request.getParameter("searchCamp"));

        request.setAttribute("search", request.getParameter("searchCamp"));
        request.setAttribute("carsSold", cars);

        RequestDispatcher rd = request.getRequestDispatcher("car/dashboardCar.jsp");
        rd.forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Car> cars = car.listCarSearch(request.getParameter("searchCamp"));
        ArrayList<OlderCar> olderCars = new OlderCar().listOlderCarSearch(request.getParameter("searchCamp"));

        request.setAttribute("search", request.getParameter("searchCamp"));
        request.setAttribute("cars", cars);
        request.setAttribute("olderCars", olderCars);

        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }

    public void dashboardCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Car> carsSold = car.listCarSold();
        request.setAttribute("carsSold", carsSold);
        RequestDispatcher rd = request.getRequestDispatcher("car/dashboardCar.jsp");
        rd.forward(request, response);
    }

    public void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        float value = Float.parseFloat(request.getParameter("value"));
        String desc = request.getParameter("desc");
        String model = request.getParameter("model");
        String mark = request.getParameter("mark");
        String pathImg = request.getParameter("imgPath");
        car.addCar(name, mark, pathImg, model, desc, year, value);
        response.sendRedirect("home");
    }

    public void viewCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        car.recoverCar(id);
        request.setAttribute("car", car);
        RequestDispatcher rd = request.getRequestDispatcher("car/viewCar.jsp");
        rd.forward(request, response);
    }

    public void selectCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        car.recoverCar(id);
        request.setAttribute("car", car);
        RequestDispatcher rd = request.getRequestDispatcher("car/editCar.jsp");
        rd.forward(request, response);
    }

    public void editCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        float value = Float.parseFloat(request.getParameter("value"));
        String desc = request.getParameter("desc");
        String model = request.getParameter("model");
        String mark = request.getParameter("mark");
        String pathImg = request.getParameter("imgPath");
        String state = request.getParameter("state");
        car.editCar(id,name, mark, pathImg, model, desc, year, value, state);
        response.sendRedirect("viewCar?id="+id);
    }

    public void deleterCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        car.recoverCar(request.getParameter("id"));
        car.deleteCar(request.getParameter("id"));
        if(car.getState().equals("Vendido")){
            response.sendRedirect("dashboardCar");
        }
        else {
            response.sendRedirect("home");
        }
    }

}
