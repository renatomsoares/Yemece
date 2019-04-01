package com.example.yemece.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yemece.R;
import com.example.yemece.data.Imc;
import com.example.yemece.enums.GravidadeIndiceImc;
import com.example.yemece.helpers.CalculoImcHelper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ImcsAdapter extends BaseAdapter {

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private Context context;
    private List<Imc> imcs = new ArrayList<>();

    public ImcsAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return imcs.size();
    }

    @Override
    public Imc getItem(int position) {
        return imcs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return imcs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        CalculoImcHelper calculoImc = new CalculoImcHelper(R.id.txt_peso,R.id.txt_altura);


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_listimcs, parent, false);
            holder = new ViewHolder();
            holder.txtSituacao = view.findViewById(R.id.txt_situacao);
            holder.txtPeso = view.findViewById(R.id.txt_peso);
            holder.txtAltura = view.findViewById(R.id.txt_altura);
            holder.txtDataRegistro = view.findViewById(R.id.txt_dataRegistro);


            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Imc imc = imcs.get(position);

        holder.txtDataRegistro.setText(imc.getDataRegistro());
        holder.txtPeso.setText(Double.toString(imc.getPeso()) + "kg");
        holder.txtAltura.setText(Double.toString(imc.getAltura()) + "m");
        holder.txtSituacao.setText(imc.getSituacao());


        if (calculoImc.getGravidadeIndice() == GravidadeIndiceImc.ALTA) {
            holder.txtSituacao.setTextColor(ContextCompat.getColor(context, R.color.riscoAlto));
        }

        return view;
    }

    public void setItems(List<Imc> imcs) {
        this.imcs = imcs;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView txtSituacao;
        public TextView txtPeso;
        public TextView txtAltura;
        public TextView txtDataRegistro;
    }
}
