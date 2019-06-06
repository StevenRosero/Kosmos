package com.fct.kosmos.jin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fct.kosmos.R;
import com.fct.kosmos.jin.db.ContactosDatasource;
import com.fct.kosmos.jin.model.Contacto;

public class AltaActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etEmail;
    ContactosDatasource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        etNombre = findViewById(R.id.etNombreA);
        etEmail = findViewById(R.id.etEmailA);

        cds = new ContactosDatasource(this);
    }

    public void guardar(View v) {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Debe introducir todos los datos",
                    Toast.LENGTH_LONG).show();
        } else {
            Contacto c = new Contacto(nombre, email);
            long id = cds.insertarContacto(c);

            if (id != -1) {
                // mostrar un toast indicando que la inserción ha ido correctamente
                Toast.makeText(this, "La insercción se ha realizado correctamente",
                        Toast.LENGTH_LONG).show();
                c.setId((int) id);

                etNombre.setText("");
                etEmail.setText("");
            } else {
                // mostrar un toast indicando que no se ha podido insertar
                Toast.makeText(this, "La insercción no se ha podido realizar",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void cancelar(View v) {
        finish();
    }
}
