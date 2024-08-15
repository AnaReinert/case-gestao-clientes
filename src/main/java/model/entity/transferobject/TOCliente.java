package model.entity.transferobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import model.entity.transferobject.abstracts.TOCadastro;

public class TOCliente extends TOCadastro {

    private Date dataRegistroCliente;
	
    private List<TOContato> contatos;
    private Set<TOContato> contatosSet;
    
    public TOCliente(Long idCadastro, String nomeCompleto, String telefone1, String telefone2, Date dataRegistroCliente, List<String> emails, List<TOContato> contatos) {
		super(idCadastro, nomeCompleto,telefone1, telefone2, emails);
		this.dataRegistroCliente = dataRegistroCliente;
		this.contatos = contatos;
	}

	public TOCliente(Date dataRegistroCliente) {
    	contatos = new ArrayList<TOContato>();
		this.dataRegistroCliente = dataRegistroCliente;
	}

	public TOCliente() {
    	contatos = new ArrayList<TOContato>();
	}
	
	public Date getDataRegistroCliente() {
		return dataRegistroCliente;
	}

	public void setDataRegistroCliente(Date dataRegistroCliente) {
		this.dataRegistroCliente = dataRegistroCliente;
	}

	public List<TOContato> getContatos() {
		return contatos;
	}

	public void setContatos(List<TOContato> contatos) {
		this.contatos = contatos;
	}

	public Set<TOContato> getContatosSet() {
		return contatosSet;
	}

	public void setContatosSet(Set<TOContato> contatosSet) {
		this.contatosSet = contatosSet;
	}
	
}