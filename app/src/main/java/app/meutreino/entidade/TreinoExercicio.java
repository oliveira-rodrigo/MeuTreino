package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by olive on 16/11/2016.
 */

@Table
public class TreinoExercicio extends SugarRecord {

    private long ID;

    //private long TreinoID;
    private Treino Treino;

    //private long ExercicioID;
    private Exercicio Exercicio;

    private long Dia;

    private long SerieID;

    private long Carga;

    public TreinoExercicio() {
    }

    public TreinoExercicio(long _id, Treino _treino, Exercicio _exercicio, long _dia, long _serieId, long _carga) {
        super();
        this.setID(_id);
        this.setTreino(_treino);
        this.setExercicio(_exercicio);
        this.setDia(_dia);
        this.setSerieID(_serieId);
        this.setCarga(_carga);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Treino getTreino() {
        return Treino;
    }

    public void setTreino(Treino treino) {
        Treino = treino;
    }

    public Exercicio getExercicio() {
        return Exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        Exercicio = exercicio;
    }

    public long getDia() {
        return Dia;
    }

    public void setDia(long dia) {
        Dia = dia;
    }

    public long getSerieID() {
        return SerieID;
    }

    public void setSerieID(long serieID) {
        SerieID = serieID;
    }

    public long getCarga() {
        return Carga;
    }

    public void setCarga(long carga) {
        Carga = carga;
    }
}
