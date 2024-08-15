package model.entity.transferobject;

import java.io.Serializable;
import java.util.List;

import model.entity.transferobject.abstracts.TOCadastro;

public class TOContato extends TOCadastro implements Serializable, Cloneable {

	
	public TOContato() {
		super();
	}

	public TOContato(Long id, String nomeCompleto, String telefone1, String telefone2, List<String> emails) {
		super(id, nomeCompleto, telefone1, telefone2, emails);
	}

	private static final long serialVersionUID = 2125829636829080334L;
	
}