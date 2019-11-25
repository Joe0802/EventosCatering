package pe.edu.upn.evento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.evento.model.entity.CategoriaEvento;
import pe.edu.upn.evento.model.entity.Evento;
import pe.edu.upn.evento.model.repository.CategoriaRepository;
import pe.edu.upn.evento.model.repository.EventoRepository;
import pe.edu.upn.evento.service.CategoriaService;
import pe.edu.upn.evento.service.EventoService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<CategoriaEvento> findAll() throws Exception {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	@Override
	public Optional<CategoriaEvento> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return categoriaRepository.findById(id);
	}

	@Override
	public CategoriaEvento save(CategoriaEvento entity) throws Exception {
		// TODO Auto-generated method stub
		return categoriaRepository.save(entity);
	}

	@Override
	public CategoriaEvento update(CategoriaEvento entity) throws Exception {
		// TODO Auto-generated method stub
		return categoriaRepository.save(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		categoriaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		categoriaRepository.deleteAll();
	}

	@Override
	public void deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	

	

}
