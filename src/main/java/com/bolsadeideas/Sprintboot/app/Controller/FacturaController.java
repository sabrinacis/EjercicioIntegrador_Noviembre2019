package com.bolsadeideas.Sprintboot.app.Controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bolsadeideas.Sprintboot.app.models.entity.Factura;
import com.bolsadeideas.Sprintboot.app.models.service.IClienteService;
import com.bolsadeideas.Sprintboot.app.models.service.IFacturaService;
import com.bolsadeideas.Sprintboot.app.models.service.IProductoService;

@Controller
@SessionAttributes("factura")
public class FacturaController {

	private final static Logger LOGGER = Logger.getLogger("myLogger");

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IFacturaService facturaService;

	@RequestMapping(value = "/factura")
	public String crear(Map<String, Object> model) {

		model.put("factura", new Factura());
		model.put("producto", productoService.findall());
		model.put("titulo", "Crear Factura de Cliente");
		return "factura";
	}

	@RequestMapping(value = "/factura", method = RequestMethod.POST)
	public String guardar(@Valid Factura factura, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		LOGGER.info("comienzo controller guardar Factura");

		List<Factura> lista;
		lista = facturaService.findall();

		try {

			if (result.hasErrors()) {
				flash.addFlashAttribute("error", "debe completar todos los campos");
				model.addAttribute("producto", productoService.findall());
				model.addAttribute("factura", factura);
				model.addAttribute("titulo", "crear factura");
				return "/factura";
			}

			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getNombreCliente().equals(factura.getNombreCliente())
						& lista.get(i).getProductoId().equals(factura.getProductoId())) {
					LOGGER.info("la factura ya exite");
					flash.addFlashAttribute("error", "Ya hay registrada una factura");
					return "redirect:listar";
				}
			}
			facturaService.save(factura);
			LOGGER.info("la factura con nombre cliente: " + factura.getNombreCliente() + " y numero producto: "
					+ factura.getProductoId() + " se genero con exito");
			status.setComplete();
			flash.addFlashAttribute("success", "la factura se creo con exito");
			LOGGER.info("el proceso termino con exito");

		} catch (Exception e) {
			LOGGER.info("Error");

		}

		LOGGER.info("fin controller guardar factura");
		return "redirect:listar";

	}

	@RequestMapping(value = "/eliminarProducto/{id}/{idProducto}")
	public String eliminarProducto(@PathVariable(value = "id") Long id,
			@PathVariable(value = "idProducto") Long idProducto, Map<String, Object> model, RedirectAttributes flash) {
		try {
			if (idProducto > 0) {
				productoService.delete(id);
				flash.addFlashAttribute("error", "Producto eliminado con exito");
				model.put("producto", productoService.findall());
				model.put("factura", facturaService.findOne(id));
				return "redirect:/factura";
			}

		} catch (Exception e) {
			LOGGER.info("Error");
		}
		return "/factura";

	}

	@RequestMapping(value = "/factura/{id}")
	public String factura(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		try {
			Factura factura = new Factura();
			factura.setNombreCliente(
					clienteService.findOne(id).getNombre() + " " + clienteService.findOne(id).getApellido());
			model.put("producto", productoService.findall());
			model.put("factura", factura);
			model.put("titulo", "crear factura");
		} catch (Exception e) {

			LOGGER.info("Error");
		}

		return "factura";

	}

}
