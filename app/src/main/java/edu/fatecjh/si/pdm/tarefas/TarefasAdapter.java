package edu.fatecjh.si.pdm.tarefas;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.fatecjh.si.pdm.tarefas.persistence.TarefaDAO;

/**
 * Created by Icaro on 12/06/2015.
 */
public class TarefasAdapter extends BaseAdapter {

    private final Context context;

    private Tarefa[] lista;

    public TarefasAdapter(Context ctx) {
        this.context = ctx;
        this.lista = TarefaDAO.getInstance(context).pegarTodos();
    }

    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public Object getItem(int position) {
        return lista[position];
    }

    @Override
    public long getItemId(int position) {
        return lista[position].codigo;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listitem_tarefa, parent, false);

        Tarefa tarAtual = lista[position];

        TextView txt;

        txt = (TextView) rowView.findViewById(R.id.txtTitulo);
        txt.setText(tarAtual.titulo);
        if (tarAtual.status == StatusTarefa.COMPLETA) {
            txt.setPaintFlags(txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        txt = (TextView) rowView.findViewById(R.id.txtTipo);
        txt.setText(tarAtual.tipo.toString());
        if (tarAtual.status == StatusTarefa.COMPLETA) {
            txt.setPaintFlags(txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        txt = (TextView) rowView.findViewById(R.id.txtData);
        String newDate = Util.getFriendlyDate(tarAtual.dataCumprimento);
        txt.setText(newDate);
        if (tarAtual.status == StatusTarefa.COMPLETA) {
            txt.setPaintFlags(txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        return rowView;
    }

    @Override
    public void notifyDataSetChanged() {
        this.lista = TarefaDAO.getInstance(context).pegarTodos();
        super.notifyDataSetChanged();
    }
}
