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

    Bitmap photobmp;
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
            holder.ivImagen = convertView.findViewById(R.id.ivImagen);
            holder.tvNombre = convertView.findViewById(R.id.tvNombre);
            holder.tvCantidad = convertView.findViewById(R.id.tvCantidad);

            convertView.setTag(holder);
        }
        else {

            holder = (ViewHolder) convertView.getTag();
        }

        Productos producto = productos.get(posicion);
        //holder.ivImagen.setImageBitmap(producto.getImagen());
        holder.tvNombre.setText(producto.getNombre());
        holder.tvCantidad.setText(producto.getCantidad());
        //holder.tvPrecio.setText(Util.formatearMoneda(evento.getPrecio()));

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
