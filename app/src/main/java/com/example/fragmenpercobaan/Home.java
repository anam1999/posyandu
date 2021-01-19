package com.example.fragmenpercobaan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_home);
        TextView largeTextView = bottomNavigationView.findViewById(R.id.bottom_nav_home).findViewById(com.google.android.material.R.id.largeLabel);
        TextView smallTextView = bottomNavigationView.findViewById(R.id.bottom_nav_home).findViewById(com.google.android.material.R.id.smallLabel);
        largeTextView.setVisibility(View.GONE);
        smallTextView.setVisibility(View.VISIBLE);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_anggota);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_anak, R.id.navigation_ibu, R.id.navigation_beranda,
                R.id.navigation_video, R.id.navigation_toko
        ).build();

//        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);



    }
}
