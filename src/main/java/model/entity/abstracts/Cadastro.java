package model.entity.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Cadastro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_completo", nullable = false, length = 80)
	private String nomeCompleto;

	private String telefone1;
	
	private String telefone2;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> emails = new ArrayList<String>();
	
	public Cadastro() {
		super();
	}

	public Cadastro(Long id, String nomeCompleto, String telefone1, String telefone2, List<String> emails) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.emails = emails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

}
