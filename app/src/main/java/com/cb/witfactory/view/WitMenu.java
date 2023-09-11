package com.cb.witfactory.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cb.witfactory.R;
import com.cb.witfactory.data.classModel.AmplifyCognito;
import com.cb.witfactory.data.classModel.Utils;
import com.cb.witfactory.databinding.ActivityWitMenuBinding;
import com.cb.witfactory.model.Callfun;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class WitMenu extends AppCompatActivity implements Callfun {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityWitMenuBinding binding;
    DrawerLayout drawer;
    private NavigationView navigationView;
    AmplifyCognito amplifyCognito = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityWitMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarLoginMenu.toolbar);


        amplifyCognito = new AmplifyCognito(getApplicationContext());
        amplifyCognito.setListener(WitMenu.this);




        binding.appBarLoginMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "  Envío de Correos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = binding.drawerLayout;
        navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menu_home, R.id.menu_profile, R.id.menu_activity, R.id.menu_soporte, R.id.menu_politicas, R.id.menu_add_user)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.menu_home) {
                    binding.appBarLoginMenu.fab.setVisibility(View.VISIBLE);
                    NavController navController = Navigation.findNavController(WitMenu.this, R.id.nav_host_fragment_content_login_menu);
                    navController.navigateUp();
                    navController.navigate(R.id.menu_home);
                    drawer.close();
                    return true;
                }

                if (id == R.id.menu_profile) {
                    binding.appBarLoginMenu.fab.setVisibility(View.VISIBLE);
                    NavController navController = Navigation.findNavController(WitMenu.this, R.id.nav_host_fragment_content_login_menu);
                    navController.navigateUp();
                    navController.navigate(R.id.menu_profile);
                    drawer.close();
                    return true;
                }

                if (id == R.id.menu_soporte ) {
                    binding.appBarLoginMenu.fab.setVisibility(View.GONE);
                    NavController navController = Navigation.findNavController(WitMenu.this, R.id.nav_host_fragment_content_login_menu);
                    navController.navigateUp();
                    navController.navigate(R.id.menu_soporte);
                    drawer.close();
                    return true;
                }

                if (id == R.id.menu_activity) {
                    binding.appBarLoginMenu.fab.setVisibility(View.GONE);
                    NavController navController = Navigation.findNavController(WitMenu.this, R.id.nav_host_fragment_content_login_menu);
                    navController.navigateUp();
                    navController.navigate(R.id.menu_activity);
                    drawer.close();
                    return true;
                }

                if (id == R.id.terms_conditions) {
                    binding.appBarLoginMenu.fab.setVisibility(View.GONE);
                    NavController navController = Navigation.findNavController(WitMenu.this, R.id.nav_host_fragment_content_login_menu);
                    navController.navigateUp();
                    navController.navigate(R.id.terms_conditions);
                    drawer.close();
                    return true;
                }


                if (id == R.id.menu_logoff) {
                    Toast.makeText(getApplicationContext(), getString(R.string.text_logout), Toast.LENGTH_LONG).show();
                    amplifyCognito.logoutAmplify(getApplicationContext());
                    return true;
                }


                return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onSuccess(String s) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Utils.goToLogin(getApplicationContext());
            }
        });
    }

    @Override
    public void onError(String s) {

    }



}