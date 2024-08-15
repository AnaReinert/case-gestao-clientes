package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import controller.abstracts.CrudBaseMBean;
import model.entity.transferobject.TOCliente;
import model.entity.transferobject.TOContato;
import service.ClienteSBean;
import service.ContatoSBean;

@ViewScoped
@Named("clienteMBean")
public class ClienteMBean extends CrudBaseMBean implements Serializable {

	private static final long serialVersionUID = 6182921764947574982L;
	private TOCliente cliente;
	private TOCliente clienteSelected;
	private List<TOContato> contatosSelected;
	private ClienteSBean clienteSbean;
	private ContatoSBean contatoSbean;
	private List<TOCliente> clientes;
	private List<TOContato> contatos;
	private TOContato contatoClienteSelected;
	private List<TOContato> contatosClienteSelected;

	public ClienteMBean() {
		super();
		this.clienteSelected = new TOCliente();
		this.contatosSelected = new ArrayList<TOContato>();
		this.contatosClienteSelected = new ArrayList<TOContato>();
		this.cliente = new TOCliente(new Date());
		this.clientes = getClienteSbean().listarClientes();
		this.contatos = getContatoSbean().listarContatos();
	}

	@Override
	public void onCadastrar() {
		if (validarEmail(cliente.getEmails())) {
			adicionarContato();
			getClienteSbean().salvarCliente(cliente);
			showMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado com sucesso!");
			onLimparCadastro();
		}
		this.clientes = getClienteSbean().listarClientes();
	}

	@Override
	public void onAtualizar() {
		if (validarEmail(clienteSelected.getEmails())) {
			getClienteSbean().atualizarCliente(clienteSelected);
			this.clientes = getClienteSbean().listarClientes();
			showMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso!");
			PrimeFaces.current().executeScript("PF('cadastroClientesDialog').hide()");
		}
	}

	@Override
	public void onExcluir() {
		getClienteSbean().remover(cliente);
		this.clientes = getClienteSbean().listarClientes();
	}

	@Override
	public void onLimparCadastro() {
		this.cliente = new TOCliente(new Date());
		this.contatosSelected = new ArrayList<TOContato>();
	}

	public void adicionarContato() {
		cliente.setContatos(contatosSelected);
	}

	public void onExcluirContato() {
		clienteSelected.getContatos().remove(contatoClienteSelected);
	}

	public List<TOContato> onRemoverContatoExtra() {

		List<TOContato> contatosDoCliente = clienteSelected.getContatos();

		Set<Long> idsDosContatosDoCliente = contatosDoCliente.stream().map(TOContato::getId)
				.collect(Collectors.toSet());

		return contatos.stream().filter(contato -> !idsDosContatosDoCliente.contains(contato.getId()))
				.collect(Collectors.toList());

	}

	public void atualizarContatosCliente() {
		for (TOContato contato : contatosClienteSelected) {
			clienteSelected.getContatos().add(contato);
		}
	}

	public void onRowSelect(SelectEvent<?> event) {
		clienteSelected = (TOCliente) event.getObject();
	}

	public ClienteSBean getClienteSbean() {
		if (clienteSbean == null)
			clienteSbean = new ClienteSBean();
		return clienteSbean;
	}

	public ContatoSBean getContatoSbean() {
		if (contatoSbean == null)
			contatoSbean = new ContatoSBean();
		return contatoSbean;
	}

	public TOCliente getCliente() {
		return cliente;
	}

	public void setCliente(TOCliente cliente) {
		this.cliente = cliente;
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

	public TOCliente getClienteSelected() {
		return clienteSelected;
	}

	public void setClienteSelected(TOCliente clienteSelected) {
		this.clienteSelected = clienteSelected;
	}

	public List<TOContato> getContatosSelected() {
		return contatosSelected;
	}

	public void setContatosSelected(List<TOContato> contatosSelected) {
		this.contatosSelected = contatosSelected;
	}

	public TOContato getContatoClienteSelected() {
		return contatoClienteSelected;
	}

	public void setContatoClienteSelected(TOContato contatoClienteSelected) {
		this.contatoClienteSelected = contatoClienteSelected;
	}

	public List<TOContato> getContatosClienteSelected() {
		return contatosClienteSelected;
	}

	public void setContatosClienteSelected(List<TOContato> contatosClienteSelected) {
		this.contatosClienteSelected = contatosClienteSelected;
	}

}
