package br.com.newtec.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.newtec.config.AppWebConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) // essa é a classe do spring responsavel por rodar os testes
@ContextConfiguration(classes = { AppWebConfiguration.class, SendEmail.class }) // informa as classes responsaveis por configuração e
																// rodar o JPA
@ActiveProfiles("test")
public class SendEmailTest {

	@Autowired
	private SendEmail email;

	@Test
	public void deveEnviarUmEmail() {

		boolean retorno = email.enviarEmail("Produto cadastrado com sucesso!", "joao.p.lira@gmail.com",
				"O Produto foi cadastrado com sucesso!");
		Assert.assertEquals(true, retorno);

	}

}
