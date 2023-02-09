package dao;

import model.Client;
import model.OlderCar;
import model.Rent;
import model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientDAO {

    public void addClient(Client client){
        String sql = "INSERT INTO client(name, email, phone, adress, cpf) VALUES (?,?,?,?,?)";
        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, client.getName());
            pst.setString(2, client.getEmail());
            pst.setString(3, client.getPhone());
            pst.setString(4, client.getAdress());
            pst.setString(5, client.getCpf());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editClient(Client client){
        String sql = "UPDATE client SET name = ?, email = ?, phone = ?, adress = ?, cpf = ? WHERE id = ?";
        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, client.getName());
            pst.setString(2, client.getEmail());
            pst.setString(3, client.getPhone());
            pst.setString(4, client.getAdress());
            pst.setString(5, client.getCpf());
            pst.setString(6, client.getId());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteClient(String id){
        ArrayList<Sale> sales = new SaleDAO().recoverSalesByClient(id);
        ArrayList<Rent> rents = new RentDAO().recoverRentsByClient(id);
        String sql = "DELETE FROM client WHERE id = ?";
        try {
            Connection con = new DAO().conectar();
            String select = "SELECT * FROM sale WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(select);
            for(int i = 0; i < sales.size(); i++){
                ps.setString(1, sales.get(i).getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    new CarDAO().deleteCar(rs.getString("id_car"));
                    new OlderCarDAO().deleteCar(rs.getString("id_older_car"));
                }
            }
            select = "SELECT * FROM rent WHERE id = ?";
            ps = con.prepareStatement(select);
            for(int i = 0; i < rents.size(); i++){
                ps.setString(1, rents.get(i).getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    if(rs.getDate("date_devolution") == null) {
                        OlderCar car = new OlderCar();
                        car.recoverOlderCar(rs.getString("id_older_car"));
                        new RentDAO().finalizeRent(car);
                    }
                }
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void recoverClient(Client client){
        String sql = "SELECT * FROM client WHERE id = ?";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, client.getId());
            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                client.setName(rs.getString("name"));
                client.setEmail(rs.getString("email"));
                client.setId(rs.getString("id"));
                client.setAdress(rs.getString("adress"));
                client.setPhone(rs.getString("phone"));
                client.setCpf(rs.getString("cpf"));
                con.close();
            } else {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Client> listClient(){
        ArrayList<Client> clis = new ArrayList<Client>();
        String sql = "SELECT * FROM client ORDER BY name";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                Client cli = new Client();
                cli.setName(rs.getString("name"));
                cli.setEmail(rs.getString("email"));
                cli.setId(rs.getString("id"));
                cli.setAdress(rs.getString("adress"));
                cli.setPhone(rs.getString("phone"));
                cli.setCpf(rs.getString("cpf"));
                clis.add(cli);
            }
            con.close();
            return clis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Client> listClientsSearch(String search) {
        ArrayList<Client> clis = new ArrayList<Client>();
        String sql = "SELECT * FROM client WHERE name LIKE ?";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%"+search+"%");

            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                Client cli = new Client();
                cli.setName(rs.getString("name"));
                cli.setEmail(rs.getString("email"));
                cli.setId(rs.getString("id"));
                cli.setAdress(rs.getString("adress"));
                cli.setPhone(rs.getString("phone"));
                cli.setCpf(rs.getString("cpf"));
                clis.add(cli);
            }
            con.close();
            return clis;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}