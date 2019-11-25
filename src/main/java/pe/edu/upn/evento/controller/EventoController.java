package pe.edu.upn.evento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.evento.model.entity.CategoriaEvento;
import pe.edu.upn.evento.model.entity.Comprobante;
import pe.edu.upn.evento.model.entity.Evento;
import pe.edu.upn.evento.model.entity.Servicio;
import pe.edu.upn.evento.model.entity.Usuario;
import pe.edu.upn.evento.service.CategoriaService;
import pe.edu.upn.evento.service.ComprobanteService;
import pe.edu.upn.evento.service.EventoService;
import pe.edu.upn.evento.service.ServicioService;
import pe.edu.upn.evento.service.UsuarioService;

@Controller
@RequestMapping("/evento")
@SessionAttributes({ "usuario", "eventos" })
public class EventoController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ServicioService servicioService;

	@Autowired
	private EventoService eventoService;

	@Autowired
	private ComprobanteService comproService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Evento evento = new Evento();
		/*
		 * List<Servicio> servicios = new ArrayList<Servicio>(); try { servicios =
		 * servicioService.findAll(); } catch (Exception e1) { // TODO Auto-generated
		 * catch block model.addAttribute("servicios", evento); }
		 */
		try {
			List<CategoriaEvento> listaCategoria = categoriaService.findAll();
			model.addAttribute("categorias", listaCategoria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("evento", evento);

		return "/evento/nuevo";
	}


	
	public boolean SoloLetras(String cadena ) {
		for (int i = 0; i < cadena.length(); i++)
		{
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return false; //Se ha encontrado un caracter que no es letra
		}
		return true;
		
	}
	
	

	@PostMapping("/save")
	public String save(@ModelAttribute("evento") Evento evento, Model model, SessionStatus status) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			Optional<Usuario> optional = usuarioService.findByUsername(username);

			if (optional.isPresent()) {

			if (optional.isPresent() && SoloLetras(evento.getNombre())) {

				evento.setUsuario(optional.get());
				if (evento.getServicio() == null) {
					Optional<Servicio> optionalServicio = servicioService.findById(1);
					if (optionalServicio.isPresent()) {

						evento.setServicio(optionalServicio.get());

					} else {
						model.addAttribute("dangerRegister", "Error Servicio");
						return "/evento/nuevo";
					}
				}

				eventoService.save(evento);
				Optional<Evento> optionalEvento = eventoService.fetchUltimoevento();
				if (optionalEvento.isPresent()) {
					Comprobante _comprobante = new Comprobante(optionalEvento.get().getEventoId(), evento, false);
					comproService.save(_comprobante);
					evento.setComprobante(_comprobante);
					eventoService.save(evento);
					status.setComplete();
				} else {
					model.addAttribute("dangerRegister", "Error Comprobante");
					return "/evento/nuevo";
				}
			} else {

				model.addAttribute("dangerRegister", "Error NO EXISTE USUARIO");

				model.addAttribute("dangerRegister", "ERROR NO EXISTE USUARIO / SOLO SE ADMITEN LETRAS EN CAMPO NOMBRE ");

				return "/evento/nuevo";
			}
			}
			} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("dangerRegister", "Error al insertar ");
			return "/evento/nuevo";
		}

		try {
			List<Servicio> listaServicio = servicioService.findAll();

			model.addAttribute("servicios", listaServicio);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "/evento/servicios";
	}

	@GetMapping("/escoger/servicio/{id}")
	public String escoger(@PathVariable Integer id, SessionStatus session, Model model) {
		try {
			Optional<Evento> optional = eventoService.fetchUltimoevento();
			if (optional.isPresent()) {
				Optional<Servicio> servicio = servicioService.findById(id);
				if (servicio.isPresent()) {
					optional.get().setServicio(servicio.get());
					eventoService.save(optional.get());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		try {
			//obtenemos usuario
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			Optional<Usuario> optional = usuarioService.findByUsername(username);
			//obtenemos lista eventos por usuario
			if (optional.isPresent()) {
				List<Evento> listaEventos = optional.get().getEventos();
				if (!listaEventos.isEmpty())
					model.addAttribute("eventos", listaEventos);
			}

		} catch (Exception e) {
			//TODO: handle exception
		}
		return "/usuario/inicio";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable Integer id, Model model, SessionStatus session) {
		try {

			Optional<Evento> optional = eventoService.findById(id);
			if (optional.isPresent()) {

				model.addAttribute("evento", optional.get());
			} else {
				//lanzamos exception
				Exception ex = new Exception();
				throw ex;
			}
		} catch (Exception e) {
			try {
				//e.printStackTrace();
				//obtenemos usuario
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String username = authentication.getName();
				Optional<Usuario> optionalUser = usuarioService.findByUsername(username);
				//obtenemos lista eventos por usuario
				if (optionalUser.isPresent()) {
					List<Evento> listaEventos = optionalUser.get().getEventos();
					if (!listaEventos.isEmpty())
						model.addAttribute("eventos", listaEventos);
				}

			} catch (Exception ex) {
				// TODO: handle exception
			}
			return "/usuario/inicio";
		}

		return "evento/info";
	}

	@PostMapping("/editar")
	public String editarEvento(@ModelAttribute("evento") Evento evento, Model model, SessionStatus status) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			Optional<Usuario> optional = usuarioService.findByUsername(username);
			if (optional.isPresent()) {
				evento.setUsuario(optional.get());
				Optional<Evento> optionalEvento = eventoService.findById(evento.getEventoId());
				if (optionalEvento.isPresent()) {
					evento.setServicio(optionalEvento.get().getServicio());
					evento.setComprobante(optionalEvento.get().getComprobante());
				} else {
					model.addAttribute("dangerRegister", "Error NO HAY SERVICIO DISPONIBLE");
					return "/evento/nuevo";
				}
				eventoService.save(evento);
				status.setComplete();
			} else {
				model.addAttribute("dangerRegister", "Error NO EXISTE USUARIO");
				return "/evento/nuevo";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("dangerRegister", "Error al insertar ");
			return "/evento/nuevo";
		}

		try {
			List<Servicio> listaServicio = servicioService.findAll();

			model.addAttribute("servicios", listaServicio);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "/evento/servicios";
	}

	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable Integer id, Model model, SessionStatus session) {
		try {
			Optional<Evento> optional = eventoService.findById(id);
			if (optional.isPresent()) {
				Optional<Comprobante> optionalComprobante = comproService.findByEvento(id);
				if (optionalComprobante.isPresent() && !optionalComprobante.get().getEstadoPago()) {
				 eventoService.deleteById(id);
					//obtenemos usuario
				
					session.setComplete();
				} else {
					Exception ex = new Exception();
					throw ex;
				}
			}else {
				Exception ex = new Exception();
				throw ex;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			Optional<Usuario> optionalUsuario = usuarioService.findByUsername(username);
			//obtenemos lista eventos por usuario
			if (optionalUsuario.isPresent()) {
				List<Evento> listaEventos = optionalUsuario.get().getEventos();
				if (!listaEventos.isEmpty())model.addAttribute("eventos", listaEventos);
				else model.addAttribute("eventos", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/usuario/inicio";
	}




	@GetMapping("/pay/{id}")
	public String pagar(@PathVariable Integer id, Model model, SessionStatus session) {
		try {
			Optional<Evento> optional = eventoService.findById(id);
			if (optional.isPresent()) {
				Optional<Comprobante> optionalComprobante = comproService.findByEvento(id);
				if (optionalComprobante.isPresent() && !optionalComprobante.get().getEstadoPago()) {
					optionalComprobante.get().setEstadoPago(true);
					comproService.save(optionalComprobante.get());
				}
				model.addAttribute("succesRegister", "Pago Realizado");
				session.setComplete();
			} else {
				Exception ex = new Exception();
				throw ex;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//obtenemos usuario
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			Optional<Usuario> optional = usuarioService.findByUsername(username);
			//obtenemos lista eventos por usuario
			if (optional.isPresent()) {
				List<Evento> listaEventos = optional.get().getEventos();
				if (!listaEventos.isEmpty())
					model.addAttribute("eventos", listaEventos);
			}

		} catch (Exception ex) {

		}
		
		return "/usuario/inicio";

	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		try {
			Optional<Evento> optional = eventoService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("evento", optional.get());
				try {
					List<CategoriaEvento> listaCategoria = categoriaService.findAll();
					model.addAttribute("categorias", listaCategoria);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				Exception ex = new Exception();
				throw ex;
			}
		} catch (Exception e) {
			try {
				// obtenemos usuario
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String username = authentication.getName();
				Optional<Usuario> optional = usuarioService.findByUsername(username);
				// obtenemos lista eventos por usuario
				if (optional.isPresent()) {
					List<Evento> listaEventos = optional.get().getEventos();
					if (!listaEventos.isEmpty())
						model.addAttribute("eventos", listaEventos);
				}

			} catch (Exception ex) {

			}
			return "/usuario/inicio";
		}

		return "/evento/editar";
	}

}
