package pe.edu.upn.evento.service;

import java.util.Optional;

import pe.edu.upn.evento.model.entity.Evento;

public interface EventoService extends CrudService<Evento,Integer> {
	Optional<Evento> fetchUltimoevento();
}
