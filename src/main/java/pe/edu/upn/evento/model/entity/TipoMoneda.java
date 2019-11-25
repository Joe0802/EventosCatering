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
@Table(name = "tipoMonedas")
public class TipoMoneda {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tipoMonedaId;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="factura_id",nullable = false)
	private Factura factura;

	public Integer getTipoMonedaId() {
		return tipoMonedaId;
	}

	public void setTipoMonedaId(Integer tipoMonedaId) {
		this.tipoMonedaId = tipoMonedaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	
}
