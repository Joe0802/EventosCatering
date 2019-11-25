package pe.edu.upn.evento.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pe.edu.upn.evento.model.entity.Authority;

@Entity
@Table(name="usuarios")

public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioId;
	
	@Column(name="username",length=60)
	private String username;
	
	@Column(name="nombre",length=60)
	private String nombre;
	
	@Column(name="pass",length=60)
	private String password;
	
	@Column(name = "telefono",length=15)
	private Integer telefono;
	
	@Column(name="email",length=60)
	private String email;
	
	@Column(name="ruc",length=60)
	private String 	ruc;
	
	@OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
	private List<Evento> eventos;
	
    private boolean enable;

	
	 
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)    
    private List<Authority> authorities;
    

	public void addAuthority( String _authority ) {
		Authority authority = new Authority();
		authority.setAuthority( _authority );
		authority.setUsuario(this);
		this.authorities.add(authority);
	}
    
	public List<Authority> getAuthorities() {
		return authorities;
	}

	
	
	
	public Usuario() {
		this.enable = true;
		this.authorities = new ArrayList<>();
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}



	
	
}
