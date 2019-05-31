package com.fct.kosmos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fct.kosmos.R;
import com.fct.kosmos.javabeans.Clientes;
import com.fct.kosmos.javabeans.Productos;

import java.util.List;

public class ClientesAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int idLayout;
    private List<Clientes> clientes;

    public ClientesAdapter(Context contexto, int idLayout, List<Clientes> clientes) {
        inflater = LayoutInflater.from(contexto);
        this.idLayout = idLayout;
        this.clientes = clientes;
    }

    static class ViewHolder {
        ImageView ivImagen;
        TextView tvNombreEmpresa;
        TextView tvContacto;
        TextView tvTelefono;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProductosAdapter.ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);

            holder = new ProductosAdapter.ViewHolder();
            holder.ivImagen = convertView.findViewById(R.id.ivImagen);
            holder.tvNombre = convertView.findViewById(R.id.tvNombre);
            //holder.tvCantidad = convertView.findViewById(R.id.tvCantidad);

            convertView.setTag(holder);
        }
        else {

            holder = (ProductosAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
