package dao;

import model.Client;
import model.OlderCar;
import model.Rent;
import model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RentDAO {

    public void addRentOfOlderCar(Rent rent, String idOlderCar){
        String sql1 = "UPDATE older_car SET state = 'Alugado', id_client = ? WHERE id = ?";
        String sql = "INSERT INTO rent(date_rent, id_older_car, id_client) VALUES (?, ?, ?)";
        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, rent.getDate());
            pst.setString(2, idOlderCar);
            pst.setString(3,rent.getClient().getId());
            pst.executeUpdate();
            pst = con.prepareStatement(sql1);
            pst.setString(1, rent.getClient().getId());
            pst.setString(2, idOlderCar);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void recoverRentByOlderCar(Rent rent, String idOlderCar){
        String sql = "SELECT * FROM rent WHERE id_older_car = ? AND date_devolution = null";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, idOlderCar);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                rent.setDate(rs.getDate("date_rent"));
                rent.setDateDevolution(rs.getDate("date_devolution"));
                rent.setParcel(rs.getInt("parcel"));
                rent.setValue(rs.getFloat("value_rent"));
                rent.setMileage(rs.getFloat("mileage"));
                rent.setId(rs.getString("id"));
                Client client = new Client();
                client.recoverClient(rs.getString("id_client"));
                rent.setClient(client);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recoverRentActualByOlderCar(Rent rent, String idOlderCar){
        String sql = "SELECT * FROM rent WHERE id_older_car = ? AND date_devolution IS NULL";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, idOlderCar);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                rent.setDate(rs.getDate("date_rent"));
                rent.setDateDevolution(rs.getDate("date_devolution"));
                rent.setParcel(rs.getInt("parcel"));
                rent.setValue(rs.getFloat("value_rent"));
                rent.setMileage(rs.getFloat("mileage"));
                rent.setId(rs.getString("id"));
                Client client = new Client();
                client.recoverClient(rs.getString("id_client"));
                rent.setClient(client);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Rent> recoverSalesByClient(String id) {
        String sql = "SELECT * FROM rent WHERE id_client = ?";
        ArrayList<Rent> rents = new ArrayList<Rent>();

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Rent rent = new Rent();
                rent.setId(rs.getString("id"));
                rent.setDate(rs.getDate("date_rent"));
                rent.setDateDevolution(rs.getDate("date_devolution"));
                rent.setParcel(rs.getInt("parcel"));
                rent.setValue(rs.getFloat("value_rent"));
                rent.setMileage(rs.getFloat("mileage"));

                rents.add(rent);
            }
            con.close();
            return rents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Rent> recoverRentsByClient(String id) {
        String sql = "SELECT * FROM rent WHERE id_client = ?";
        ArrayList<Rent> rents = new ArrayList<Rent>();

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Rent rent = new Rent();
                rent.setId(rs.getString("id"));
                rent.setDate(rs.getDate("date_rent"));
                rent.setValue(rs.getFloat("value_rent"));
                rent.setParcel(rs.getInt("parcel"));

                rents.add(rent);
            }
            con.close();
            return rents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void finalizeRent(OlderCar car){
        String sql = "UPDATE rent SET date_devolution = ?, mileage = ?, parcel = ?, value_rent = ? WHERE id = ? AND id_older_car = ? ";
        String updateCar = "UPDATE older_car SET state = 'Disponivel', mileage = ? WHERE id = ?";
        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, car.getRent().getDateDevolution());
            pst.setFloat(2, car.getRent().getMileage());
            pst.setInt(3, car.getRent().getParcel());
            pst.setFloat(4, car.getRent().getValue());
            pst.setString(5, car.getRent().getId());
            pst.setString(6, car.getId());
            pst.executeUpdate();
            pst = con.prepareStatement(updateCar);
            pst.setFloat(1, car.getMileage() + car.getRent().getMileage());
            pst.setString(2, car.getId());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
