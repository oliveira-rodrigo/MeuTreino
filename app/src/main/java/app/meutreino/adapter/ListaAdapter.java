package app.meutreino.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.meutreino.R;
import app.meutreino.entidade.Entidade;

/**
 * Created by rodrigo.oliveira on 30/09/2016.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {

    private List<Entidade> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.entidadeID);
        }
    }

    public ListaAdapter(List<Entidade> entidadesList) {
        this.list = entidadesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entidade_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Entidade entidade = list.get(position);
        holder.id.setText(String.valueOf(entidade.getID()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}