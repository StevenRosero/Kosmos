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

public class BorradoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_borrado);

        etId = findViewById(R.id.etIdB);
        etNombre = findViewById(R.id.etNombreB);
        etEmail = findViewById(R.id.etEmailB);

        btnBuscar = findViewById(R.id.btnBuscarB);
        btnGuardar = findViewById(R.id.btnGuardarB);

        cds = new ContactosDatasource(this);
    }

    public void buscarB(View v) {
        String id = etId.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "Debe introducir un id",
                    Toast.LENGTH_LONG).show();
        } else {
            contacto = cds.consultarContacto(Integer.parseInt(id));

            if (contacto != null) {
                etId.setEnabled(false);
                btnBuscar.setEnabled(false);

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

    public void borrar(View v) {

        cds.borrarContacto(contacto.getId());

        Toast.makeText(this,
                "Se ha eliminado el contacto correctamente",
                Toast.LENGTH_LONG).show();

        etId.setText("");
        etNombre.setText("");
        etEmail.setText("");

        etId.setEnabled(true);
        btnBuscar.setEnabled(true);

        btnGuardar.setEnabled(false);

    }

    public void cancelarB(View v) {
        finish();
    }
}
