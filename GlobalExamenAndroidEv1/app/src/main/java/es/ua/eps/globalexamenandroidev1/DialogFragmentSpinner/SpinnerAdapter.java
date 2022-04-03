package es.ua.eps.globalexamenandroidev1.DialogFragmentSpinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.ua.eps.globalexamenandroidev1.R;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Modelo> modeloList;//Hay que tocar esta linea con el modelo <Modelo>

    public SpinnerAdapter(Context context, List<Modelo> modeloList) {//Lo generamos automaticamente
        this.context = context;
        this.modeloList = modeloList;
    }

    @Override
    public int getCount() {
        return modeloList != null ? modeloList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_spinner_personalizado, parent, false);//Hay que tocar el item_spinner_personalizado

        TextView txtName = rootView.findViewById(R.id.textViewSpinner);//Hay que poner los id del item personalizado correspondientes
        ImageView image = rootView.findViewById(R.id.imageViewSpinner);//Hay que poner los id del item personalizado correspondientes

        //Se añade la info de cada view
        txtName.setText(modeloList.get(position).getName());
        image.setImageResource(modeloList.get(position).getImage());
        return rootView;
    }
}
