package com.marko_cvijanovic.project.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marko_cvijanovic.project.databinding.CarItemLayoutBinding;
import com.marko_cvijanovic.project.model.data.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarsViewHolder> {

    private List<Car> cars;

    private UpdateCarDelegate updateCarDelegate;

    public CarAdapter(List<Car> cars) {
        this.cars = cars;
    }

    public CarAdapter(List<Car> cars, UpdateCarDelegate updateCarDelegate){
        this.cars = cars;
        this.updateCarDelegate = updateCarDelegate;
    }


    public interface UpdateCarDelegate{
        void updateCar(Car car);
        void removeCar(Car car);
    }


    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CarItemLayoutBinding binding = CarItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CarsViewHolder(binding);
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        Car car = cars.get(position);

        holder.binding.carTextview.setText((car.getCarName()));
        holder.binding.priceTextview.setText(car.getCarPrice()+"$ per day.");
        holder.binding.licenceTextview.setText(car.getLicence());
        holder.binding.idTextview.setText("#ID00"+car.getCarId());

        if(car.getAvailable()){
            holder.binding.availText.setText("Available");
        }
        else  holder.binding.availText.setText("Not Available");

        holder.binding.availButton.setOnClickListener(v -> {
            updateCarDelegate.updateCar(car);
        });

        holder.binding.removeButton.setOnClickListener(v -> {
            updateCarDelegate.removeCar(car);
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class CarsViewHolder extends RecyclerView.ViewHolder{
        CarItemLayoutBinding binding;
        public CarsViewHolder(CarItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
