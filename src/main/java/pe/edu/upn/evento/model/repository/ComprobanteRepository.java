package pe.edu.upn.evento.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upn.evento.model.entity.Comprobante;
import pe.edu.upn.evento.model.entity.Evento;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante,Integer> {

	@Query(value="Select top 1 * from comprobante  where evento_id =:id", nativeQuery=true)
	Optional<Comprobante> findByEvento(Integer id);
	
}
