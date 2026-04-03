package usp.icmc.ssc.lasdpc.labstreamcontrol.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Atuador;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Medicao;
import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.Sensor;

@Repository
public interface MedicaoRepository extends MongoRepository<Medicao, String> {
    List<Medicao> getByAtuador(Atuador atuador, Sort sort);
    Page<Medicao> getByAtuador(Atuador atuador, Pageable pageable);

    List<Medicao> getBySensor(Sensor sensor, Sort sort);
    Page<Medicao> getBySensor(Sensor sensor, Pageable pageable);
}
