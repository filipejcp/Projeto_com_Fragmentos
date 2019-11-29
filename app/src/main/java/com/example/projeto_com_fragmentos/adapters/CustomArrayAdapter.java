package com.example.projeto_com_fragmentos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.projeto_com_fragmentos.R;
import com.example.projeto_com_fragmentos.entities.Contacto;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Contacto> {
    public CustomArrayAdapter(@NonNull Context context, ArrayList<Contacto> resource) {
        super(context,0, resource);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent){
        Contacto p = getItem(position);
        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_da_linha,parent,false);
        }

        ((TextView) convertView.findViewById(R.id.nom)).setText(p.getNome());
        ((TextView) convertView.findViewById(R.id.ntmovel)).setText(p.getIdade());
        return convertView;
    }
}
