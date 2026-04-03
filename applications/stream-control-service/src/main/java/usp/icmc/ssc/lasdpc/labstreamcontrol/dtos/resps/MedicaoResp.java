package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps;

import java.util.Date;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;

public class MedicaoResp {
    
    private String id;

    private String valor;

    private Date dataCadastro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static MedicaoResp from(Medicao medicao) {
        MedicaoResp medicaoResp = new MedicaoResp();
        medicaoResp.setId(medicao.getId());
        medicaoResp.setValor(medicao.getValor());
        medicaoResp.setDataCadastro(medicao.getDataCadastro());
        return medicaoResp;
    }
}
