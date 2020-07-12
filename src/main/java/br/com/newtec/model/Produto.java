package br.com.newtec.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.newtec.model.enums.Situacao;

@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataUltimaAlteracao;
	
	@NotNull(message = "{field.required.produto.descricao}")
	@Size(min = 5, message = "Valor menos que 5")
	private String descricao;
	
	private String unidadeMedida;
	private String codigobarras;
	private String sessaoProduto;
	private String grupo;
	private String subGrupo;
	private Situacao situacao;
	private double margemLucro;
	private String referencia;
	private double estoqueMinimo;
	private double estoqueMaximo;
	private double estoqueAtual;
	
	private String imgPath;

	
	@ElementCollection //cria uma tabale de relacionamento de-para no banco 
	private List<Preco> precos = new ArrayList<Preco>();
	
	private String observacao;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Calendar getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Calendar dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public String getCodigobarras() {
		return codigobarras;
	}
	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}
	public String getSessaoProduto() {
		return sessaoProduto;
	}
	public void setSessaoProduto(String sessaoProduto) {
		this.sessaoProduto = sessaoProduto;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getSubGrupo() {
		return subGrupo;
	}
	public void setSubGrupo(String subGrupo) {
		this.subGrupo = subGrupo;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public double getMargemLucro() {
		return margemLucro;
	}
	public void setMargemLucro(double margemLucro) {
		this.margemLucro = margemLucro;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public double getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public double getEstoqueMaximo() {
		return estoqueMaximo;
	}
	public void setEstoqueMaximo(double estoqueMaximo) {
		this.estoqueMaximo = estoqueMaximo;
	}
	public double getEstoqueAtual() {
		return estoqueAtual;
	}
	public void setEstoqueAtual(double estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public List<Preco> getPrecos() {
		return precos;
	}
	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", dataCadastro=" + dataCadastro + ", dataUltimaAlteracao="
				+ dataUltimaAlteracao + ", descricao=" + descricao + ", unidadeMedida=" + unidadeMedida
				+ ", codigobarras=" + codigobarras + ", sessaoProduto=" + sessaoProduto + ", grupo=" + grupo
				+ ", subGrupo=" + subGrupo + ", situacao=" + situacao + ", margemLucro=" + margemLucro + ", referencia="
				+ referencia + ", estoqueMinimo=" + estoqueMinimo + ", estoqueMaximo=" + estoqueMaximo
				+ ", estoqueAtual=" + estoqueAtual + ", precos=" + precos + ", observacao=" + observacao + "]";
	}
	
}
