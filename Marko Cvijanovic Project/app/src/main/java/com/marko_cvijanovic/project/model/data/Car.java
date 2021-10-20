package com.marko_cvijanovic.project.model.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cars")
public class Car{

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCarPrice(double pricePerDay) {
        this.carPrice = pricePerDay;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setLicence(String licence) { this.licence = licence;   }

    public int getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public String getLicence(){ return licence;}

    public boolean getAvailable(){ return available;}

    @Override
    public String toString(){
        return "Car " +
                "name= " + carName + '\'' +
                ", id= " + carId +
                ", licence= " + licence +
                ", rent= " + carPrice +
                ", Availability =" + available +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "carId")
    private int carId;

    @ColumnInfo(name = "carName")
    private String carName;

    @ColumnInfo(name = "carPrice")
    private double carPrice;

    @ColumnInfo(name ="available")
    private boolean available;

    @ColumnInfo(name = "licence")
    private String licence;

    public Car(int carId, String carName, double carPrice, boolean available) {
        this.carId = carId;
        this.carName = carName;
        this.carPrice = carPrice;
        this.available = available;
    }


    @Ignore
    public Car(int carId, String carName, double carPrice, String licence) {
        this.carId = carId;
        this.carName = carName;
        this.carPrice = carPrice;
        this.licence = licence;
    }

    @Ignore
    public Car(String carName, double pricePerDay, String licence, boolean available) {
        this.carName = carName;
        this.carPrice = pricePerDay;
        this.licence = licence;
        this.available = available;
    }


}
