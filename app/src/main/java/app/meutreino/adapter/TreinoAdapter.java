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
import app.meutreino.entidade.Treino;

/**
 * Created by olive on 14/11/2016.
 */

public class TreinoAdapter extends RecyclerView.Adapter<TreinoAdapter.MyViewHolder> {

    private RecyclerViewClickListener listener;

    private List<Treino> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeTreino;
        public TextView dtInicio;
        public TextView dtFim;
        public ImageView imgDeletar;

        public MyViewHolder(View view) {
            super(view);
            nomeTreino = (TextView) view.findViewById(R.id.tv_nome_treino);
            dtInicio = (TextView) view.findViewById(R.id.tv_dt_inicio);
            dtFim = (TextView) view.findViewById(R.id.tv_dt_fim);
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

    public TreinoAdapter(List<Treino> entidadesList, RecyclerViewClickListener listener) {
        this.list = entidadesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.treino_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Treino treino = list.get(position);
        holder.nomeTreino.setText("Nome do treino: " + treino.getNome());
        holder.dtInicio.setText(treino.getDataInicio() != null ?
                "Data de início: " + new SimpleDateFormat("dd/MM/yyyy").format(treino.getDataInicio()) :
                "Data de início:");
        holder.dtFim.setText(treino.getDataFim() != null ?
                "Data de fim: " + new SimpleDateFormat("dd/MM/yyyy").format(treino.getDataFim()) :
                "Data de fim:");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
