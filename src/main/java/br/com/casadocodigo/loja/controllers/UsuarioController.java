package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/usuarios")
@Transactional
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("usuarios/form");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(usuario);
		} else if (dao.findUserByEmail(usuario.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("erro", "Email já existente");
			return new ModelAndView("redirect:/usuarios/form");
		}
		
//		É SETADO UMA NOVA ROLE PADRÃO NA INSERÇÃO, PODENDO SER ALTERADA DEPOIS
		usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));
		
//		FEITO PARA ADICIONAR SENHA CRIPTOGRAFADA
		String salGerado = BCrypt.gensalt();
		String senhaHasheada = BCrypt.hashpw(usuario.getSenha(), salGerado);
		usuario.setSenha(senhaHasheada);
		
		dao.gravar(usuario);

		redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");

		return new ModelAndView("redirect:/usuarios");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		
		List<Usuario> usuarios = dao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "alterarRoles")
	public ModelAndView mostrarRoles(String email, String nome) {
		Usuario usuario = dao.findUserByEmail(email);
		
		ModelAndView modelAndView = new ModelAndView("usuarios/alterarRoles");
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}
	
	@RequestMapping(value="roleAlterada")
	public ModelAndView alterarRole(Usuario usuario, RedirectAttributes redirectAttributes) {
		if (usuario.getRoles().isEmpty()) {
			redirectAttributes.addFlashAttribute("erro", "Ao menos uma role deve ser preenchida");
			return new ModelAndView("redirect:/usuarios");
		}
		
		dao.alterarRole(usuario);
		
		redirectAttributes.addFlashAttribute("sucesso", "Role alterada com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
	
}
