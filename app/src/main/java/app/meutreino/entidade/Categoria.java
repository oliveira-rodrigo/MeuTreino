package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by olive on 14/11/2016.
 */

@Table
public class Categoria extends SugarRecord {

    private long ID;

    private String Nome;

    private boolean Status;

    public Categoria() {
    }

    public Categoria(String _nome, boolean _status) {
        super();
        this.setNome(_nome);
        this.setStatus(_status);
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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
