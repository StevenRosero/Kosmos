package com.fct.kosmos;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


import android.content.Intent;

import com.bumptech.glide.Glide;

import com.fct.kosmos.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;


public class HomeLogIn extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String current_mail, currentUserName;
    private FirebaseAuth mAuth;
    Uri currenImg;

    TextView navNombreUsuario;
    TextView navUsuarioEmail;
    ImageView navUsuarioImagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_log_in);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




        updateNavCabecero();


    }

    private void updateNavCabecero() {



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View actualizarCabecero = navigationView.getHeaderView(0);

        navNombreUsuario = actualizarCabecero.findViewById(R.id.navUsuarioNombre);
        navUsuarioEmail = actualizarCabecero.findViewById(R.id.navUsuarioEmail);
        navUsuarioImagen = actualizarCabecero.findViewById(R.id.navUsuarioImgPerfil);

        //Init Firebase
        FirebaseUser userFirebase = FirebaseAuth.getInstance().getCurrentUser();

        /*if (userFirebase != null) {
            for (Usuario aux : listaUsuarios) {
                Log.e("usuari", aux.getUid());
                if (aux.getUid().equals(userFirebase.getUid())) {
                    Glide.with(User_Profile_Activity.this).load(aux.getUrlFoto()).into(ivFoto);
                    tvNombre.setText(aux.getNombre_completo());
                    tvFecha.setText(aux.getFechaNac());
                    tvDescripcion.setText(aux.getDescripcion());
                    URL = aux.getUrlFoto();
                    dbr.removeEventListener(cel);
                }
            }
        }*/

        current_mail = userFirebase.getEmail();
        currentUserName = userFirebase.getDisplayName();
        currenImg = userFirebase.getPhotoUrl();

        navNombreUsuario.setText(currentUserName);
        navUsuarioEmail.setText(current_mail);

        Glide.with(this).load(currenImg).into(navUsuarioImagen);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_log_in, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent backSLogIN = new Intent(this, LoginActivity.class);
            startActivity(backSLogIN);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
