package es.ua.eps.recumarzo;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiContenedor> implements View.OnClickListener{

    ArrayList<Notas> listaProductos;
    Context contexto;
    OnDatosListener onDatosListener;

    private View.OnClickListener listener;

    public MiAdaptador(ArrayList<Notas> listaProductos, Context contexto, OnDatosListener onDatosListener) {
        this.listaProductos = listaProductos;
        this.contexto = contexto;
        this.onDatosListener = onDatosListener;
    }

    @NonNull
    @Override
    public MiContenedor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        view.setOnClickListener((View.OnClickListener)this);
        return new MiContenedor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiContenedor holder, int position) {
        holder.tvTitleItem.setText(listaProductos.get(position).getTitulo());
        holder.tvItem.setText(listaProductos.get(position).getDescipcion());
        if(listaProductos.get(position).getCategoria().equals("Cita/Reunion")) {
            holder.ivItem.setImageResource(R.mipmap.icon_item);
        }
        else if (listaProductos.get(position).getCategoria().equals("Cosas pendientes")){
            holder.ivItem.setImageResource(R.mipmap.icon_cosas);
        }
        else if(listaProductos.get(position).getCategoria().equals("Varios")){
            holder.ivItem.setImageResource(R.mipmap.icon_varios);
        }
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

        TextView tvTitleItem, tvItem;
        ImageView ivItem;

        public MiContenedor(@NonNull View itemView) {
            super(itemView);
            tvTitleItem = itemView.findViewById(R.id.tvTitleItem);
            tvItem = itemView.findViewById(R.id.tvItem);
            ivItem = itemView.findViewById(R.id.ivItem);
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
