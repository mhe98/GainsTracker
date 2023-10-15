package com.gt.gainstracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gt.gainstracker.databinding.FragmentCreatePlanBinding;

public class CreatePlanFragment extends Fragment {

    private static final String KEY_GENDER = "gender";
    private static final String KEY_AGE = "age";
    private static final String KEY_PROGRAM_LENGTH = "program_length";
    private static final String KEY_GOALS = "goals";

    private Spinner genderSpinner, ageSpinner, programLengthSpinner, goalsSpinner;

    private FragmentCreatePlanBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreatePlanBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Set up the gender spinner
        String[] genders = {"", "Male", "Female", "Other"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.genderSpinner.setAdapter(genderAdapter);

        // Set up the age spinner
        String[] ages = {"", "18-25", "26-35", "36-45"};
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, ages);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ageSpinner.setAdapter(ageAdapter);

        // Set up the program length spinner
        String[] programLengths = {"", "1 month", "3 months", "6 months", "12 months"};
        ArrayAdapter<String> programLengthAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, programLengths);
        programLengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.programLengthSpinner.setAdapter(programLengthAdapter);

        // Set up the goals spinner
        String[] goals = {"", "Get Strong", "Lose Weight"};
        ArrayAdapter<String> goalsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, goals);
        goalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.goalsLabelSpinner.setAdapter(goalsAdapter);

        // Set up the submit button click listener
        binding.buttonCreatePlan.setOnClickListener(view -> {
            // Perform an action when the submit button is clicked
            // For example, navigate to another fragment
            NavHostFragment.findNavController(CreatePlanFragment.this)
                    .navigate(R.id.action_CreatePlanFragment_to_HomeFragment);
        });

        // Initialize spinners and button
        genderSpinner = rootView.findViewById(R.id.genderSpinner);
        ageSpinner = rootView.findViewById(R.id.ageSpinner);
        programLengthSpinner = rootView.findViewById(R.id.programLengthSpinner);
        goalsSpinner = rootView.findViewById(R.id.goalsLabelSpinner);

        // Set OnClickListener for the submit button
        binding.buttonSubmit.setOnClickListener(v -> {
            // Get selected values from spinners
            String selectedGender = genderSpinner.getSelectedItem().toString();
            String selectedAge = ageSpinner.getSelectedItem().toString();
            String selectedProgramLength = programLengthSpinner.getSelectedItem().toString();
            String selectedGoals = goalsSpinner.getSelectedItem().toString();

            SharedPreferences preferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_GENDER, selectedGender);
            editor.putString(KEY_AGE, selectedAge);
            editor.putString(KEY_PROGRAM_LENGTH, selectedProgramLength);
            editor.putString(KEY_GOALS, selectedGoals);
            editor.apply();

            Toast.makeText(getActivity(), "Data saved!", Toast.LENGTH_SHORT).show();
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release the binding when the fragment view is destroyed
    }
}
