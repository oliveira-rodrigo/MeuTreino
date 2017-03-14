package app.meutreino.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.meutreino.R;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Categoria;

/**
 * Created by olive on 14/11/2016.
 */

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.MyViewHolder> {

    private RecyclerViewClickListener listener;

    private List<Categoria> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeCategoria;
        public ImageButton btnRemover, btnEditar;

        public MyViewHolder(View view) {
            super(view);
            nomeCategoria = (TextView) view.findViewById(R.id.tvNome);
            btnRemover = (ImageButton) view.findViewById(R.id.btnRemover);
            btnEditar = (ImageButton) view.findViewById(R.id.btnEditar);

            btnRemover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onViewClicked(v, getAdapterPosition());
                }
            });
        }
    }

    public CategoriaAdapter(List<Categoria> entidadesList, RecyclerViewClickListener listener) {
        this.list = entidadesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoria_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Categoria categoria = list.get(position);
        holder.nomeCategoria.setText(categoria.getNome());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
