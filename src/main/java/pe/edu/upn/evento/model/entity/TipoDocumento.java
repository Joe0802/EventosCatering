package pe.edu.upn.evento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tipoDocumentos")
public class TipoDocumento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tipoDocumentoId;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id",nullable = false)
	private Usuario usuario;


	public Integer getTipoDocumentoId() {
		return tipoDocumentoId;
	}


	public void setTipoDocumentoId(Integer tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}