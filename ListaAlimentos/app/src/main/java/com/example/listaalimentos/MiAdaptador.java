package com.example.listaalimentos;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiContenedor> implements View.OnClickListener{

    ArrayList<Producto> listaProductos;
    Context contexto;
    OnDatosListener onDatosListener;

    private View.OnClickListener listener;

    public MiAdaptador(ArrayList<Producto> listaProductos, Context contexto, OnDatosListener onDatosListener) {
        this.listaProductos = listaProductos;
        this.contexto = contexto;
        this.onDatosListener = onDatosListener;
    }

    @NonNull
    @Override
    public MiContenedor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        view.setOnClickListener((View.OnClickListener)this);
        return new MiAdaptador.MiContenedor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiContenedor holder, int position) {
        holder.tvProducto.setText(listaProductos.get(position).getNombre());
        holder.tvCantidad.setText(listaProductos.get(position).getCantidad() + "");
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    //Función para inicializar el listener
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        //Si el listener no es nulo mostramos la vista al clickar
        if(listener != null) {
            listener.onClick(view);
        }
    }

    //Interfaz
    public interface OnDatosListener {
        public void onDatosBorrar(int posicion);
    }

    public class MiContenedor extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{

        TextView tvProducto, tvCantidad;

        public MiContenedor(@NonNull View itemView) {
            super(itemView);
            tvProducto = itemView.findViewById(R.id.tv_IPnombre);
            tvCantidad = itemView.findViewById(R.id.tv_IPcantidad);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            onDatosListener.onDatosBorrar(getAdapterPosition());
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem itemBorrar = contextMenu.add(Menu.NONE, 1, 1, "BORRAR PRODUCTO");
            itemBorrar.setOnMenuItemClickListener(this);
        }
    }
}
