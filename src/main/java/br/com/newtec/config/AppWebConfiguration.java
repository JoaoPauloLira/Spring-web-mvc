package br.com.newtec.config;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.expression.ParseException;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.newtec.controller.HomeController;
import br.com.newtec.controller.LoginController;
import br.com.newtec.controller.ProdutoController;
import br.com.newtec.dao.ProdutoDao;
import br.com.newtec.infra.FileSaver;
import br.com.newtec.util.SendEmail;

@EnableWebMvc
@ComponentScan(basePackageClasses = {
		// Controller's
		HomeController.class, 
		ProdutoController.class,
		LoginController.class,

		// DAO's
		ProdutoDao.class,

		// Infra
		FileSaver.class,
		SendEmail.class})
@EnableCaching
public class AppWebConfiguration implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");

		/*
		 * Dessa forma podemos deixar todos os Beans acessiveis por todas os JSP, que
		 * não é muito legal
		 */
		// resolver.setExposeContextBeansAsAttributes(true);
		/*
		 * Dessa forma podemos deixar um Bean expecifico acessiveis para todos os JSP's
		 */
		// resolver.setExposedContextBeanNames("NOME_DO_BEAN");

		return resolver;
	}

//	@Bean
//	public MessageSource messageSource() {
//		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
//		source.setBasename("/WEB-INF/messages");
//		source.setDefaultEncoding("UTP-8");
//		source.setCacheSeconds(1);
//
//		return source;
//	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);

		return conversionService;
	}

	@InitBinder // confirguração para receber a data como parametro do dentro do opbjeto
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Calendar.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String value) {
				try {
					Calendar cal = Calendar.getInstance();
					try {
						cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(value));
					} catch (java.text.ParseException e) {
						e.printStackTrace();
					}
					setValue(cal);
				} catch (ParseException e) {
					setValue(null);
				}
			}

			@Override
			public String getAsText() {
				if (getValue() == null) {
					return "";
				}
				return new SimpleDateFormat("dd-MM-yyyy").format(((Calendar) getValue()).getTime());
			}
		});
	}

	@Bean // Para receber arquivos
	public MultipartResolver multipartResolver() { // configurado no servlet Spring MVC o metodo ->
													// customizeRegistration
		return new StandardServletMultipartResolver();
	}

	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> builder = CacheBuilder
												.newBuilder()
												.maximumSize(100)//guarda 100 objetos
												.expireAfterAccess(5,TimeUnit.MINUTES); //limpa em 5 minutos o cache
		GuavaCacheManager maneger = new GuavaCacheManager();
		maneger.setCacheBuilder(builder);

		return maneger;
	}
	
	
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manage){

		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers);
		resolver.setContentNegotiationManager(manage);
		
		return resolver;
	}

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    @Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("newtec.testes.jp@gmail.com");
		mailSender.setPassword("***********");
		
		//configuração para ter uma conexão segura
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		
		mailSender.setJavaMailProperties(mailProperties);
		
		return mailSender;
	}

}
