package com.fct.kosmos.jin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fct.kosmos.R;
import com.fct.kosmos.jin.db.ContactosDatasource;
import com.fct.kosmos.jin.model.Contacto;

public class ModificacionActivity extends AppCompatActivity {

    EditText etId;
    EditText etNombre;
    EditText etEmail;

    Button btnBuscar;
    Button btnGuardar;

    ContactosDatasource cds;
    Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion);

        etId = findViewById(R.id.etIdM);
        etNombre = findViewById(R.id.etNombreM);
        etEmail = findViewById(R.id.etEmailM);

        btnBuscar = findViewById(R.id.btnBuscarM);
        btnGuardar = findViewById(R.id.btnGuardarM);

        cds = new ContactosDatasource(this);
    }

    public void buscar(View v) {
        String id = etId.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "Debe introducir un id",
                    Toast.LENGTH_LONG).show();
        } else {
            contacto = cds.consultarContacto(Integer.parseInt(id));

            if (contacto != null) {
                etId.setEnabled(false);
                btnBuscar.setEnabled(false);

                etNombre.setEnabled(true);
                etEmail.setEnabled(true);
                btnGuardar.setEnabled(true);

                etNombre.setText(contacto.getName());
                etEmail.setText(contacto.getEmail());

            } else {
                Toast.makeText(this,
                        "No se ha encontrado ningún contacto para el id introducido",
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    public void modificarM(View v) {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Debe introducir todos los datos",
                    Toast.LENGTH_LONG).show();
        } else {
            contacto.setName(nombre);
            contacto.setEmail(email);

            cds.modificarContacto(contacto);

            Toast.makeText(this,
                    "Se ha realizado la modificación correctamente",
                    Toast.LENGTH_LONG).show();

            etId.setText("");
            etNombre.setText("");
            etEmail.setText("");

            etId.setEnabled(true);
            btnBuscar.setEnabled(true);

            etNombre.setEnabled(false);
            etEmail.setEnabled(false);
            btnGuardar.setEnabled(false);

        }
    }

    public void cancelarM(View v) {
        finish();
    }
}
