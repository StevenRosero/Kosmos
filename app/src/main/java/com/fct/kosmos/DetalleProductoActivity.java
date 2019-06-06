package com.fct.kosmos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetalleProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        getIncomingIntent();
    }
    private void getIncomingIntent(){

        if(getIntent().hasExtra("image_cor") && getIntent().hasExtra("image_name")){

            String imagecor = getIntent().getStringExtra("image_cor");
            String imageName = getIntent().getStringExtra("image_name");

            setImage(imagecor, imageName);
        }
    }

    private void setImage(String imagecor, String imageName){

        TextView name = findViewById(R.id.tvNombreProd);
        TextView correo = findViewById(R.id.tvEmailProd);
        name.setText(imageName);
        correo.setText(imagecor);

    }

}
