package br.com.newtec.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.newtec.builder.ProdutoBuilder;
import br.com.newtec.config.DataSourceConfigurationTest;
import br.com.newtec.config.JPAConfiguration;
import br.com.newtec.model.Produto;


@RunWith(SpringJUnit4ClassRunner.class)// essa é a classe do spring responsavel por rodar os testes
@ContextConfiguration(classes ={
								JPAConfiguration.class, 
								ProdutoDao.class,
								DataSourceConfigurationTest.class
								}) //informa as classes responsaveis por configuração e rodar o JPA
@ActiveProfiles("test")
public class ProdutoDaoTest {

	@Autowired
	private ProdutoDao dao;
	
	@Test
	@Transactional
	public void deveSomarTodosOsPrecosPorGrupoDeProduto() {
		
		List<Produto> listProdutos = ProdutoBuilder.newProduto(BigDecimal.TEN, 1).more(3).buildAll();
		
		listProdutos.stream().forEach(dao::salvar);
		
		BigDecimal valor = dao.somaPrecoVendaProdutosPorUnidadeMedida(1);

		Assert.assertEquals(new BigDecimal(40).setScale(2), valor.setScale(2));
		
		
	}
	
}
