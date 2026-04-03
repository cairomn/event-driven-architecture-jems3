package usp.icmc.ssc.lasdpc.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import usp.icmc.ssc.lasdpc.management.entities.Bloco;
import usp.icmc.ssc.lasdpc.management.entities.Instituicao;

import java.util.Optional;
import java.util.UUID;

public interface BlocoRespository extends JpaRepository<Bloco, UUID> {
    Optional<Bloco> getFirstByNomeAndInstituicao(String nome, Instituicao instituicao);
    Page<Bloco> findByInstituicao(Instituicao instituicao, Pageable pageable);
}
