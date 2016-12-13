package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

/**
 * Created by rodrigo.oliveira on 30/09/2016.
 */

@Table
public class Entidade extends SugarRecord {

    @Column(name = "ID_TESTE")
    private long ID;

    public Entidade() {
    }

    public Entidade(long _id) {
        super();
        this.ID = _id;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
