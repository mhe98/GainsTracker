package com.gt.gainstracker;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.gt.gainstracker.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ActionBarDrawerToggle toggle;

    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // View-binding the Navigation Drawer Toolbar
        setSupportActionBar(binding.sideNavMain.sideNavToolbar);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout,
                binding.sideNavMain.sideNavToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
        }
        binding.sideNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                String title = "";
                int id = item.getItemId();
                item.setChecked(true);

                if (id == R.id.triple_bar_home) {
                    fragment = new HomeFragment();
                    title = getString(R.string.home_fragment_label);
//                } else if (id == R.id.triple_bar_user_profile) {
//                    transaction.replace(R.id.containers, new FirstFragment());
//                    transaction.commit();
                } else if (id == R.id.triple_bar_workout_plan) {
                    fragment = new ViewPlanFragment();
                    title = getString(R.string.view_plan_label);
//                } else if (id == R.id.triple_bar_history) {
//                    transaction.replace(R.id.containers, new FirstFragment());
//                    transaction.commit();
//                } else if (id == R.id.triple_bar_preferences) {
//                    transaction.replace(R.id.containers, new FirstFragment());
//                    transaction.commit();
                } else if (id == R.id.action_settings) {
                    fragment = new SettingsFragment();
                    title = getString(R.string.settings_label);
                }
                replaceFragment(fragment);
                setFragmentTitle(title);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
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
    protected void onPostCreate(Bundle state) {
        super.onPostCreate(state);
        toggle.syncState();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setFragmentTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    // Programmatically replace fragment within the activity's container
    public void replaceFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            // catch exception
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}