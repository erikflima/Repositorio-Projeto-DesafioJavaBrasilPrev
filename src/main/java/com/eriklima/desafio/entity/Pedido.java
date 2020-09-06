package com.eriklima.desafio.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity                                         
@Table(name = "pedido")                        
public class Pedido implements Serializable {


	private static final long serialVersionUID = 3960436649365666212L;
	

	@Id                                          
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idpedido;


	@Column(name = "precoPedido", nullable = false)
	private Double precoPedido;	
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataCriacao", nullable = false)
	private Date dataCriacao;
	
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "dataAtualizacao", nullable = false)
	private Date dataAtualizacao;
	


	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;

	
	@ManyToMany
	private List<Produto> produtos;	

	
	public Pedido() {
	}


	//-----------Getters and Setters-------------//
	
	public Long getIdpedido() {
		return idpedido;
	}


	public void setIdpedido(Long idpedido) {
		this.idpedido = idpedido;
	}


	public Double getPrecoPedido() {
		return precoPedido;
	}


	public void setPrecoPedido(Double precoPedido) {
		this.precoPedido = precoPedido;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	/*
	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	*/
	
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