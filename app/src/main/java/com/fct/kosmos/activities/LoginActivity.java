package com.fct.kosmos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fct.kosmos.HomeLogInOk;
import com.fct.kosmos.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private EditText nombreUsuario, contraseña;
    private Button btnAcceso, btnRegistro;

    //Conexion a firebase
    private FirebaseAuth mAuth;

    //lOG IN GOOGLE
    private GoogleApiClient googleApi;
    public static final int SING_IN_CODE = 777;

    private Intent activityRegistro,restart, homeActivity;
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //asignar los valores de las variables
        nombreUsuario = findViewById(R.id.etId);
        contraseña = findViewById(R.id.etContrasenia);
        btnAcceso = findViewById(R.id.btnAcceso);
        btnRegistro = findViewById(R.id.btnAcceso2);

        activityRegistro = new Intent(this, RegisterActivity.class);
        homeActivity = new Intent(this, HomeLogInOk.class);
        restart = new Intent(this, LoginActivity.class);

        //gOOGLE
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApi = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        btnAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //barraProgreso.setVisibility(View.VISIBLE);
                btnAcceso.setVisibility(View.INVISIBLE);
                btnRegistro.setVisibility(View.INVISIBLE);
                //missPass.setVisibility(View.INVISIBLE);


                final String idUsuario = nombreUsuario.getText().toString();
                final String contraseniaUsuario = contraseña.getText().toString();

                if (idUsuario.isEmpty() || contraseniaUsuario.isEmpty()) {
                    showMessage("Verifica los datos introducidos");
                    //barraProgreso.setVisibility(View.INVISIBLE);
                    btnAcceso.setVisibility(View.VISIBLE);
                    btnRegistro.setVisibility(View.VISIBLE);
                } else {
                    acceder(idUsuario, contraseniaUsuario);
                }


            }
        });
    }


    private void acceder(String idUsuario, String contraseniaUsuario) {

        mAuth.signInWithEmailAndPassword(idUsuario, contraseniaUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //barraProgreso.setVisibility(View.VISIBLE);
                    btnAcceso.setVisibility(View.INVISIBLE);
                    btnRegistro.setVisibility(View.INVISIBLE);
                    actualizarUsuario();
                }else{
                    showMessage(task.getException().getMessage());
                    iniciarDeNuevo();
                }


            }
        });
    }
    private void updateUI(GoogleSignInAccount account) {

    }

    private void actualizarUsuario() {
        FirebaseUser user = mAuth.getCurrentUser();
        startActivity(homeActivity);
        finish();
    }

    private void showMessage(String s) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, "No hay una cuenta asociada a este email", Toast.LENGTH_LONG);
        toast.show();
    }

    public void accesoRegistro(View v){
        startActivity(activityRegistro);
    }


    private void iniciarDeNuevo() {
        startActivity(homeActivity);
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}