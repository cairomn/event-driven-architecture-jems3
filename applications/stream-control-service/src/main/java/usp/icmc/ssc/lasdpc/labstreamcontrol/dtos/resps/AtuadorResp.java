package usp.icmc.ssc.lasdpc.labstreamcontrol.dtos.resps;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;

public class AtuadorResp {
    
    private String id;

    private UUID atuadorID;

    private List<MedicaoResp> medicoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getAtuadorID() {
        return atuadorID;
    }

    public void setAtuadorID(UUID atuadorID) {
        this.atuadorID = atuadorID;
    }

    public List<MedicaoResp> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(List<MedicaoResp> medicoes) {
        this.medicoes = medicoes;
    }

    public void addMedicao(MedicaoResp medicaoResp) {
        if (this.medicoes == null) {
            this.medicoes = new ArrayList<>();
        }

        this.medicoes.add(medicaoResp);
    }

    public static AtuadorResp from(Atuador atuador) {
        AtuadorResp atuadorResp = new AtuadorResp();
        atuadorResp.setId(atuador.getId());
        atuadorResp.setAtuadorID(atuador.getAtuadorID());
        return atuadorResp;
    }
}
