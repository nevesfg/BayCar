package model;

import dao.CarDAO;
import dao.OlderCarDAO;

import java.util.ArrayList;

public class OlderCar extends Car {
    private float mileage;
    private Rent rent;

    public void addOlderCar(String name, String mark, String pathImage, String model, String desc, int year, float value, float mileage) {
        this.setName(name);
        this.setMark(mark);
        this.setPathImage(pathImage);
        this.setModel(model);
        this.setDesc(desc);
        this.setYear(year);
        this.setValue(value);
        this.setMileage(mileage);
        this.setState("Disponivel");
        new OlderCarDAO().addCar(this);
    }

    public void deleteOlderCar(String id){
        new OlderCarDAO().deleteCar(id);
    }

    public void recoverOlderCar(String id){
        this.setId(id);
        new OlderCarDAO().recoverOlderCar(this);
    }

    public void editOlderCar(String name, String mark, String pathImage, String model, String desc, int year, float value, float mileage, String state,String id) {
        this.setName(name);
        this.setMark(mark);
        this.setPathImage(pathImage);
        this.setModel(model);
        this.setDesc(desc);
        this.setYear(year);
        this.setValue(value);
        this.setMileage(mileage);
        this.setState(state);
        this.setId(id);

        new OlderCarDAO().editCar(this);
    }

    public ArrayList<OlderCar> listOlderCarDisponible(){
        return new OlderCarDAO().listOlderCarDisponible();
    }

    public ArrayList<OlderCar> listOlderCarRented(){
        return new OlderCarDAO().listOlderCarRented();
    }

    public ArrayList<OlderCar> listOlderCarSold(){
        return new OlderCarDAO().listOlderCarSold();
    }

    public ArrayList<OlderCar> listOlderCarSearch(String searchCamp) {
        return new OlderCarDAO().listOlderCarSearch(searchCamp);
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
