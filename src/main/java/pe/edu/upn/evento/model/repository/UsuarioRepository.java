package pe.edu.upn.evento.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upn.evento.model.entity.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>    {
	
	
	Optional<Usuario> findByUsername(String username);
}
