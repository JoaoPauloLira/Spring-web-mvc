package br.com.newtec.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.newtec.model.Preco;
import br.com.newtec.model.Produto;
import br.com.newtec.model.enums.Situacao;
import br.com.newtec.model.enums.TipoPreco;

public class ProdutoBuilder {

	private List<Produto> listaProdutos = new ArrayList<Produto>();
	private static int indece = 0;

	public ProdutoBuilder(Produto podutos) {
			this.listaProdutos.add(podutos);
	}
	
	public static ProdutoBuilder newProduto(BigDecimal valor, int unidadeMedida) {
		Produto p = create(TipoPreco.VENDA, valor, unidadeMedida);
		return new ProdutoBuilder(p);
	}
	
	public static ProdutoBuilder newProduto() {
		Produto p = create(TipoPreco.VENDA, BigDecimal.TEN, 5);
		return new ProdutoBuilder(p);
	}
	
	private static Produto create(TipoPreco tipo,BigDecimal valor, int unidadeMedida) {
		
		Produto produto = new Produto();
		produto.setCodigobarras("123456789");
		produto.setDataCadastro(Calendar.getInstance());
		produto.setDataUltimaAlteracao(Calendar.getInstance());
		produto.setDescricao("Produto Teste " + getIndece());
		produto.setEstoqueAtual(10);
		produto.setEstoqueMaximo(100);
		produto.setEstoqueMinimo(5);
		produto.setGrupo("Grupo 1");
		produto.setImgPath("img");
		produto.setMargemLucro(25);
		produto.setObservacao("teste de observação");
		
		Preco preco = new Preco();
		preco.setTipo(tipo);
		preco.setValor(valor);
		
		produto.getPrecos().add(preco);
		produto.setReferencia("Referencia 001");
		produto.setSessaoProduto("Sessão 01");
		produto.setSituacao(Situacao.ATIVO);
		produto.setSubGrupo("SubGrupo 1");
		produto.setUnidadeMedida("Unidade "+unidadeMedida);
		
		return produto;
	}
	
	public ProdutoBuilder more(int number) {
        Produto base = listaProdutos.get(0);
        Preco preco = base.getPrecos().get(0);
        for (int i = 0; i < number; i++) {
        	listaProdutos.add(create(TipoPreco.VENDA,preco.getValor(),1));
        }
        return this;
    }
	
	
	private static int getIndece() {
		return indece++;
	}
	
	public List<Produto> buildAll() {
        return listaProdutos;
    }
	
}
