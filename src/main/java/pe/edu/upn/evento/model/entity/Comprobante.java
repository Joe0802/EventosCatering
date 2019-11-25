package pe.edu.upn.evento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "comprobante",uniqueConstraints = {@UniqueConstraint(
		columnNames = {"evento_id"})})
public class Comprobante {

	@Id
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "evento_id" ,nullable = false)
	private  Evento evento;

	@Column(name = "estado_pago")
	private Boolean estadoPago;
	
	
	
	public Comprobante() {
		
	}

	public Comprobante(Integer id,Evento evento, Boolean estadoPago) {
		this.id=id;
		this.evento = evento;
		this.estadoPago = estadoPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Boolean getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(Boolean estadoPago) {
		this.estadoPago = estadoPago;
	}
	
	
	
	
}
