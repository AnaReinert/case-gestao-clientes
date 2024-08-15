package model.entity;

import java.util.List;

import javax.persistence.Entity;

import model.entity.abstracts.Cadastro;

@Entity
public class Contato extends Cadastro {

	public Contato() {
	}
	
	public Contato(Long id, String nomeCompleto, String telefone1, String telefone2, List<String> emails) {
		super(id, nomeCompleto, telefone1, telefone2, emails);
	}
}
