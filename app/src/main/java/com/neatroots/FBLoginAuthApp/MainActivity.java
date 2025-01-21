package com.neatroots.FBLoginAuthApp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import fragements.FavoritesFragment;
import fragements.NotificationFragment;
import fragements.ProfileFragment;
import fragements.RecipesFragment;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // Set the listener for the BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (itemId == R.id.navigation_products) {
                loadFragment(new RecipesFragment());
                return true;
            } else if (itemId == R.id.navigation_favorites) {
                loadFragment(new FavoritesFragment());
                return true;
            } else if (itemId == R.id.navigation_notification) {
                loadFragment(new NotificationFragment());
                return true;
            } else if (itemId == R.id.navigation_Profile) {
                loadFragment(new ProfileFragment());
                return true;
            }
            return false;
        });

        // Load the default fragment (HomeFragment) when the activity starts
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());  // Initially load HomeFragment
        }
    }

    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_activity_home_main, fragment) // Replace the existing fragment
                .commit(); // Commit the transaction

    }
}
