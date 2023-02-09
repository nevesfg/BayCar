package model;

import dao.CarDAO;

import java.util.ArrayList;

public class Car {
    private float value;
    private String name;
    private int year;
    private String pathImage;
    private String desc;
    private String mark;
    private String model;
    private String id;
    private String state;

    private Sale sale;

    public Car(){}

    public void addCar(String name, String mark, String pathImage, String model, String desc, int year, float value){
        this.setName(name);
        this.setMark(mark);
        this.setPathImage(pathImage);
        this.setModel(model);
        this.setDesc(desc);
        this.setYear(year);
        this.setValue(value);
        this.setState("Disponivel");
        new CarDAO().addCar(this);
    }

    public void editCar(String id, String name, String mark, String pathImage, String model, String desc, int year, float value, String state){
        this.setId(id);
        this.setName(name);
        this.setMark(mark);
        this.setPathImage(pathImage);
        this.setModel(model);
        this.setDesc(desc);
        this.setYear(year);
        this.setValue(value);
        this.setState(state);
        new CarDAO().editCar(this);
    }

    public void deleteCar(String id){
        new CarDAO().deleteCar(id);
    }

    public ArrayList<Car> listCarSearch(String searchCamp) {
        return new CarDAO().listCarSearch(searchCamp);
    }

    public ArrayList<Car> listCarDisponible(){
        return new CarDAO().listCarDisponible();
    }

    public void recoverCar(String id){
        this.setId(id);
        new CarDAO().recoverCar(this);
    }

    public ArrayList<Car> listCarSold(){
        return new CarDAO().listCarSold();
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
