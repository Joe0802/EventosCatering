package pe.edu.upn.evento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.evento.model.entity.Servicio;
import pe.edu.upn.evento.model.repository.ServicioRepository;
import pe.edu.upn.evento.service.ServicioService;
@Service
public class ServicioServiceimpl implements ServicioService {
	
	@Autowired
	private ServicioRepository servicioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> findAll() throws Exception {
		return servicioRepository.findAll();
	}

	@Override
	public Optional<Servicio> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return servicioRepository.findById(id);
	}

	@Override
	public Servicio save(Servicio entity) throws Exception {
		// TODO Auto-generated method stub
		return servicioRepository.save(entity);
	}

	@Override
	public Servicio update(Servicio entity) throws Exception {
		// TODO Auto-generated method stub
		return servicioRepository.save(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		servicioRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		servicioRepository.deleteAll();
	}

	@Override
	public void deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
