package com.eriklima.desafio.entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity                                         
@Table(name = "produto")                        
public class Produto implements Serializable {


	private static final long serialVersionUID = 3960436649365666211L;
	

	@Id                                          
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idProduto;

	
	@Column(name = "nome", nullable = true)
	private String nome;
	
	
	@Column(name = "descricao", nullable = true)
	private String descricao;
	
	
	@Column(name = "quantidade", nullable = true)
	private Integer quantidade;	
	
	
	@Column(name = "precoUnitario", nullable = false)
	private Double precoUnitario;
	
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "dataCriacao", nullable = false)
	private Date dataCriacao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataAtualizacao", nullable = false)
	private Date dataAtualizacao;


	public Produto() {
	}


	//----------Metodos adicionais--------------//	
	
	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	
	//----------Metodos adicionais--------------//
	
	@PreUpdate
    public void preUpdate() {
		
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
    	
        final Date atual = new Date();
        dataCriacao      = atual;
        dataAtualizacao  = atual;
    }	

}