package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.entity.Contato;
import model.entity.transferobject.TOContato;

public class TOtoEntityConverter {

	public List<TOContato> convertEntityToTO(Set<Contato> contatos) {
		List<TOContato> tos = new ArrayList<TOContato>();
		for (Contato contato : contatos) {
			tos.add(new TOContato(contato.getId(), contato.getNomeCompleto(), contato.getTelefone1(),
					contato.getTelefone2(), contato.getEmails()));
		}
		return tos;

	}

	public Set<Contato> convertTOtoEntity(List<TOContato> list) {
		Set<Contato> tos = new HashSet<Contato>();
		for (TOContato contato : list) {
			tos.add(new Contato(contato.getId(), contato.getNomeCompleto(), contato.getTelefone1(),
					contato.getTelefone2(), contato.getEmails()));
		}
		return tos;

	}

}
