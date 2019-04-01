package com.example.yemece.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yemece.R;
import com.example.yemece.data.Imc;

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

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_listimcs, parent, false);
            holder = new ViewHolder();
            holder.txtSituacao = view.findViewById(R.id.txt_situacao);
            holder.txtPeso = view.findViewById(R.id.txt_peso);
            holder.txtAltura = view.findViewById(R.id.txt_altura);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        Imc imc = imcs.get(position);

        holder.txtSituacao.setText(imc.getSituacao());
        holder.txtPeso.setText(nf.format(imc.getPeso()));
        holder.txtAltura.setText(nf.format(imc.getAltura()));

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
    }
}
