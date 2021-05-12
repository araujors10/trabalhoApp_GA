package com.example.cadastrodeclientes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterCliente extends BaseAdapter {

    private List<Cliente> clienteList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterCliente(Context context, List<Cliente> clienteList){
        this.clienteList = clienteList;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return clienteList.size();
    }

    @Override
    public Object getItem(int i) {
        return clienteList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return clienteList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvTelefone = convertView.findViewById(R.id.tvListaTelefone);
            item.tvCelular = convertView.findViewById(R.id.tvListaCelular);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        Cliente cliente = clienteList.get(i);
        item.tvNome.setText(  cliente.nome );
        item.tvTelefone.setText(  cliente.telefone );
        item.tvCelular.setText(  cliente.celular );

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvTelefone, tvCelular;
        LinearLayout layout;
    }

}
