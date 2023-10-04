package com.gt.gainstracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gt.gainstracker.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonGoToViewPlanFragment = view.findViewById(R.id.button_view_plan);

        buttonGoToViewPlanFragment.setOnClickListener(view1 -> NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_HomeFragment_to_ViewPlanFragment));

        Button buttonGoToCreatePlanFragment = view.findViewById(R.id.button_create_plan);

        buttonGoToCreatePlanFragment.setOnClickListener(view2 -> NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_HomeFragment_to_CreatePlanFragment));
    }

    @Override
    public void onResume() {
        super.onResume();

        // Get the TextView
        TextView dateTextView = getView().findViewById(R.id.dateTextView);

        // Get the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MM-dd-yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        // Set the current date in the TextView
        dateTextView.setText(currentDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}