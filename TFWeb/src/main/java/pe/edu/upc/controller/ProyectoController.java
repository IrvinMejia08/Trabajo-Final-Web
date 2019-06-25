package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Proyecto;
import pe.edu.upc.service.IAreaService;
import pe.edu.upc.service.IProductoService;
import pe.edu.upc.service.IProyectoService;
import pe.edu.upc.service.IUsuarioService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

	@Autowired
	private IProyectoService pService;

	@Autowired
	private IUsuarioService uService;

	@Autowired
	private IAreaService arService;

	@Autowired
	private IProductoService prService;

	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "bienvenido";
	}

	@Secured("ROLE_JEFE")
	@GetMapping("/nuevo")
	public String nuevoProyecto(Model model) {
		model.addAttribute("proyecto", new Proyecto());
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("listaAreas", arService.listar());
		model.addAttribute("listaProductos", prService.listar());
		return "proyecto/proyecto";
	}

	@PostMapping("/guardar")
	public String guardarProyecto(@Valid Proyecto proyecto, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaAreas", arService.listar());
			model.addAttribute("listaProductos", prService.listar());
			return "proyecto/proyecto";
		} else {
			int rpta = pService.insertar(proyecto);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/proyecto/proyecto";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}

		}
		model.addAttribute("listaProyectos", pService.listar());

		if (result.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			return "/proyecto/proyecto";
		}

		if (result.hasErrors()) {
			model.addAttribute("listaAreas", arService.listar());
			return "/proyecto/proyecto";
		}
		if (result.hasErrors()) {
			model.addAttribute("listaProductos", prService.listar());
			return "/proyecto/proyecto";
		} else {
			pService.insertar(proyecto);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/proyectos/listar";
		}
	}

	@GetMapping("/listar")
	public String listarProyectos(Model model) {
		try {
			model.addAttribute("proyecto", new Proyecto());
			model.addAttribute("listaProyectos", pService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/proyecto/listaProyecto";
	}

	@Secured("ROLE_JEFE")
	@GetMapping("/detalle/{id}")
	public String detailsProyecto(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Proyecto> proyecto = pService.listarId(id);

			if (!proyecto.isPresent()) {
				model.addAttribute("info", "Proyecto no existe");
				return "redirect:/proyectos/listar";
			} else {
				model.addAttribute("proyecto", proyecto.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/proyecto/proyecto";
	}

	@Secured("ROLE_JEFE")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.eliminar(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un proyecto");
		}
		model.put("listaProyectos", pService.listar());

		return "redirect:/proyectos/listar";
	}

	@Secured({ "ROLE_JEFE", "ROLE_ENCARGADO" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Proyecto proyecto) throws ParseException {

		List<Proyecto> listaProyectos;
		proyecto.setNombreProyecto(proyecto.getNombreProyecto());
		listaProyectos = pService.buscar(proyecto.getNombreProyecto());

		if (listaProyectos.isEmpty()) {
			listaProyectos = pService.buscarUsuario(proyecto.getNombreProyecto());
		}

		if (listaProyectos.isEmpty()) {
			listaProyectos = pService.buscarArea(proyecto.getNombreProyecto());
		}

		if (listaProyectos.isEmpty()) {
			listaProyectos = pService.buscarProducto(proyecto.getNombreProyecto());
		}

		if (listaProyectos.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaProyectos", listaProyectos);
		return "proyecto/listaProyecto";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Proyecto> proyecto = pService.listarId(id);
		if (proyecto == null) {
			flash.addFlashAttribute("error", "El Proyecto no existe en la base de datos");
			return "redirect:/proyectos/listar";
		}

		model.put("proyecto", proyecto.get());

		return "proyecto/ver";
	}

	@Secured("ROLE_JEFE")
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Proyecto> objPro = pService.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/proyectos/listar";
		} else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaAreas", arService.listar());
			model.addAttribute("listaProductos", prService.listar());
			model.addAttribute("proyecto", objPro.get());
			return "proyecto/proyecto";
		}
	}
}
