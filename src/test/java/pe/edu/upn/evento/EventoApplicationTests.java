package pe.edu.upn.evento;


import static org.junit.Assert.assertTrue;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import pe.edu.upn.evento.model.entity.Evento;
import pe.edu.upn.evento.model.entity.Servicio;
import pe.edu.upn.evento.model.entity.Usuario;
import pe.edu.upn.evento.model.repository.EventoRepository;
import pe.edu.upn.evento.model.repository.ServicioRepository;
import pe.edu.upn.evento.model.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventoApplicationTests {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ServicioRepository servicioRepository;
	

	@Test
	public void contextLoads() {
		try {
			
			
			
			
			
		/*	// Evento
			Evento quinceañero = new Evento();
			quinceañero.setEventoId("E1");
			quinceañero.setNombre("quinceañero");
			
			Evento boda = new Evento();
			boda.setEventoId("E2");
			boda.setNombre("boda");
			
			Evento promocion = new Evento();
			promocion.setEventoId("E3");
			promocion.setNombre("promocion");
			
			Evento bautizo = new Evento();
			bautizo.setEventoId("E4");
			bautizo.setNombre("bautizo");
			
			
			
			bautizo = eventoRepository.save(bautizo);
			promocion = eventoRepository.save(promocion);
			boda = eventoRepository.save(boda);
			quinceañero = eventoRepository.save(quinceañero);
			
			
			
		
		
			
			// grabar
			
			
			eventoRepository.save(quinceañero);
			eventoRepository.save(boda);
			eventoRepository.save(promocion);
			eventoRepository.save(bautizo);
		
					
			
			
			*/
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
