package com.marko_cvijanovic.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.marko_cvijanovic.project.model.data.Car;
import com.marko_cvijanovic.project.model.db.CarDatabase;
//import com.coolcats.Car_Room_Recycle_fragment.utli.SingletonExample;
import com.marko_cvijanovic.project.view.CarAdapter;
import com.marko_cvijanovic.project.view.InfoFragment;
import com.marko_cvijanovic.project.view.InputFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements InputFragment.CarDelegate, CarAdapter.UpdateCarDelegate {

    private InputFragment inputFragment;
    private InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFragment = (InputFragment) getSupportFragmentManager().findFragmentById(R.id.input_fragment);
        infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.info_fragment);
        readDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        readDatabase();
    }


    @Override
    public void insertCar(Car car) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Log.d("TAG_X", "inserting car");
                CarDatabase.getDatabase(MainActivity.this).getDAO().insertCars(car);
                Log.d("TAG_X", "reading all...");
                readDatabase();
            }
        }.start();
    }

    @Override
    public void clearAll() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                CarDatabase.getDatabase(MainActivity.this).getDAO().nukeTable();
                readDatabase();
            }
        }.start();
    }

    public void switchAvailability(int id){
        new Thread() {
            @Override
            public void run() {

                super.run();
                Log.d("TAG_X", "updating topic");
                CarDatabase.getDatabase(MainActivity.this).getDAO().toggleAvailability(id);
                Log.d("TAG_X", "reading all...");
                readDatabase();

            }
        }.start();
    }

    private void readDatabase() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<Car> cars = CarDatabase.getDatabase(MainActivity.this).getDAO().getAllCars();

                runOnUiThread(() -> {
                    infoFragment.updateList(cars);
                });
                getDisplayUser(cars);
            }
        }.start();
    }

    @Override
    public void updateCar(Car car){
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra("id", car.getCarId());
        i.putExtra("name", car.getCarName());
        i.putExtra("price", car.getCarPrice());
        i.putExtra("licence", car.getLicence());
        startActivity(i);
    }

    @Override
    public void removeCar(Car car) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                CarDatabase.getDatabase(MainActivity.this).getDAO().deleteCar(car);
                readDatabase();
            }
        }.start();
    }

    private void getDisplayUser(List<Car> car) {
        Log.d("TAG_X" , "DataBase: " +"\n");
        for(int i =0; i < car.size(); i++){
            Log.d("TAG_X",  car.get(i).toString());
        }
    }
}