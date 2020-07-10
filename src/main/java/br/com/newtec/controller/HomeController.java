package br.com.newtec.controller;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.newtec.dao.UsuarioLoginDao;
import br.com.newtec.model.Role;
import br.com.newtec.model.Usuario;

@Controller
public class HomeController {

	@Autowired
	private UsuarioLoginDao usuarioDao;
	
	@RequestMapping("/")
	@Cacheable(value="IndexCache")
	public String index() {
		System.out.println("vai gota");
		
		return "home";
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping("/geraUsuario_144875sdfsfsf157grghtrxsvzcvnkewkwrykcbvcx-sggr548e-487-99")
	public String geraUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setEmail("joao.p.lira@gmail.com");
		usuario.setSenha("123456");
		usuario.setNome("João Paulo");
		usuario.setRoles(Arrays.asList( new Role("ADMIN")));
		
		usuarioDao.save(usuario);
		
		return "Usuario Criado com Sucesso!";
	}
	
}
