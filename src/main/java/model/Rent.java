package model;

import dao.RentDAO;

import java.sql.Date;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit ;

public class Rent extends Sale {
    private Date dateDevolution;
    private float mileage;

    public void addRent(String idCar, Client client, Date date){
        setClient(client);
        setDate(date);
        new RentDAO().addRentOfOlderCar(this, idCar);
    }

    public ArrayList<Rent> recoverRentByClient(String idClient){
        return new RentDAO().recoverSalesByClient( idClient);
    }

    public void recoverRentActualByOlderCar(String idCar){
         new RentDAO().recoverRentActualByOlderCar(this, idCar);
    }

    public void finalizeRent(OlderCar car){
        car.getRent().setValue((float) calculateValue(car.getValue(), car.getRent().getParcel()));
        new RentDAO().finalizeRent(car);
    }

    @Override
    public double calculateValue(float valueCar, int parcel) {
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DAY_OF_MONTH, this.getDate().getDay());
        c1.set(Calendar.MONTH, this.getDate().getMonth());
        c1.set(Calendar.YEAR, this.getDate().getYear());
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.DAY_OF_MONTH, this.getDateDevolution().getDay());
        c2.set(Calendar.MONTH, this.getDateDevolution().getMonth());
        c2.set(Calendar.YEAR, this.getDateDevolution().getYear());
        long days = c2.getTime().getTime() - c1.getTime().getTime();
        days = TimeUnit.MILLISECONDS.toDays(days);
        return valueCar + ( ((valueCar * 0.02) * days) + (15 * parcel));
    }

    public void recoverRentByOlderCar(String idCar){
        new RentDAO().recoverRentByOlderCar(this, idCar);
    }

    public void setDateDevolution(Date dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public Date getDateDevolution() {
        return dateDevolution;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }
}
