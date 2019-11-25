package pe.edu.upn.evento.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="servicios")
public class Servicio {
	@Id
	@Column(name="servicio_id",nullable = false)
	private Integer servicioId;
	

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "servicio")
	private  List<Evento> eventos;
	
	@Column(name="servicio_nombre",length=40)
	private String servicioNombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	
	
	@Column(name="monto",nullable = false)
	private Float monto;

	public Integer getServicioId() {
		return servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}


	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public String getServicioNombre() {
		return servicioNombre;
	}

	public void setServicioNombre(String servicioNombre) {
		this.servicioNombre = servicioNombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	
	
	
  
}
