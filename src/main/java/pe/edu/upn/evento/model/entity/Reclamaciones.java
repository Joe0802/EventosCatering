package pe.edu.upn.evento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reclamaciones")
public class Reclamaciones {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reclamacionesId;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="servicio_id",nullable = false)
	private Servicio servicio;


	public Integer getReclamacionesId() {
		return reclamacionesId;
	}


	public void setReclamacionesId(Integer reclamacionesId) {
		this.reclamacionesId = reclamacionesId;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
}
