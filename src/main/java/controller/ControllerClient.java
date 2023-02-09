package controller;

import dao.ClientDAO;
import model.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/searchClient", "/addClient", "/dashboardClient", "/deleteClient", "/viewClient","/selectClient", "/editClient"})
public class ControllerClient extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Client client = new Client();
    Sale sale = new Sale();
    Rent rent = new Rent();

    public ControllerClient(){
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/addClient")) {
            addClient(response, request);
        } else if (action.equals("/dashboardClient")) {
            dashboardClient(response, request);
        } else if (action.equals("/deleteClient")) {
            deleteClient(response, request);
        } else if (action.equals("/viewClient")) {
            viewClient(response, request);
        } else if (action.equals("/searchClient")) {
            searchClient(response, request);
        } else if (action.equals("/selectClient")) {
            selectClient(response ,request);
        } else if (action.equals("/editClient")) {
            editClient(response ,request);
        }
    }

    private void searchClient(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        ArrayList<Client> clients = client.listClientsSearch(request.getParameter("searchCamp"));
        request.setAttribute("clients", clients);

        RequestDispatcher rd = request.getRequestDispatcher("client/dashboardClient.jsp");
        rd.forward(request, response);
    }

    private void viewClient(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        client.recoverClient(request.getParameter("id"));
        ArrayList<Sale> sales = sale.recoverSalesByClient(client.getId());
        ArrayList<Rent> rents = rent.recoverRentByClient(client.getId());
        request.setAttribute("sales", sales);
        request.setAttribute("rents", rents);
        request.setAttribute("client", client);

        RequestDispatcher rd = request.getRequestDispatcher("client/viewClient.jsp");
        rd.forward(request, response);
    }

    private void deleteClient(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        client.deleteClient(request.getParameter("id"));

        response.sendRedirect("dashboardClient");
    }

    private void addClient(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String adress = request.getParameter("adress");
        String phone = request.getParameter("phone");
        String cpf = request.getParameter("cpf");

        client.addClient(name, email, adress, phone, cpf);

       response.sendRedirect("dashboardClient");
    }

    private void dashboardClient(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        ArrayList<Client> clients = client.listClients();
        request.setAttribute("clients", clients);

        RequestDispatcher rd = request.getRequestDispatcher("client/dashboardClient.jsp");
        rd.forward(request, response);
    }

    private void selectClient(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        client.recoverClient(request.getParameter("id"));
        request.setAttribute("client", client);
        RequestDispatcher rd = request.getRequestDispatcher("client/editClient.jsp");
        rd.forward(request, response);
    }

    private void editClient(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String adress = request.getParameter("adress");
        String phone = request.getParameter("phone");
        String cpf = request.getParameter("cpf");

        client.editClient(id, name, email, adress, phone, cpf);

        response.sendRedirect("viewClient?id="+id);
    }
}

