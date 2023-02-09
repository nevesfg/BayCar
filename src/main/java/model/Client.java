package model;

import dao.ClientDAO;
import dao.SaleDAO;

import java.util.ArrayList;

public class Client {
    private String name;
    private String email;
    private String id;
    private String adress;
    private String phone;
    private String cpf;

    public Client() {

    }

    public void addClient(String name, String email, String adress, String phone, String cpf){
        setName(name);
        setEmail(email);
        setAdress(adress);
        setPhone(phone);
        setCpf(cpf);
        new ClientDAO().addClient(this);
    }

    public void editClient(String id, String name, String email, String adress, String phone, String cpf){
        setId(id);
        setName(name);
        setEmail(email);
        setAdress(adress);
        setPhone(phone);
        setCpf(cpf);
        new ClientDAO().editClient(this);
    }

    public ArrayList<Client> listClients(){
       return new ClientDAO().listClient();
    }

    public ArrayList<Client> listClientsSearch(String search) {
        return new ClientDAO().listClientsSearch(search);
    }

    public void recoverClient(String id){
        this.setId(id);
        new ClientDAO().recoverClient(this);
    }

    public void deleteClient(String id){
        new ClientDAO().deleteClient(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
