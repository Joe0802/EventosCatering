package pe.edu.upn.evento.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import pe.edu.upn.evento.model.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Integer> {

	@Query(value="Select top 1 * from eventos  order by 1 desc", nativeQuery=true)
	Optional<Evento> fetchUltimoevento();
	
}
