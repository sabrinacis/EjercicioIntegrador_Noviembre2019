package com.bolsadeideas.Sprintboot.app.Controller;

import java.util.Map;
import java.util.logging.Logger;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bolsadeideas.Sprintboot.app.models.entity.Cliente;
import com.bolsadeideas.Sprintboot.app.models.entity.Factura;
import com.bolsadeideas.Sprintboot.app.models.entity.Producto;
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
	
	
	@RequestMapping(value="/factura") 
	  public String crear(Map<String,Object> model) {
	  
	  model.put("factura",new Factura()); 
	  model.put("producto", productoService.findall());
	  model.put("titulo", "Crear Factura de Cliente");
	  return "factura"; 
	  }
	  
		@RequestMapping(value="/factura",method=RequestMethod.POST) 
		public String guardar(@Valid Factura factura,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) {
			LOGGER.info("comienzo controller guardar Factura");
			
			try {
				
				if(result.hasErrors()) {
					flash.addFlashAttribute("error","debe completar todos los campos");
					model.addAttribute("producto",productoService.findall());
					model.addAttribute("factura",factura);
					return "/factura";		
				}									
	
				facturaService.save(factura);			
				status.setComplete();
				flash.addFlashAttribute("success","la factura se creo con exito");
				LOGGER.info("el proceso termino con exito");
		
		    } catch (Exception e) {
		       LOGGER.info("Error");
		       
		    }
					
			LOGGER.info("fin controller guardar factura");
			return "redirect:listar";
			
			
		}
	 
		
		  @RequestMapping(value="/factura/{id}") 
		  public String factura(@PathVariable(value="id") Long id,Map<String,Object> model) {
			  try {
				  Factura factura=new Factura();
			      factura.setNombreCliente(clienteService.findOne(id).getNombre()+" "+clienteService.findOne(id).getApellido());		  
				  model.put("producto", productoService.findall());
				  model.put("factura", factura);
				  model.put("titulo","crear factura"); 
			  }
			  catch(Exception e){
				  LOGGER.info("Error");
			  }
		  return "factura";
		  
		  }
		  
		 		  
	  
}
	 
	 