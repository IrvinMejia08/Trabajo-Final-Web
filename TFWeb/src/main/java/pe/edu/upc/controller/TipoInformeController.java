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

import pe.edu.upc.entity.TipoInforme;
import pe.edu.upc.service.ITipoInformeService;

@Controller
@RequestMapping("/tipoinformes")
public class TipoInformeController {

	@Autowired
	private ITipoInformeService tpService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}

	@GetMapping("/nuevo")
	public String nuevoTipoInforme(Model model) {
		model.addAttribute("tipoinforme", new TipoInforme());
		return "tipoinforme/tipoinforme";
	}

	@PostMapping("/guardar")
	public String guardarTipoInforme(@Valid TipoInforme tipoinforme, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "tipoinforme/tipoinforme";
		} else {
			int rpta = tpService.insertar(tipoinforme);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/tipoinforme/tipoinforme";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaTipoInformes", tpService.listar());

		return "/tipoinforme/listaTipoInforme";
	}

	@GetMapping("/listar")
	public String listarTipoInformes(Model model) {
		try {
			model.addAttribute("tipoinforme", new TipoInforme());
			model.addAttribute("listaTipoInformes", tpService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tipoinforme/listaTipoInforme";
	}

	@GetMapping("/detalle/{id}")
	public String detailsTipoInforme(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<TipoInforme> tipoinforme = tpService.listarId(id);
			if (!tipoinforme.isPresent()) {
				model.addAttribute("info", "El tipo de informe no existe");
				return "redirect:/tipoinformes/listar";
			} else {
				model.addAttribute("tipoinforme", tipoinforme.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tipoinforme/tipoinforme";
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
			model.put("mensaje", "No se puede eliminar un tipo de informe.");
		}
		model.put("listaTipoInformes", tpService.listar());

		return "redirect:/tipoinforme/listar";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute TipoInforme tipoinforme) throws ParseException {

		List<TipoInforme> listaTipoInformes;

		tipoinforme.setNombreTipoInforme(tipoinforme.getNombreTipoInforme());
		listaTipoInformes = tpService.buscarnombreTipoInforme(tipoinforme.getNombreTipoInforme());

		if (listaTipoInformes.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaTipoInformes", listaTipoInformes);
		return "tipoinforme/listaTipoInforme";
	}

}
