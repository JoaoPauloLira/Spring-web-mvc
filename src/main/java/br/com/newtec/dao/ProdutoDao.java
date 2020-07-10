package br.com.newtec.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.newtec.model.Produto;

@Repository
@Transactional
public class ProdutoDao {

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos", Produto.class)
				.getResultList();
	}

	public Produto getProduto(Long id) {
		return manager.createQuery(
				"select distinct(p) " + "from Produto p " + "join fetch p.precos precos " + "where p.codigo = :id",
				Produto.class).setParameter("id", id).getSingleResult();
	}


	public BigDecimal somaPrecoVendaProdutosPorUnidadeMedida(int unidadeMedida) {

		String uni = "Unidade " + unidadeMedida;

		List<Produto> produtos = manager
				.createQuery("select p from Produto p join fetch p.precos precos "
						+ "where p.unidadeMedida = :uni " + "and precos.tipo = 1", Produto.class)
				.setParameter("uni", uni).getResultList();

		System.out.println(somaValorVenda(produtos));
		
		return somaValorVenda(produtos);
	}

	private BigDecimal somaValorVenda(List<Produto> list) {
		return new BigDecimal(
				list.stream().map(x -> x.getPrecos().get(0).getValor()).mapToDouble(BigDecimal::doubleValue).sum(),
				MathContext.DECIMAL64);
	}
	
}
