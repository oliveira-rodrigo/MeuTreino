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
import app.meutreino.entidade.Medida;

/**
 * Created by olive on 14/11/2016.
 */

public class MedidaAdapter extends RecyclerView.Adapter<MedidaAdapter.MyViewHolder> {

    private RecyclerViewClickListener listener;

    private List<Medida> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        public ImageView imgDeletar;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.tv_titulo);
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

    public MedidaAdapter(List<Medida> entidadesList, RecyclerViewClickListener listener) {
        this.list = entidadesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medida_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Medida medida = list.get(position);
        holder.titulo.setText("Medição realizada em " + new SimpleDateFormat("dd/MM/yyyy")
                .format(medida.getDataMedicao()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
