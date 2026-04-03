package usp.icmc.ssc.lasdpc.labstreamcontrol.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import usp.icmc.ssc.lasdpc.labstreamcontrol.entities.MedicaoErro;

public interface MedicaoErroRepository extends MongoRepository<MedicaoErro, String> {  }
