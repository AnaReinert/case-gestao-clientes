package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.entity.transferobject.TOCliente;
import service.ClienteSBean;

@ViewScoped
@Named("registroMBean")
public class RegistroMBean implements Serializable {

	private static final long serialVersionUID = -7039227559844410301L;
	
	private List<TOCliente> clientes;
	private ClienteSBean clienteSbean;

	public RegistroMBean() {
		this.clientes = getClienteSbean().listarClientes();
	}

	public List<TOCliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<TOCliente> clientes) {
		this.clientes = clientes;
	}

	public ClienteSBean getClienteSbean() {
		if (clienteSbean == null)
			clienteSbean = new ClienteSBean();
		return clienteSbean;
	}
	
}
