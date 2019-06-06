package com.fct.kosmos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.fct.kosmos.jin.AltaActivity;
import com.fct.kosmos.jin.BorradoActivity;
import com.fct.kosmos.jin.ModificacionActivity;
import com.fct.kosmos.jin.db.ContactosDatasource;
import com.fct.kosmos.jin.model.Contacto;
import com.fct.kosmos.jin.recycler.ContactoAdaptador;
import com.fct.kosmos.scannerJIN.ScannerUnoActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeLogIn extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //BALBLALBAHGDXKHDKHGDKGHD
    FirebaseUser usuarioActual;
    private FirebaseAuth mAuth;

    TextView navNombreUsuario;
    TextView navUsuarioEmail;
    ImageView navUsuarioImagen;
    String receivedMessage;

    RecyclerView rv;
    ContactoAdaptador adaptador;
    LinearLayoutManager llm;
    ArrayList<Contacto> datos;
    ContactosDatasource cds;

    //camara
    private static final int ZBAR_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

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
                startActivity(new Intent(HomeLogIn.this, AdministrarActivity.class));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //Init Firebase
        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();

        updateNavCabecero();



        rv = findViewById(R.id.rvContactos);

        cds = new ContactosDatasource(this);
        datos = cds.consultarContactos();

        if (datos.isEmpty()) {
            Toast.makeText(this, "No se han encontrado contactos",
                    Toast.LENGTH_LONG).show();
        } else {
            adaptador = new ContactoAdaptador(datos);
            llm = new LinearLayoutManager(this);

            rv.setLayoutManager(llm);
            rv.setAdapter(adaptador);
        }
    }

    private void updateNavCabecero() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View actualizarCabecero = navigationView.getHeaderView(0);

        navNombreUsuario = actualizarCabecero.findViewById(R.id.navUsuarioNombre);
        navUsuarioEmail = actualizarCabecero.findViewById(R.id.navUsuarioEmail);
        navUsuarioImagen = actualizarCabecero.findViewById(R.id.navUsuarioImgPerfil);


        navNombreUsuario.setText(usuarioActual.getDisplayName());
        navUsuarioEmail.setText(usuarioActual.getEmail());
        Glide.with(this).load(usuarioActual.getPhotoUrl()).into(navUsuarioImagen);
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

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
//TODO:apartir de aqui
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQuery((CharSequence) receivedMessage, false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaptador.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }else if (id == R.id.action_scanner ){
            launchActivity(ScannerUnoActivity.class);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this, HomeLogIn.class);
            startActivity(i);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {
            Intent i = new Intent(this, BorradoActivity.class);
            startActivity(i);

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

//camara

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivityForResult(intent,1);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            receivedMessage = data.getStringExtra("Data");
          //  textoRecibido.setText(receivedMessage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZBAR_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

}
