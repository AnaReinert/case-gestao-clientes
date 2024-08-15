package model.entity.transferobject.abstracts;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class TOCadastro {

	private Long id;
	
	private String nomeCompleto;

	private String telefone1;
	
	private String telefone2;

	private List<String> emails;
	
	public TOCadastro() {
		emails = new ArrayList<>();
	}
	
	public TOCadastro(Long id, String nomeCompleto, String telefone1, String telefone2, List<String> emails) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
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

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TOCadastro other = (TOCadastro) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
