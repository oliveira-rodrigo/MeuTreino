package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by olive on 16/11/2016.
 */

@Table
public class Peso extends SugarRecord {

    private long ID;

    private float Valor;

    private Date DataPesagem;

    public Peso() {
    }

    public Peso(float _valor, Date _dataPesagem) {
        super();
        this.setValor(_valor);
        this.setDataPesagem(_dataPesagem);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float valor) {
        Valor = valor;
    }

    public Date getDataPesagem() {
        return DataPesagem;
    }

    public void setDataPesagem(Date dataPesagem) {
        DataPesagem = dataPesagem;
    }
}
