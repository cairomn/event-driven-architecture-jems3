package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.Instituicao;

import java.util.Optional;
import java.util.UUID;

public interface InsituticaoRepository extends JpaRepository<Instituicao, UUID> {
    Optional<Instituicao> getByNome(String nome);
}
