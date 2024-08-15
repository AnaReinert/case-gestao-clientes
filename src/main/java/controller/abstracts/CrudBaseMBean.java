package controller.abstracts;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import utils.EmailUtils;

public abstract class CrudBaseMBean {

	public abstract void onCadastrar();

	public abstract void onAtualizar();

	public abstract void onExcluir();
	
	public abstract void onLimparCadastro();
	private EmailUtils emailUtils = EmailUtils.getInstance();


	public void showMessage(Severity severity, String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(severity, message, null));
	}
	
	public boolean validarEmail(List<String> emailsCad) {
		String emails = "";
		for (String email : emailsCad) {
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
}
