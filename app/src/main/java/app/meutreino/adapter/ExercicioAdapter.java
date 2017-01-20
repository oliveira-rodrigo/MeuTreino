package app.meutreino.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.meutreino.R;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Categoria;
import app.meutreino.entidade.Exercicio;

/**
 * Created by olive on 14/11/2016.
 */

public class ExercicioAdapter extends RecyclerView.Adapter<ExercicioAdapter.MyViewHolder> {

    private RecyclerViewClickListener listener;

    private List<Exercicio> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeExercicio;
        public TextView nomeCategoria;
        public ImageView imgDeletar;

        public MyViewHolder(View view) {
            super(view);
            nomeExercicio = (TextView) view.findViewById(R.id.tv_nome_exercicio);
            nomeCategoria = (TextView) view.findViewById(R.id.tv_nome_categoria);
            imgDeletar = (ImageView) view.findViewById(R.id.item_remover);

            imgDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onRowClicked(getAdapterPosition());
                }
            });
        }
    }

    public ExercicioAdapter(List<Exercicio> entidadesList, RecyclerViewClickListener listener) {
        this.list = entidadesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercicio_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Exercicio exercicio = list.get(position);
        holder.nomeExercicio.setText(exercicio.getNome());
        holder.nomeCategoria.setText("Exercício de " + exercicio.getCategoria().getNome());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
