package br.com.newtec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.newtec.dao.ProdutoDao;
import br.com.newtec.infra.FileSaver;
import br.com.newtec.model.Produto;
import br.com.newtec.model.enums.TipoPreco;
import br.com.newtec.util.SendEmail;
import br.com.newtec.validations.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private FileSaver fileSave;
	
	@Autowired
	private SendEmail send;
	
//	@InitBinder
//	public void InitBinder(WebDataBinder dataBinder) {
//		dataBinder.addValidators(new ProdutoValidation());
//	}
	
	@RequestMapping("/cadastro")
	@CacheEvict(value="IndexCache",allEntries = true)
	public ModelAndView CadastroProduto() {
		
		System.out.println("Cadastro de produtos");
		ModelAndView view = new ModelAndView("cadastroProduto");
		view.addObject("tiposPreco", TipoPreco.values());

		
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView Salvar(MultipartFile imgPath,
 							   @Valid Produto produto, 
							   BindingResult result, //BindingResult Sempre colocar depois do obj que vai receber a validação
							   RedirectAttributes attributes
									  ) {
		
		if(result.hasErrors()) {
			return new ModelAndView("cadastroProduto");
		}
		
		String nameImg = fileSave.SaveFale("FotosProdutos", imgPath);
		
		produto.setImgPath(nameImg);
		
		produtoDao.salvar(produto);
		
		send.enviarEmail("Produto: "+produto.getDescricao()+" cadastrado com sucesso!", "joao.p.lira@gmail.com", "O Produto foi cadastrado com sucesso!");
		
		ModelAndView view = new ModelAndView("redirect:/produtos");
		attributes.addFlashAttribute("sucesso","Produto Cadastrado com Sucesso");
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView Lista(){
		ModelAndView view = new ModelAndView("listaProdutos");
		List<Produto> lista = produtoDao.listar();
		view.addObject("produtos",lista);
		
		return view;
	}
	
	
	@RequestMapping("/editar")
	public ModelAndView EditarProduto(Long codigo) {
		
		ModelAndView view = new ModelAndView("editarProduto");
		Produto produto = produtoDao.getProduto(codigo);
		view.addObject("produto",produto);
		System.out.println(produto);

		return view;	
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Produto ListarProdutoJson(@PathVariable("id") Long id) {
		return produtoDao.getProduto(id);		
	}
	
	
	
	
}
