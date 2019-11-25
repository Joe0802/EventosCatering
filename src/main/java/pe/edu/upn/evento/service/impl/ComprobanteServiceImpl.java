package pe.edu.upn.evento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upn.evento.model.entity.Comprobante;
import pe.edu.upn.evento.model.repository.ComprobanteRepository;
import pe.edu.upn.evento.service.ComprobanteService;

@Service
public class ComprobanteServiceImpl implements ComprobanteService{
	
	@Autowired
	private ComprobanteRepository comproRepository;

	@Override
	public List<Comprobante> findAll() throws Exception {
		// TODO Auto-generated method stub
		return comproRepository.findAll();
	}

	@Override
	public Optional<Comprobante> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return comproRepository.findById(id);
	}

	@Override
	public Comprobante save(Comprobante entity) throws Exception {
		// TODO Auto-generated method stub
		return comproRepository.save(entity);
	}

	@Override
	public Comprobante update(Comprobante entity) throws Exception {
		// TODO Auto-generated method stub
		return comproRepository.save(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		comproRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		comproRepository.deleteAll();
	}

	@Override
	public void deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Comprobante> findByEvento(Integer id) {
		// TODO Auto-generated method stub
		return comproRepository.findByEvento(id);
	}



	

	

}
