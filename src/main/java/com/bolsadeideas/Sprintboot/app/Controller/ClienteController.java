package com.bolsadeideas.Sprintboot.app.Controller;

import java.util.List;
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


/**
 * @author maria.ortiz
 *
 */
@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	private final static Logger LOGGER = Logger.getLogger("myLogger");
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private IProductoService productoService;
	

	@RequestMapping(value="listar",method=RequestMethod.GET)
	public String listar(Model model) {
	model.addAttribute("titulo","Listado de clientes");	
	model.addAttribute("clientes",clienteService.findall());
	
	return "listar";
	}
	

	@RequestMapping(value="/form")
	public String crear(Map<String,Object>model) {
		Cliente cliente=new Cliente();
		model.put("cliente",cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
		
	}
	@RequestMapping(value="/buscar")
	public String crearBuscar(Map<String,Object>model) {
		String nombre=" ";
		model.put("nombre",nombre);
		model.put("titulo", "Buscar Cliente");
		return "buscar";
		
	}
	
		
	
	@RequestMapping(value="/form",method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario de Cliente");
			return "form";		
		}
		String mensajeFlash=(cliente.getId()!=null)? "Cliente editado con exito":"Cliente creado con exito";	
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success",mensajeFlash);
		return "redirect:listar";
		
	}
		
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String,Object> model,RedirectAttributes flash) {
		
		Cliente cliente=null;
		if(id>0) {
			cliente=clienteService.findOne(id);
			if (cliente==null) {
				flash.addFlashAttribute("error","el id del cliente no existe");
				return "redirect:/listar";
			}
		}
		else {
			flash.addFlashAttribute("error","el id del cliente no puede ser cero");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "editar cliente");
		return "/form";
		
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id,RedirectAttributes flash){
		if(id>0) {
			clienteService.delete(id);
			flash.addFlashAttribute("error","Cliente eliminado con exito");
		}
		return "redirect:/listar";
		
	}
	
	
	  
	@RequestMapping(value="/buscar/{nombre}") 
	  public String buscar(@PathVariable(value="nombre") String nombre, Map<String,Object>model,RedirectAttributes flash) {
        nombre="emilia";
		try { 
        LOGGER.info("inicio buscar cliente");
	  
		  List<Cliente> listaClientes=clienteService.findall();
		  Cliente cliente=new Cliente();	
		  LOGGER.info("valor variable path: "+nombre);
		  if(nombre==" ") {
				
				flash.addFlashAttribute("error","no se genero ningun resultado para la búsqueda");
				return "redirect:/listar";
			}
		  		  
			for (int i=0;i<listaClientes.size();i++) {
				LOGGER.info("pase por  buscar cliente por nombre");
				LOGGER.info("cliente: "+listaClientes.get(i).getNombre());
				if(listaClientes.get(i).getNombre().equals(nombre)) {
					LOGGER.info("cliente: "+listaClientes.get(i).getNombre());					
					cliente=listaClientes.get(i);
									
				}
				else {
					flash.addFlashAttribute("error","no se genero ningun resultado para la búsqueda");
					return "redirect:/listar";
				}
			}
		  						
			model.put("titulo", "busqueda de cliente");
			model.put("cliente", cliente);
        }
        catch(Exception e) {
        	LOGGER.info("Error");
        }
        LOGGER.info("fin buscar cliente");
			return "/form";
	  }
	   
	  
}
