package br.edu.ucsal.petagende.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ucsal.petagende.domain.Agendamento;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    

    boolean existsByTecnicoIdAndDataHora(Long tecnicoId, LocalDateTime dataHora);
}