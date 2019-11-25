package pe.edu.upn.evento.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import pe.edu.upn.evento.model.entity.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Integer>   {

}
