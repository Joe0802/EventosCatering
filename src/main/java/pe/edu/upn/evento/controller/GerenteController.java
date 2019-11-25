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
import pe.edu.upn.evento.model.entity.Evento;
import pe.edu.upn.evento.model.entity.Servicio;
import pe.edu.upn.evento.model.entity.Usuario;
import pe.edu.upn.evento.service.CategoriaService;
import pe.edu.upn.evento.service.ServicioService;

@Controller
@RequestMapping("/gerente")
@SessionAttributes({ "usuario", "categoria" })
public class GerenteController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ServicioService servicioService;

	@GetMapping
	public String inicio(Model model) {

		return "usuario/inicio";
	}

	@GetMapping("/categoria")
	public String categoria(Model model) {
		try {

			List<CategoriaEvento> lista = categoriaService.findAll();
			// obtenemos lista eventos por usuario

			if (!lista.isEmpty())
				model.addAttribute("categorias", lista);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "gerente/categoria/inicio";
	}
	
	
	@GetMapping("/servicio")
	public String servicio(Model model) {
		try {

			List<Servicio> lista = servicioService.findAll();
			// obtenemos lista eventos por usuario

			if (!lista.isEmpty())
				model.addAttribute("servicio", lista);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "gerente/servicio/inicio";
	}

	
	@GetMapping("/servicio/nuevo")
	public String servicioNuevo(Model model) {
		Servicio serv = new Servicio();
		model.addAttribute("servicio", serv);
		return "gerente/servicio/nuevo";
	}
	
	@PostMapping("/servicio/save")
	public String Serviciosave(@ModelAttribute("servicio") Servicio servicio, Model model,
			SessionStatus status) {

		try {

			servicioService.save(servicio);
			status.setComplete();

			if (SoloLetras(servicio.getDescripcion())) {servicioService.save(servicio); status.setComplete();}
			else {
			model.addAttribute("dangerRegister", "ERROR - Nombre solo admite letras");
			return "gerente/servicio/nuevo";}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			List<Servicio> lista = servicioService.findAll();
			// obtenemos lista eventos por usuario

			if (!lista.isEmpty())model.addAttribute("servicio", lista);
			else model.addAttribute("servicio", null);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "gerente/servicio/inicio";
	}

	@GetMapping("/servicio/edit/{id}")
	public String Servicioeditar(@PathVariable Integer id, Model model, SessionStatus status) {
		Optional<Servicio> optional;
		try {
			optional = servicioService.findById(id);
			if (optional.isPresent()) {
				
				model.addAttribute("servicio",optional.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "gerente/servicio/nuevo";
	}
	
	@GetMapping("/servicio/del/{usuarioId}")
	public String Servicioeliminar(@PathVariable("usuarioId") Integer id, Model model) {
		try {
			Optional<Servicio> servicio = servicioService.findById(id);
			if (servicio.isPresent()) {
				servicioService.deleteById(id);
				 
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");

		}
		try {
			List<Servicio> lista = servicioService.findAll();
			if (!lista.isEmpty())model.addAttribute("servicio", lista);
			else model.addAttribute("servicio", null);

		} catch (Exception e2) {
			// TODO: handle exception
		}

		return "gerente/servicio/inicio";

	}
	

	public boolean SoloLetras(String cadena ) {
		for (int i = 0; i < cadena.length(); i++)
		{
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII > 32 && (valorASCII < 64 || valorASCII > 90) && (valorASCII < 97 || valorASCII > 122 ) && (valorASCII <= 192 || valorASCII >= 255))
				return false; //Se ha encontrado un caracter que no es letra
		}
		return true;
		
	}
	

	@PostMapping("/categoria/save")
	public String save(@ModelAttribute("categoria") CategoriaEvento categoriaEvento, Model model,
			SessionStatus status) {

		try {

			if (SoloLetras(categoriaEvento.getDescripcion())) {categoriaService.save(categoriaEvento); status.setComplete();}
			else {
			model.addAttribute("dangerRegister", "ERROR - Descripción solo admite letras");
			return "gerente/categoria/nuevo";}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			List<CategoriaEvento> lista = categoriaService.findAll();
			// obtenemos lista eventos por usuario

			if (!lista.isEmpty())
				model.addAttribute("categorias", lista);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "gerente/categoria/inicio";
	}
	
	
	
	@GetMapping("/categoria/nuevo")
	public String nuevo(Model model) {
		CategoriaEvento cate = new CategoriaEvento();
		model.addAttribute("categoria", cate);
		return "gerente/categoria/nuevo";
	}
	
	@GetMapping("/categoria/edit/{id}")
	public String editar(@PathVariable Integer id, Model model, SessionStatus status) {
		Optional<CategoriaEvento> optional;
		try {
			optional = categoriaService.findById(id);
			if (optional.isPresent()) {
				
				model.addAttribute("categoria",optional.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "gerente/categoria/nuevo";
	}

	@GetMapping("/categoria/del/{usuarioId}")
	public String eliminar(@PathVariable("usuarioId") Integer id, Model model) {
		try {
			Optional<CategoriaEvento> categoria = categoriaService.findById(id);
			if (categoria.isPresent()) {
				categoriaService.deleteById(id);
				 
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");

		}
		try {
			List<CategoriaEvento> lista = categoriaService.findAll();
			if (!lista.isEmpty())model.addAttribute("categorias", lista);
			else model.addAttribute("servicio", null);
		} catch (Exception e2) {
			// TODO: handle exception
		}

		return "gerente/categoria/inicio";

	}

}
