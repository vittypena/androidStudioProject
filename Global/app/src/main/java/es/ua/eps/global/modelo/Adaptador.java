package es.ua.eps.global.modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import es.ua.eps.global.R;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<data> listItems;

    public Adaptador(Context context, ArrayList<data> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        data Item = (data) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvFecha = (TextView) convertView.findViewById(R.id.textView3);

        tvNombre.setText(Item.getNombre());
        tvFecha.setText(Item.getFecha());
        return convertView;
    }
}
