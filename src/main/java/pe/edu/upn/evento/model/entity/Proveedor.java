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
@Table(name = "proveedores")
public class Proveedor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer proveedorId;

	@Column(name = "empresa", length = 30, nullable = false)
	private String empresa;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="servicio_id",nullable = false)
	private Servicio servicio;


	public Integer getProveedorId() {
		return proveedorId;
	}


	public void setProveedorId(Integer proveedorId) {
		this.proveedorId = proveedorId;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
