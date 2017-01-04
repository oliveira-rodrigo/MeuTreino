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
import app.meutreino.entidade.Treino;
import app.meutreino.entidade.TreinoExercicio;

/**
 * Created by olive on 14/11/2016.
 */

public class TreinoExercicioAdapter extends RecyclerView.Adapter<TreinoExercicioAdapter.MyViewHolder> {

    private RecyclerViewClickListener listener;

    private List<TreinoExercicio> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nome, series, carga;
        public ImageView imgDeletar;

        public MyViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.tv_nome);
            series = (TextView) view.findViewById(R.id.tv_series);
            carga = (TextView) view.findViewById(R.id.tv_carga);
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

    public TreinoExercicioAdapter(List<TreinoExercicio> entidadesList, RecyclerViewClickListener listener) {
        this.list = entidadesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.treino_exercicio_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TreinoExercicio treinoExercicio = list.get(position);
        holder.nome.setText("Exercicio: " + treinoExercicio.getExercicio().getNome());
        holder.series.setText("Séries: " + treinoExercicio.getSeries());
        holder.carga.setText("Carga: " + treinoExercicio.getCarga());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
