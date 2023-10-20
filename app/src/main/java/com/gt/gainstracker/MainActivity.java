package com.gt.gainstracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;
import com.gt.gainstracker.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the TextView
        TextView dateTextView = findViewById(R.id.dateTextView);

        // Get the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MM-dd-yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        // Set the current date in the TextView
        dateTextView.setText(currentDate);

        // View-binding the Navigation Drawer Toolbar
        setSupportActionBar(binding.sideNavMain.sideNavToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout,
                binding.sideNavMain.sideNavToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        
        binding.sideNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                int id = item.getItemId();

                if (id == R.id.triple_bar_home) {
                    transaction.replace(R.id.containers, new HomeFragment());
                    transaction.commit();
//                }
//                else if (id == R.id.triple_bar_user_profile) {
//                    transaction.replace(R.id.containers, new FirstFragment());
//                    transaction.commit();
//                } else if (id == R.id.triple_bar_workout_plan) {
//                    transaction.replace(R.id.containers, new FirstFragment());
//                    transaction.commit();
                } else if (id == R.id.triple_bar_history) {
                    transaction.replace(R.id.containers, new HistoryFragment());
                    transaction.commit();
//                } else if (id == R.id.triple_bar_preferences) {
//                    transaction.replace(R.id.containers, new FirstFragment());
//                    transaction.commit();
                } else if (id == R.id.action_settings) {
                    transaction.replace(R.id.containers, new SettingsFragment());
                    transaction.commit();
                }
                return true;
            }
        });

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}