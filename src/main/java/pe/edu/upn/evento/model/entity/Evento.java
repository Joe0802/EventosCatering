package pe.edu.upn.evento.model.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
;

@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventoId;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id",nullable = false)
	private Usuario usuario;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "servicio_id")
	private Servicio servicio;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "evento")
	private Comprobante comprobante;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoria_id")
	private CategoriaEvento categoria; 
	
	
	public Evento() {
		
	}


	public CategoriaEvento getCategoria() {
		return categoria;
	}


	public void setCategoria(CategoriaEvento categoria) {
		this.categoria = categoria;
	}






	public Comprobante getComprobante() {
		return comprobante;
	}






	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Integer getEventoId() {
		return eventoId;
	}

	public void setEventoId(Integer eventoId) {
		this.eventoId = eventoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	


	
}
