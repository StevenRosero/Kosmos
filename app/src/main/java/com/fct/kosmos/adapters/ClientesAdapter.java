package com.fct.kosmos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fct.kosmos.javabeans.Productos;

import java.util.List;

public class ClientesAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private int idLayout;
    private List<Productos> productos;


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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
