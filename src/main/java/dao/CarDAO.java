package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.*;

public class CarDAO {

    public void addCar(Car car){
        String sql = "INSERT INTO car(name, value_car, year_car, path_img, description, mark, model, state)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, car.getName());
            pst.setFloat(2, car.getValue());
            pst.setInt(3, car.getYear());
            pst.setString(4, car.getPathImage());
            pst.setString(5, car.getDesc());
            pst.setString(6, car.getMark());
            pst.setString(7, car.getModel());
            pst.setString(8, car.getState());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editCar(Car car){
        String sql = "UPDATE car SET name = ?, value_car = ?, year_car = ?, " +
                "path_img = ? , description = ?, mark = ?, model = ?, state = ? WHERE id = ?";
        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, car.getName());
            pst.setFloat(2, car.getValue());
            pst.setInt(3, car.getYear());
            pst.setString(4, car.getPathImage());
            pst.setString(5, car.getDesc());
            pst.setString(6, car.getMark());
            pst.setString(7, car.getModel());
            pst.setString(8, car.getState());
            pst.setString(9, car.getId());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteCar(String id){
        String sql = "DELETE FROM car WHERE id = ?";
        try {
            Connection con = new DAO().conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void recoverCar(Car car){
        String sql = "SELECT * FROM car WHERE id = ?";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, car.getId());

            ResultSet rs = pstm.executeQuery();
            if(rs.next()) {
                car.setName(rs.getString("name"));
                car.setYear(rs.getInt("year_car"));
                car.setId(rs.getString("id"));
                car.setValue(rs.getFloat("value_car"));
                car.setPathImage(rs.getString("path_img"));
                car.setDesc(rs.getString("description"));
                car.setMark(rs.getString("mark"));
                car.setModel(rs.getString("model"));
                car.setState(rs.getString("state"));
            }
            Sale sale = new Sale();
            sale.recoverSaleByCar(car.getId());
            car.setSale(sale);
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Car> listCarSold(){
        ArrayList<Car> cars = new ArrayList<Car>();
        String sql = "SELECT * FROM car WHERE state = 'Vendido' ORDER BY name";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Car car = new Car();
                car.setName(rs.getString("name"));
                car.setYear(rs.getInt("year_car"));
                car.setId(rs.getString("id"));
                car.setValue(rs.getFloat("value_car"));
                car.setPathImage(rs.getString("path_img"));
                car.setDesc(rs.getString("description"));
                car.setMark(rs.getString("mark"));
                car.setModel(rs.getString("model"));
                car.setState(rs.getString("state"));

                cars.add(car);
            }
            con.close();
            return cars;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Car> listCarDisponible(){
        ArrayList<Car> cars = new ArrayList<Car>();
        String sql = "SELECT * FROM car WHERE state = 'Disponivel' ORDER BY name";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Car car = new Car();
                car.setName(rs.getString("name"));
                car.setYear(rs.getInt("year_car"));
                car.setId(rs.getString("id"));
                car.setValue(rs.getFloat("value_car"));
                car.setPathImage(rs.getString("path_img"));
                car.setDesc(rs.getString("description"));
                car.setMark(rs.getString("mark"));
                car.setModel(rs.getString("model"));
                car.setState(rs.getString("state"));

                cars.add(car);
            }
            con.close();
            return cars;
        } catch(Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public String recoverIdCarBySale(String id) {
        String sql = "SELECT id_car FROM sale WHERE id = ?";

        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            String idCar = null;
            if(rs.next())idCar = rs.getString(1);
            con.close();
            return idCar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Car> listCarSearch(String searchCamp) {
        String sql = "SELECT * FROM car WHERE name LIKE ?;";
        ArrayList<Car> cars = new ArrayList<Car>();
        try {
            Connection con = new DAO().conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%"+searchCamp+"%");

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Car car = new Car();
                car.setName(rs.getString("name"));
                car.setYear(rs.getInt("year_car"));
                car.setId(rs.getString("id"));
                car.setValue(rs.getFloat("value_car"));
                car.setPathImage(rs.getString("path_img"));
                car.setDesc(rs.getString("description"));
                car.setMark(rs.getString("mark"));
                car.setModel(rs.getString("model"));
                car.setState(rs.getString("state"));

                cars.add(car);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(cars.size());
        return cars;
    }
}
