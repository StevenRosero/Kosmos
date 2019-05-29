package com.fct.kosmos.activities.javabean;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fct.kosmos.R;
import com.fct.kosmos.activities.QuitarProductoActivity;

import java.util.ArrayList;

public class ProductosAdaptador extends RecyclerView.Adapter<ProductosAdaptador.CocheViewHolder> {
    private AdapterView.OnItemClickListener listener;
    private ArrayList<Productos> lista;
    private Context contexto;

    public ProductosAdaptador(ArrayList<Productos> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }

    /*                       MÉTODOS NECESARIOS                                    */
    @NonNull
    @Override
    public CocheViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_productos, viewGroup, false);
        CocheViewHolder cvh = new CocheViewHolder(v, contexto);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CocheViewHolder cocheViewHolder, int i) {
        cocheViewHolder.bindCoche(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void clear() {
        lista.clear();
    }

    /*-------------------------           CLASE INTERNA           -------------------------------*/
    public class CocheViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        private TextView tvMatricula;
        private TextView tvModelo;
        private TextView tvColor;
        private TextView tvCantidad;
        private TextView tvPrecio;


        public CocheViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvMatricula = itemView.findViewById(R.id.tvNombrePItem);
            tvModelo = itemView.findViewById(R.id.tvTipoPItem) ;
            tvColor = itemView.findViewById(R.id.tvColorPItem);
            tvCantidad = itemView.findViewById(R.id.tvCantidadPItem);
            tvPrecio = itemView.findViewById(R.id.tvPrecioPItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {

                    Intent intent = new Intent(itemView.getContext(), QuitarProductoActivity.class);

                    // get position
                    int pos = getAdapterPosition();
                //TODO: INVESTIGAR COMO SE HACE UNA PUT.EXTRA
                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        Productos clickedDataItem = lista.get(pos);
                        Toast.makeText(itemView.getContext(), "You clicked " + clickedDataItem.getNombreP(), Toast.LENGTH_SHORT).show();

                        itemView.getContext().startActivity(intent);
                    }

                }
            });
        }


        public void bindCoche(Productos c) {
            tvMatricula.setText(String.format(context.getString(R.string.tv_nombreP_item), c.getNombreP()));
            tvModelo.setText(String.format(context.getString(R.string.tv_tipoP_item), c.getTipoP()));
            tvColor.setText(String.format(context.getString(R.string.tv_colorP_item), c.getColorP()));
            tvCantidad.setText(String.format(context.getString(R.string.tv_cantidadP_item), c.getCantidadP()));
            tvPrecio.setText(String.format(context.getString(R.string.tv_precioP_item), c.getPrecioP()));
        }
    }

}
