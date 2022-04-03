package com.example.listaultramarinos;

import android.content.Context;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiContenedor> implements View.OnClickListener{//Hay que ponerle el contenedor y extender de recyclerview.adapter
    ArrayList<Producto> arrayListProducto;
    Context contexto;
    OnDatosListener onDatosListener;
    Cursor mCursor;
    private View.OnClickListener listener;

    public MiAdaptador(ArrayList<Producto> arrayListProducto, Context contexto, OnDatosListener onDatosListener) {
        this.onDatosListener = onDatosListener;
        this.contexto = contexto;
        this.arrayListProducto = arrayListProducto;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public MiContenedor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//Aqui ponemos el item_layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        view.setOnClickListener((View.OnClickListener) this);
        return new MiContenedor(view);//Le pasamos la clase interna
    }

    @Override
    public void onBindViewHolder(@NonNull MiContenedor holder, int position) {//Aqui pillamos los datos del arrayList
        int cantidad = arrayListProducto.get(position).getCantidad();
        holder.tvProductoI.setText(arrayListProducto.get(position).getProducto());
        holder.tvExistencia.setText(cantidad + "");
    }

    @Override
    public int getItemCount() {
        return arrayListProducto.size();
    }

    public interface OnDatosListener {
        public void onDatosBorrar(int posicion);
        public  void onDatosEditar(int posicion);
    }

    /*
    public void actualizarCursor (Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }

        mCursor = newCursor;

        if(newCursor!=null){
            notifyDataSetChanged();
        }
    }
    */

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

    public class MiContenedor extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {//Debe contener las vistas del item
        TextView tvProductoI, tvExistencia;

        public MiContenedor(@NonNull View itemView) {
            super(itemView);
            tvProductoI = itemView.findViewById(R.id.tvProductoI);
            tvExistencia = itemView.findViewById(R.id.tvExistencia);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem itemEditar = contextMenu.add(Menu.NONE, 1, 1, "EDITAR");
            MenuItem itemBorrar = contextMenu.add(Menu.NONE, 2, 2, "BORRAR");
            itemEditar.setOnMenuItemClickListener(this);
            itemBorrar.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if(menuItem.getItemId() == 1) {
                Toast.makeText(contexto, "Editar", Toast.LENGTH_SHORT).show();
                onDatosListener.onDatosEditar(getAdapterPosition());
            }
            else {
                Toast.makeText(contexto, "Borrar", Toast.LENGTH_SHORT).show();
                onDatosListener.onDatosBorrar(getAdapterPosition());
            }

            return true;
        }
    }
}
