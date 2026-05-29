package br.edu.ucsal.petagende.repository;

import br.edu.ucsal.petagende.domain.OperadorTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperadorTecnicoRepository extends JpaRepository<OperadorTecnico, Long> {
}