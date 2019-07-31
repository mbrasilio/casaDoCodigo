package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.DadosPedidos;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PedidosServicoController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/pedidos",  method = RequestMethod.GET)
	public ModelAndView listarPedidos(RedirectAttributes model) {
		
		String uri = "https://book-payment.herokuapp.com/orders";
		DadosPedidos[] response = restTemplate.getForObject(uri, DadosPedidos[].class);
		
		ModelAndView modelAndView = new ModelAndView("pedidos/lista");
		modelAndView.addObject("response", response);
		
		return modelAndView;
	}
	
}
