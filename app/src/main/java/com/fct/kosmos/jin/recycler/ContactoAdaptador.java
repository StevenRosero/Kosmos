package com.fct.kosmos.jin.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.fct.kosmos.R;
import com.fct.kosmos.jin.model.Contacto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactoAdaptador
        extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> implements Filterable {

    private ArrayList<Contacto> lista;
    private ArrayList<Contacto> listaFull;

    public ContactoAdaptador(ArrayList<Contacto> lista) {
        this.lista = lista;
       listaFull = new ArrayList<>(lista);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contacto_item, viewGroup, false);

        ContactoViewHolder cvh = new ContactoViewHolder(v);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int i) {
        contactoViewHolder.bindContacto(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Contacto> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listaFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Contacto item : listaFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            lista.clear();
            lista.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvEmail;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }

        public void bindContacto(Contacto co) {
            tvNombre.setText(co.getName());
            tvEmail.setText(co.getEmail());
        }
    }

}
