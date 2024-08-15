package repository;

import java.util.ArrayList;
import java.util.List;

import model.entity.transferobject.TOCliente;
import model.entity.transferobject.TOContato;

public final class BancoDB {

	private static BancoDB instance;
	
	private List<TOCliente> clientes = new ArrayList<TOCliente>();
	private List<TOContato> contatos = new ArrayList<TOContato>();
	private TOCliente clienteLogado;

	public static BancoDB getInstance() {
		if (instance == null) {
			instance = new BancoDB();
		}
		return instance;
	}

	public List<TOCliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<TOCliente> clientes) {
		this.clientes = clientes;
	}

	public List<TOContato> getContatos() {
		return contatos;
	}

	public void setContatos(List<TOContato> contatos) {
		this.contatos = contatos;
	}

	public TOCliente getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(TOCliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}
	
}
