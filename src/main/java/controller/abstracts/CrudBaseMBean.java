package controller.abstracts;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public abstract class CrudBaseMBean {

	public abstract void onCadastrar();

	public abstract void onAtualizar();

	public abstract void onExcluir();
	
	public abstract void onLimparCadastro();

	public void showMessage(Severity severity, String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, message, null));
	}
}
