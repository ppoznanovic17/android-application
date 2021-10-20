package com.marko_cvijanovic.project.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.marko_cvijanovic.project.databinding.InputFragmentBinding;
import com.marko_cvijanovic.project.model.data.Car;

public class InputFragment extends Fragment {

    private InputFragmentBinding binding;

    public interface CarDelegate {
        void insertCar(Car car);
        void clearAll();
    }
    private CarDelegate carDelegate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = InputFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.insertButton.setOnClickListener(v -> {

            String name = binding.carEdittext.getText().toString().trim();
            String amount = binding.priceEdittext.getText().toString().trim();
            double price = Double.parseDouble(amount);
            String licence = binding.licenceEdittext.getText().toString().trim();

            Car newcar = new Car(name, price, licence, true);
            carDelegate.insertCar(newcar);
            binding.carEdittext.setText("");
            binding.priceEdittext.setText("0.00");
            binding.licenceEdittext.setText("");

        });

        binding.clearButton.setOnClickListener(v -> {
            carDelegate.clearAll();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        carDelegate = (CarDelegate)context;
    }
}
