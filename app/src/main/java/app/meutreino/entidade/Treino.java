package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by olive on 16/11/2016.
 */

@Table
public class Treino extends SugarRecord {

    private long ID;

    private String Nome;

    private Date DataInicio;

    private Date DataFim;

    public Treino() {
    }

    public Treino(long _id, String _nome, Date _dataInicio, Date _dataFim) {
        super();
        this.setID(_id);
        this.setNome(_nome);
        this.setDataInicio(_dataInicio);
        this.setDataFim(_dataFim);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Date getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        DataInicio = dataInicio;
    }

    public Date getDataFim() {
        return DataFim;
    }

    public void setDataFim(Date dataFim) {
        DataFim = dataFim;
    }
}
