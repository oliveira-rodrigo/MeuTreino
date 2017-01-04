package app.meutreino.entidade;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by olive on 16/11/2016.
 */

@Table
public class Medida extends SugarRecord {

    private long ID;

    private long BracoDir;

    private long BracoEsq;

    private long Peitoral;

    private long Cintura;

    private long Quadril;

    private long CoxaDir;

    private long CoxaEsq;

    private long PanturrilhaDir;

    private long PanturrilhaEsq;

    private Date DataMedicao;

    public Medida() {
    }

    public Medida(long _bracoDir, long _bracoEsq, long _peitoral, long _cintura, long _quadril,
                  long _coxaDir, long _coxaEsq, long _panturrilhaDir, long _panturrilhaEsq, Date _dataMedicao) {
        super();
        this.setBracoDir(_bracoDir);
        this.setBracoEsq(_bracoEsq);
        this.setPeitoral(_peitoral);
        this.setCintura(_cintura);
        this.setQuadril(_quadril);
        this.setCoxaDir(_coxaDir);
        this.setCoxaEsq(_coxaEsq);
        this.setPanturilhaDir(_panturrilhaDir);
        this.setPanturrilhaEsq(_panturrilhaEsq);
        this.setDataMedicao(_dataMedicao);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getBracoDir() {
        return BracoDir;
    }

    public void setBracoDir(long bracoDir) {
        BracoDir = bracoDir;
    }

    public long getBracoEsq() {
        return BracoEsq;
    }

    public void setBracoEsq(long bracoEsq) {
        BracoEsq = bracoEsq;
    }

    public long getPeitoral() {
        return Peitoral;
    }

    public void setPeitoral(long peitoral) {
        Peitoral = peitoral;
    }

    public long getCintura() {
        return Cintura;
    }

    public void setCintura(long cintura) {
        Cintura = cintura;
    }

    public long getQuadril() {
        return Quadril;
    }

    public void setQuadril(long quadril) {
        Quadril = quadril;
    }

    public long getCoxaDir() {
        return CoxaDir;
    }

    public void setCoxaDir(long coxaDir) {
        CoxaDir = coxaDir;
    }

    public long getCoxaEsq() {
        return CoxaEsq;
    }

    public void setCoxaEsq(long coxaEsq) {
        CoxaEsq = coxaEsq;
    }

    public long getPanturilhaDir() {
        return PanturrilhaDir;
    }

    public void setPanturilhaDir(long panturilhaDir) {
        PanturrilhaDir = panturilhaDir;
    }

    public long getPanturrilhaEsq() {
        return PanturrilhaEsq;
    }

    public void setPanturrilhaEsq(long panturrilhaEsq) {
        PanturrilhaEsq = panturrilhaEsq;
    }

    public Date getDataMedicao() {
        return DataMedicao;
    }

    public void setDataMedicao(Date dataMedicao) {
        DataMedicao = dataMedicao;
    }
}
