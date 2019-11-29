package com.bolsadeideas.Sprintboot.app.Controller;

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
import java.util.logging.Logger;

import com.bolsadeideas.Sprintboot.app.models.entity.Factura;
import com.bolsadeideas.Sprintboot.app.models.entity.Producto;
import com.bolsadeideas.Sprintboot.app.models.service.FacturaServiceImplement;
import com.bolsadeideas.Sprintboot.app.models.service.IFacturaService;
import com.bolsadeideas.Sprintboot.app.models.service.IProductoService;


@Controller
@SessionAttributes("producto")
public class ProductoController {
		
	private final static Logger LOGGER = Logger.getLogger("myLogger");
	
	@Autowired 
	private IProductoService productoService;
	
	@Autowired 
	private IFacturaService facturaService;
	
	  @RequestMapping(value="/agregar_producto") 
	  public String crear(Map<String,Object> model) {
	  
	  model.put("producto",new Producto()); 
	  model.put("productos", productoService.findall());
	  model.put("titulo", "Agregar Producto");
	  return "agregar_producto"; 
	  }
	  
		@RequestMapping(value="/agregar_producto",method=RequestMethod.POST)
		public String guardar(@Valid Producto producto,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) {
			LOGGER.info("inicio agregar producto controller");
			try {
			if(result.hasErrors()) {
				model.addAttribute("titulo","agregar producto");
				model.addAttribute(productoService.findall());
				return "agregar_producto";		
			}
			LOGGER.info("valor producto mapeado= "+producto.getNombre()+" precio: "+producto.getPrecio());
			
			if((producto.getNombre()!=null)&&(producto.getPrecio()!=null)) {
				
				productoService.save(producto);
				status.setComplete();
				flash.addFlashAttribute("success","producto creado con exito");
				LOGGER.info("el producto: "+producto.getNombre()+" fue guardado exitosamente");
				
			}
			else {
			flash.addFlashAttribute("error","todos los campos son requeridos");
			model.addAttribute(productoService.findall());
			return "redirect:agregar_producto";
			
			}
			
			}
			catch(Exception e) {
				LOGGER.info("error");
				model.addAttribute(productoService.findall());
				return "redirect:agregar_producto";
				
			}
			LOGGER.info("fin agregar producto controller");
			return "redirect:agregar_producto";
			
		}
		
		@RequestMapping(value="/eliminarProducto/{id}")
		public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes flash){
			if(id>0) {
				productoService.delete(id);
				flash.addFlashAttribute("error","Producto eliminado con exito");
			
			}
			
			return "redirect:/agregar_producto";
			
		}
		 
}
	 
	 