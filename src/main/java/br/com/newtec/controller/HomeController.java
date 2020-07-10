package br.com.newtec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.newtec.dao.UsuarioDao;
import br.com.newtec.model.Role;
import br.com.newtec.model.Usuario;
import br.com.newtec.util.SendEmail;

@Controller
public class HomeController {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping("/")
	@Cacheable(value="IndexCache")
	public String index() {
		System.out.println("vai gota");
		
//		Role role = new Role("ROLE_ADMIN");
//		
//		List<Role> roles = new ArrayList<Role>();
//		roles.add(role);
//		
//		Usuario usuario = new Usuario();
//		usuario.setEmail("joao.p.lira@gmail.com");
//		usuario.setSenha("123456");
//		usuario.setNome("João Paulo");
//		usuario.setRoles(roles);
//		
//		usuarioDao.save(usuario);
		
		return "home";
	}
	
}
