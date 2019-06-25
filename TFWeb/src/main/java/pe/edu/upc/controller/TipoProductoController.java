package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.entity.TipoProducto;
import pe.edu.upc.service.ITipoProductoService;

@Controller
@RequestMapping("/tipoproductos")
public class TipoProductoController {

	@Autowired
	private ITipoProductoService tpService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}

	@GetMapping("/nuevo")
	public String nuevoTipoProducto(Model model) {
		model.addAttribute("tipoproducto", new TipoProducto());
		return "tipoproducto/tipoproducto";
	}

	@PostMapping("/guardar")
	public String guardarTipoProducto(@Valid TipoProducto tipoproducto, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "tipoproducto/tipoproducto";
		} else {
			int rpta = tpService.insertar(tipoproducto);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/tipoproducto/tipoproducto";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaTipoProductos", tpService.listar());

		return "/tipoproducto/listaTipoProducto";
	}

	@GetMapping("/listar")
	public String listarTipoProductos(Model model) {
		try {
			model.addAttribute("tipoproducto", new TipoProducto());
			model.addAttribute("listaTipoProductos", tpService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tipoproducto/listaTipoProducto";
	}

	@GetMapping("/detalle/{id}")
	public String detailsTipoProducto(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<TipoProducto> tipoproducto = tpService.listarId(id);
			if (!tipoproducto.isPresent()) {
				model.addAttribute("info", "El tipo de producto no existe");
				return "redirect:/tipoproductos/listar";
			} else {
				model.addAttribute("tipoproducto", tipoproducto.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tipoproducto/tipoproducto";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				tpService.eliminar(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un tipo de producto.");
		}
		model.put("listaTipoProductos", tpService.listar());

		return "redirect:/tipoproducto/listar";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute TipoProducto tipoproducto) throws ParseException {

		List<TipoProducto> listaTipoProductos;

		tipoproducto.setNombreTipoProducto(tipoproducto.getNombreTipoProducto());
		listaTipoProductos = tpService.buscarnombreTipoProducto(tipoproducto.getNombreTipoProducto());

		if (listaTipoProductos.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaTipoProductos", listaTipoProductos);
		return "tipoproducto/listaTipoProducto";
	}

}
