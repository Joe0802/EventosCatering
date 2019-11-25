package pe.edu.upn.evento.controller;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.evento.model.entity.Evento;
import pe.edu.upn.evento.model.entity.Servicio;
import pe.edu.upn.evento.model.entity.Usuario;

import pe.edu.upn.evento.service.EventoService;

import pe.edu.upn.evento.service.ServicioService;
import pe.edu.upn.evento.service.UsuarioService;



@Controller
@RequestMapping("/usuario")
@SessionAttributes( {"usuario", "eventos" } )
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private ServicioService servicioService;
	
	@Autowired
	private EventoService eventoService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			//obtenemos  usuario
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			Optional<Usuario> optional = usuarioService.findByUsername(username);
			//obtenemos lista eventos por usuario
			if (optional.isPresent()) {
				List<Evento> listaEventos = optional.get().getEventos();
				if(!listaEventos.isEmpty())model.addAttribute("eventos", listaEventos);	
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/usuario/inicio";
	}
	
	@GetMapping("/edit/{usuarioId}")
	public String editar(@PathVariable("usuarioId") Integer id, Model model) {
		try {
			Optional<Usuario> optional = usuarioService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("usuario", optional.get());
			} else {
				return "redirect:/usuario";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/usuario/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") Usuario usuario, 
			Model model, SessionStatus status) {
		try {
			// Verificar que el username ya exista.
			
				usuario.setPassword(passwordEncoder.encode( usuario.getPassword() ));
				usuarioService.save(usuario);
				status.setComplete();
			

		} catch (Exception e) {
			// TODO: handle exception
			
			
		}
		
		return "redirect:/logout";

	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		try {
			List<Evento> eventos = 
					eventoService.findAll();
			model.addAttribute("eventos", eventos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/usuario/nuevo";
	}
	
	
	
	@GetMapping("/del/{usuarioId}")
	public String eliminar(@PathVariable("usuarioId") int id, Model model) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if(usuario.isPresent()) {
				//usuarioService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Usuario> usuarios = usuarioService.findAll();
				model.addAttribute("usuarios", usuarios);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/usuario/inicio";
		}
		return "redirect:/usuario";
	}
	
}



