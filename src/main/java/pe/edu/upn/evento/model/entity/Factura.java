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
@Table(name = "facturas")
public class Factura {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer facturaId;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id",nullable = false)
	private Usuario usuario;


	public Integer getFacturaId() {
		return facturaId;
	}


	public void setFacturaId(Integer facturaId) {
		this.facturaId = facturaId;
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


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
