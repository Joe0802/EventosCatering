package pe.edu.upn.evento.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upn.evento.model.entity.CategoriaEvento;
import pe.edu.upn.evento.model.entity.Evento;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEvento,Integer> {

}
