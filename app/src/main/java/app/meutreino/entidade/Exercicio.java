package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by olive on 16/11/2016.
 */

@Table
public class Exercicio extends SugarRecord {

    private long ID;

    //private long CategoriaID;
    private Categoria Categoria;

    private String Nome;

    private boolean Status;

    public Exercicio() {
    }

    public Exercicio(Categoria _categoria, String _nome, boolean _status) {
        super();
        this.setCategoria(_categoria);
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

    public Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(Categoria categoria) {
        Categoria = categoria;
    }
}
