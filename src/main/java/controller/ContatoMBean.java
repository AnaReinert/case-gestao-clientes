package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import controller.abstracts.CrudBaseMBean;
import model.entity.transferobject.TOContato;
import service.ContatoSBean;
import utils.EmailUtils;

@ViewScoped
@Named("contatoMBean")
public class ContatoMBean extends CrudBaseMBean implements Serializable {

	private static final long serialVersionUID = 7522489086457515519L;

	private EmailUtils emailUtils = EmailUtils.getInstance();
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
		if (validarEmail()) {
			getContatoSbean().salvarContato(contato);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro efetuado com sucesso!", null));
			contatos = getContatoSbean().listarContatos();
			onLimparCadastro();
		}
		this.contatos = getContatoSbean().listarContatos();
	}

	@Override
	public void onAtualizar() {
		getContatoSbean().atualizarContatos(contatoSelected);
		getContatoSbean().listarContatos();
	}

	@Override
	public void onExcluir() {
		getContatoSbean().remover(contato);
	}

	@Override
	public void onLimparCadastro() {
		this.contato = new TOContato();
	}

	private boolean validarEmail() {
		String emails = "";
		for (String email : contato.getEmails()) {
			if (!emailUtils.validaEmail(email)) {
				emails = emails + " " + email;
			}
		}
		if (!emails.isEmpty()) {
			showMessage(FacesMessage.SEVERITY_ERROR, "Os seguintes emails estão inválidos: " + emails);
			return false;
		}
		return true;
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