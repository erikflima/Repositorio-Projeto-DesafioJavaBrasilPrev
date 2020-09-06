package com.eriklima.desafio.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity                                         
@Table(name = "cliente")                        
public class Cliente implements Serializable {


	private static final long serialVersionUID = 3960436649365666213L;
	

	@Id                                          
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idcli;
	
	
	@Column(name = "nome", nullable = false)
	private String  nome;
	
	
	@Column(name = "cpf", nullable = false)
	private String cpf;	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataCriacao", nullable = false)
	private Date dataCriacao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataAtualizacao", nullable = false)
	private Date dataAtualizacao;
	

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	private List<Pedido> pedidos;
	

	public Cliente() {
	}

	
	//-----------Getters and Setters-------------//
	
    public Long getIdcli() {
		return idcli;
	}


	public void setIdcli(Long idcli) {
		this.idcli = idcli;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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