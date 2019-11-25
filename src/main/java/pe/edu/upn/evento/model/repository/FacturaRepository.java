package pe.edu.upn.evento.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upn.evento.model.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer>{

}
