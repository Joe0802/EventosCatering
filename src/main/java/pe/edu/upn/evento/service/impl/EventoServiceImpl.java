package pe.edu.upn.evento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.evento.model.entity.Evento;
import pe.edu.upn.evento.model.repository.EventoRepository;
import pe.edu.upn.evento.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService{
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Evento> findAll() throws Exception {
		return eventoRepository.findAll();
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Evento> findById(Integer id) throws Exception {
		return eventoRepository.findById(id);
	}
	@Transactional
	@Override
	public Evento save(Evento entity) throws Exception {
		return eventoRepository.save(entity);
	}
	@Transactional
	@Override
	public Evento update(Evento entity) throws Exception {
		return eventoRepository.save(entity);
	}
	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		
		
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		eventoRepository.deleteAll();
		
	}
	
	@Modifying(clearAutomatically  = true)
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		eventoRepository.deleteById(id);
		
	}
	@Override
	public Optional<Evento> fetchUltimoevento() {
		// TODO Auto-generated method stub
		return eventoRepository.fetchUltimoevento();
	}
	

	

}
