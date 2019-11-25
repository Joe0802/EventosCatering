package pe.edu.upn.evento.service;

import java.util.Optional;

import pe.edu.upn.evento.model.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Integer> {
	Optional<Usuario> findByUsername(String username) throws Exception;

}
