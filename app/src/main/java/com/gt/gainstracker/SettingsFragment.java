package com.gt.gainstracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gt.gainstracker.databinding.FragmentSettingsBinding;

import android.content.Context;
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SwitchCompat switchMode = view.findViewById(R.id.switchMode);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean("nightMode", false);


        if(nightMode) {
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        if (switchMode != null) {
            switchMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nightMode) {
                        switchMode.setChecked(false);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        editor = sharedPreferences.edit();
                        editor.putBoolean("nightMode", false);
                    } else {
                        switchMode.setChecked(true);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        editor = sharedPreferences.edit();
                        editor.putBoolean("nightMode", true);
                    }
                    editor.apply();
                }
            });
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}