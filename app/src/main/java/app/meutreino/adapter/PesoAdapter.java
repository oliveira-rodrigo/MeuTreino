package app.meutreino.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import app.meutreino.R;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Peso;

/**
 * Created by olive on 14/11/2016.
 */

public class PesoAdapter extends RecyclerView.Adapter<PesoAdapter.MyViewHolder> {

    private RecyclerViewClickListener listener;

    private List<Peso> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView peso;
        public TextView dtPesagem;
        public ImageView imgDeletar;

        public MyViewHolder(View view) {
            super(view);
            peso = (TextView) view.findViewById(R.id.tv_peso);
            dtPesagem = (TextView) view.findViewById(R.id.tv_data_pesagem);
            imgDeletar = (ImageView) view.findViewById(R.id.item_remover);

            imgDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onRowClicked(getAdapterPosition());
                }
            });
        }
    }

    public PesoAdapter(List<Peso> entidadesList, RecyclerViewClickListener listener) {
        this.list = entidadesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.peso_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Peso peso = list.get(position);
        holder.peso.setText(peso.getValor() + " KG");
        holder.dtPesagem.setText(peso.getDataPesagem() != null ?
                "Data da pesagem: " + new SimpleDateFormat("dd/MM/yyyy").format(peso.getDataPesagem()) : "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
