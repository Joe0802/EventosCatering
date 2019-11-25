package pe.edu.upn.evento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.evento.model.entity.Usuario;

import pe.edu.upn.evento.service.UsuarioService;

@Controller
@RequestMapping("/login")
@SessionAttributes( {"usuario" } )
public class LoginController{

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			model.addAttribute("usuarios", usuarios);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/login/iniciologin";
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		try {
			Usuario usuario = new Usuario();
			model.addAttribute("usuario", usuario);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/login/nuevo";
	}
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") Usuario usuario, 
			Model model, SessionStatus status) {
		
		try {
			// Verificar que el username ya exista.
			Optional<Usuario> optional 
				= usuarioService.findByUsername(usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerRegister"
						, "ERROR - El username " 
							+ usuario.getUsername()
							+ " ya existe ");
				return "/login/nuevo";
			} else {
				
				usuario.setPassword(passwordEncoder.encode( usuario.getPassword() ));
				usuario.addAuthority("ROLE_CLIENTE");
				usuarioService.save(usuario);
				status.setComplete();
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			return "/";
		}
		return "/login/iniciologin";
	}
	
	
}

