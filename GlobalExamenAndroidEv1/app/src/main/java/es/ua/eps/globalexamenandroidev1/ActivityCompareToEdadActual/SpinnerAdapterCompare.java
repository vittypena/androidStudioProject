package es.ua.eps.globalexamenandroidev1.ActivityCompareToEdadActual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;


import es.ua.eps.globalexamenandroidev1.R;

public class SpinnerAdapterCompare extends BaseAdapter {
    private Context context;
    private List<ModeloCompare> modeloList;//Hay que tocar esta linea con el modelo <Modelo>

    public SpinnerAdapterCompare(Context context, List<ModeloCompare> modeloList) {//Lo generamos automaticamente
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
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_spinner_personalizado_compare, parent, false);//Hay que tocar el item_spinner_personalizado

        TextView txtName = rootView.findViewById(R.id.textViewSpinnerItemCompare);//Hay que poner los id del item personalizado correspondientes

        //Se añade la info de cada view
        int mesGregorian = 0;
        if(DataCompare.getListaModeloCompare().get(position).getGregorianCalendar().get(Calendar.MONTH) == 0){//ESTE IFF SI TRABAJAMOS CON DATOS DESDE CALENDAR HAY QUE TENER CUIDADO CON EL 12
            mesGregorian = 12;
        }else{
            mesGregorian = DataCompare.getListaModeloCompare().get(position).getGregorianCalendar().get(Calendar.MONTH);
        }
        txtName.setText(modeloList.get(position).getName() +" " + String.valueOf(modeloList.get(position).getGregorianCalendar().get(Calendar.YEAR)) + "/"  +  mesGregorian + "/" +  String.valueOf(modeloList.get(position).getGregorianCalendar().get(Calendar.DAY_OF_MONTH)));
        return rootView;
    }
}
