package com.fct.kosmos.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fct.kosmos.R;
import com.fct.kosmos.javabeans.Productos;

import java.util.List;

public class ProductosAdapter extends BaseAdapter {
    // Adaptador de los métodos para en enlace de los layout. En un principio solo de testeo por comprobar la Entrada/Salida del flujo de datos
    private LayoutInflater inflater;
    private int idLayout;
    private List<Productos> productos;

    public ProductosAdapter(Context contexto, int idLayout,
                         List<Productos> eventos) {

        inflater = LayoutInflater.from(contexto);
        this.idLayout = idLayout;
        this.productos = eventos;
    }

    static class ViewHolder {
        ImageView ivImagen;
        TextView tvNombre;
        TextView tvCantidad;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);

            holder = new ViewHolder();
            //Meterlo ya cuando se cree el layout
            /*holder.ivImagen = convertView.findViewById(R.id.ivImagenProducto);
            holder.tvNombre = convertView.findViewById(R.id.tvNombreProducto);
            holder.tvCantidad = convertView.findViewById(R.id.tvCantidadProducto);*/

            convertView.setTag(holder);
        }
        else {

            holder = (ViewHolder) convertView.getTag();
        }

        Productos producto = productos.get(posicion);
        holder.ivImagen.setImageBitmap(producto.getImagen());
        holder.tvNombre.setText(producto.getNombre());
        holder.tvCantidad.setText(producto.getCantidad());

        return convertView;
    }




    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return productos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return productos.get(posicion).getId();
    }

}
