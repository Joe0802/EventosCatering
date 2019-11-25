package pe.edu.upn.evento.service;

import java.util.Optional;

import pe.edu.upn.evento.model.entity.Comprobante;

public interface ComprobanteService extends CrudService<Comprobante,Integer> {
	Optional<Comprobante> findByEvento(Integer id);
}
