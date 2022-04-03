package es.ua.eps.hipotenochas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.ua.eps.hipotenochas.modelo.Hipotenocha;
//Antes de hacer esto hay que armar el modelo
public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Hipotenocha> hipotenochaList;//Hay que tocar esta linea con el modelo <Modelo>

    public SpinnerAdapter(Context context, List<Hipotenocha> hipotenochaList) {//Lo generamos automaticamente
        this.context = context;
        this.hipotenochaList = hipotenochaList;
    }

    @Override
    public int getCount() {
        return hipotenochaList != null ? hipotenochaList.size() : 0;
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

        txtName.setText(hipotenochaList.get(position).getName());
        image.setImageResource(hipotenochaList.get(position).getImage());
        return rootView;
    }
}
