package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by olive on 16/11/2016.
 */

@Table
public class Serie extends SugarRecord {

    private long ID;

    //private long TreinoExercicioID;
    private TreinoExercicio TreinoExercicio;

    private long Ordem;

    private long Repeticoes;

    private long Carga;

    public Serie() {
    }

    public Serie(long _id, TreinoExercicio _treinoexercicio, long _ordem, long _repeticioes, long _carga) {
        super();
        this.setID(_id);
        this.setTreinoExercicio(_treinoexercicio);
        this.setOrdem(_ordem);
        this.setRepeticoes(_repeticioes);
        this.setCarga(_carga);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getCarga() {
        return Carga;
    }

    public void setCarga(long carga) {
        Carga = carga;
    }

    public TreinoExercicio getTreinoExercicio() {
        return TreinoExercicio;
    }

    public void setTreinoExercicio(TreinoExercicio treinoExercicio) {
        TreinoExercicio = treinoExercicio;
    }

    public long getOrdem() {
        return Ordem;
    }

    public void setOrdem(long ordem) {
        Ordem = ordem;
    }

    public long getRepeticoes() {
        return Repeticoes;
    }

    public void setRepeticoes(long repeticoes) {
        Repeticoes = repeticoes;
    }
}
