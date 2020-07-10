package br.com.newtec.controller;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.newtec.config.AppWebConfiguration;
import br.com.newtec.config.DataSourceConfigurationTest;
import br.com.newtec.config.JPAConfiguration;
import br.com.newtec.config.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)// essa é a classe do spring responsavel por rodar os testes
@WebAppConfiguration
@ContextConfiguration(classes ={
								JPAConfiguration.class, 
								AppWebConfiguration.class,
								DataSourceConfigurationTest.class,
								SecurityConfiguration.class
								}) //informa as classes responsaveis por configuração e rodar o JPA
@ActiveProfiles("test")
public class ProdutoContollerTest {

	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void deveChamarUmViewDeProdutos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos"))
		.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/listaProdutos.jsp"));
	}
	
	@Test
	public void deveChamarUmViewDeProdutosTrazendoUmaListaDeProdutos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"));
	}
	
	@Test
	public void somenteAdminDeveAcessarCadastroProdutos() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/cadastro")
				.with(SecurityMockMvcRequestPostProcessors
						.user("p.lira@gmail.com")
						.password("123456")
						.roles("VENDEDOR")))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	
	
}
