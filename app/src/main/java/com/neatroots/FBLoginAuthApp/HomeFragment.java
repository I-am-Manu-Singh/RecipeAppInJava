package com.neatroots.FBLoginAuthApp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import adapter.RecipeAdapter;
import fragements.AboutUsFragment;
import fragements.BookingsFragment;
import fragements.ContactUsFragment;
import fragements.PrivacyPolicyFragment;
import fragements.ProfileFragment;
import fragements.RateUsFragment;

public class HomeFragment extends Fragment {


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    //nav bar
    private ConstraintLayout navHome;
    private ConstraintLayout navShareApp;
    private ConstraintLayout navRate;
    private ConstraintLayout navPolicy;
    private ConstraintLayout navContactUs;
    private ConstraintLayout btAboutUs;
    private ConstraintLayout btEditProfile;
    private ConstraintLayout btBookingsView;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize the data and adapter
        List<Recipe> recipes = getDummyData();
        RecipeAdapter adapter = new RecipeAdapter(recipes);
        recyclerView.setAdapter(adapter);

        // Initialize UI Components
        drawerLayout = rootView.findViewById(R.id.drawerLayout);
        navigationView = rootView.findViewById(R.id.nav_drawer);
        ImageView btNavDrawer = rootView.findViewById(R.id.btNavDrawer);
        // UI Components
        TextView showEmail = rootView.findViewById(R.id.showemail);
        Button signOutButton = rootView.findViewById(R.id.signout);
        Button deleteButton = rootView.findViewById(R.id.delete);


        //nav bar items

        ConstraintLayout navHome = rootView.findViewById(R.id.navHome);
        ConstraintLayout navShareApp = rootView.findViewById(R.id.navShareApp);
        ConstraintLayout navRate = rootView.findViewById(R.id.navRate);
        ConstraintLayout navPolicy = rootView.findViewById(R.id.navPolicy);
        ConstraintLayout navContactUs = rootView.findViewById(R.id.navContactUs);
        ConstraintLayout btAboutUs = rootView.findViewById(R.id.btAboutUs);
        ConstraintLayout btEditProfile = rootView.findViewById(R.id.btEditProfile);
        ConstraintLayout btBookingsView = rootView.findViewById(R.id.btBookingsView);
        // Firebase Initialization
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // RecyclerView Setup (if applicable)

        // Set up Navigation Drawer Toggle
        btNavDrawer.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Display Current User's Email
        if (currentUser != null) {
            showEmail.setText(currentUser.getEmail());
        } else {
            redirectToLogin();
        }

        // Sign Out Button Logic
        signOutButton.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(getActivity(), "Signed out successfully", Toast.LENGTH_SHORT).show();
            redirectToLogin();
        });

        // Delete Account Button Logic
        deleteButton.setOnClickListener(v -> {
            if (currentUser != null) {
                currentUser.delete()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Account deleted successfully", Toast.LENGTH_SHORT).show();
                                redirectToRegister();
                            } else {
                                Toast.makeText(getActivity(), "Failed to delete account: " +
                                        Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(getActivity(), "No user to delete", Toast.LENGTH_SHORT).show();
            }
        });

// Set click listeners for navigation items

        navHome.setOnClickListener(v -> {
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);

            // Navigate to HomeFragment
            navigateToFragment(new HomeFragment());

        });

        navRate.setOnClickListener(v -> {
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);

            // Navigate to RateUsFragment
            navigateToFragment(new RateUsFragment());
        });


        navShareApp.setOnClickListener(v -> {
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);

            // Navigate to ShareFragment
            navigateToFragment(new ShareFragment());
        });

        navPolicy.setOnClickListener(v -> {
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);

            // Navigate to PrivacyPolicyFragment
            navigateToFragment(new PrivacyPolicyFragment());
        });

        navContactUs.setOnClickListener(v -> {
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);

            // Navigate to ContactUsFragment
            navigateToFragment(new ContactUsFragment());
        });

        btAboutUs.setOnClickListener(v -> {
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);

            // Navigate to AboutUsFragment
            navigateToFragment(new AboutUsFragment());
        });

        btEditProfile.setOnClickListener(v -> {
                    // Close the drawer
                    drawerLayout.closeDrawer(GravityCompat.START);

                    // Navigate to AboutUsFragment
                    navigateToFragment(new ProfileFragment());
                });

        btBookingsView.setOnClickListener(v -> {
                    // Close the drawer
                    drawerLayout.closeDrawer(GravityCompat.START);

                    // Navigate to AboutUsFragment
                    navigateToFragment(new BookingsFragment());
                });

// Dummy Data
//        List<Recipe> recipes = getDummyData();

//        // Set up adapter
//        RecipeAdapter adapter = new RecipeAdapter(recipes);
//        recyclerView.setAdapter(adapter);


        return rootView; // Return the fragment's root view
    }

    // Method to navigate to a different fragment
    private void navigateToFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // Replace the current fragment with the new one
        transaction.replace(R.id.fragment_container, fragment);

        // Do not add the transaction to the back stack to avoid fragment overlap
        transaction.commit();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
    private void redirectToRegister() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
        requireActivity().finish();

    }

    private List<Recipe> getDummyData() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pizza", "Delicious cheesy pizza", R.drawable.ri_pizza));
        recipes.add(new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.ri_pasta));
        recipes.add(new Recipe("Burger", "Juicy beef burger", R.drawable.ri_burger));
        recipes.add(new Recipe("Sushi", "Fresh sushi with wasabi", R.drawable.ri_sushi));
        recipes.add(new Recipe("Tacos", "Spicy Mexican tacos", R.drawable.ri_tacos));
        recipes.add(new Recipe("Pizza", "Delicious cheesy pizza", R.drawable.ri_pizza));
        recipes.add(new Recipe("Pasta", "Creamy Alfredo pasta", R.drawable.ri_pasta));
        recipes.add(new Recipe("Burger", "Juicy beef burger", R.drawable.ri_burger));
        recipes.add(new Recipe("Sushi", "Fresh sushi with wasabi", R.drawable.ri_sushi));
        return recipes;
    }
}
