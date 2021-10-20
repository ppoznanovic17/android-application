package com.marko_cvijanovic.project.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.marko_cvijanovic.project.databinding.InfoFragmentBinding;
import com.marko_cvijanovic.project.model.data.Car;

import java.util.LinkedList;
import java.util.List;

public class InfoFragment extends Fragment {

    private InfoFragmentBinding binding;
    private CarAdapter carAdapter;

    private CarAdapter.UpdateCarDelegate updateCarDelegate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = InfoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carAdapter = new CarAdapter(new LinkedList<>(), updateCarDelegate);
        binding.carRecyclerview.setAdapter(carAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.carRecyclerview);

    }

    public void updateList(List<Car> cars) { carAdapter.setCars(cars);  }


    @Override
    public void onAttach(@NonNull Context context) {
          super.onAttach(context);
          updateCarDelegate = (CarAdapter.UpdateCarDelegate) context;
    }
}