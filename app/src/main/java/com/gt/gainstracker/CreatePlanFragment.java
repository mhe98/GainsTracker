package com.gt.gainstracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gt.gainstracker.databinding.FragmentCreatePlanBinding;

public class CreatePlanFragment extends Fragment {

    private FragmentCreatePlanBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCreatePlanBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCreatePlan.setOnClickListener(view1 -> NavHostFragment.findNavController(CreatePlanFragment.this)
                .navigate(R.id.action_CreatePlanFragment_to_HomeFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}