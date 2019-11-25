package pe.edu.upn.evento.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upn.evento.model.entity.Servicio;
import pe.edu.upn.evento.model.entity.Usuario;
import pe.edu.upn.evento.model.repository.AuthorityRepository;
import pe.edu.upn.evento.model.repository.ServicioRepository;
import pe.edu.upn.evento.model.repository.UsuarioRepository;


@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServicioRepository serviceRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		// ACTIVAR UNA VEZ


		
		
		//PARA CONFIGURAR GERENTE Y CATEGORIAS
		
		
//=======

	/*	
		
		//PARA CONFIGURAR GERENTE Y CATEGORIAS
		
		
    // Evento Catering v2.0
		this.authorityRepository.deleteAll();
		Usuario alv = new Usuario();
		alv.setUsername("gerente");
		alv.setNombre("Alvaro");
		alv.setEmail("alvaro@gmail.com");
		alv.setPassword(passwordEncoder.encode("123"));
		alv.setEnable(true);
        
        alv.addAuthority("ROLE_GERENTE");
        
        
        List<Usuario> usuarios = Arrays.asList(alv);        
        this.usuarioRepository.saveAll(usuarios);
        

      */

		
	}

}
