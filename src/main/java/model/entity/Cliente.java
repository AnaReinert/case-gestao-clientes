package model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.entity.abstracts.Cadastro;

@Entity
public class Cliente extends Cadastro{
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cliente_contato",
               joinColumns = @JoinColumn(name = "cliente_id"),
               inverseJoinColumns = @JoinColumn(name = "contato_id"))
	private Set<Contato> contatos = new HashSet<>();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_registro")
	private Date dataRegistro;

	public Set<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
}
