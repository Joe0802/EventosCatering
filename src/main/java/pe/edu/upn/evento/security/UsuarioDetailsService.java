package pe.edu.upn.evento.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.upn.evento.security.UsuarioDetails;
import pe.edu.upn.evento.model.entity.Usuario;
import pe.edu.upn.evento.model.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Retornando el usuario 
		Optional<Usuario> buscado = this.usuarioRepository.findByUsername(username);
		//System.out.print("popo");
		if(buscado.isPresent()) {
			UsuarioDetails usuarioDetails = new UsuarioDetails(buscado.get());
			System.out.print(usuarioDetails.getUsername());
			return usuarioDetails;
		}
		throw new UsernameNotFoundException("Invalid User");
	}
}
