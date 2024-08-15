package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import controller.abstracts.CrudBaseMBean;
import model.entity.transferobject.TOContato;
import service.ContatoSBean;

@ViewScoped
@Named("contatoMBean")
public class ContatoMBean extends CrudBaseMBean implements Serializable {

	private static final long serialVersionUID = 7522489086457515519L;

	private ContatoSBean contatoSBean;
	private List<TOContato> contatos;
	private TOContato contato;
	private TOContato contatoSelected;

	public ContatoMBean() {
		this.contato = new TOContato();
		this.contatos = getContatoSbean().listarContatos();
	}

	@Override
	public void onCadastrar() {
		if (validarEmail(contato.getEmails())) {
			getContatoSbean().salvarContato(contato);
			showMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado com sucesso!");
			onLimparCadastro();
		}
	}

	@Override
	public void onAtualizar() {
		if (validarEmail(contatoSelected.getEmails())) {
			getContatoSbean().atualizarContatos(contatoSelected);
			getContatoSbean().listarContatos();
			showMessage(FacesMessage.SEVERITY_INFO, "Contato atualizado com sucesso!");
			PrimeFaces.current().executeScript("PF('cadastroContatosDialog').hide()");
		}
	}

	@Override
	public void onExcluir() {
		if(!getContatoSbean().verificaContatoVinculado(contato)) {
			getContatoSbean().remover(contato);
			showMessage(FacesMessage.SEVERITY_INFO, "Contato removido com sucesso!");
		}else {
			showMessage(FacesMessage.SEVERITY_ERROR, "Contato não pôde ser removido por estar vinculado à 1 ou mais clientes.");
		}
		this.contatos = getContatoSbean().listarContatos();
	}

	@Override
	public void onLimparCadastro() {
		this.contato = new TOContato();
		this.contatos = getContatoSbean().listarContatos();
	}

	public void onRowSelect(SelectEvent<?> event) {
		contatoSelected = (TOContato) event.getObject();
	}

	public ContatoSBean getContatoSbean() {
		if (contatoSBean == null)
			contatoSBean = new ContatoSBean();
		return contatoSBean;
	}

	public List<TOContato> getContatos() {
		return contatos;
	}

	public void setContatos(List<TOContato> contatos) {
		this.contatos = contatos;
	}

	public TOContato getContato() {
		return contato;
	}

	public void setContato(TOContato contato) {
		this.contato = contato;
	}

	public TOContato getContatoSelected() {
		return contatoSelected;
	}

	public void setContatoSelected(TOContato contatoSelected) {
		this.contatoSelected = contatoSelected;
	}

}