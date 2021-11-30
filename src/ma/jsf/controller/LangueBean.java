package ma.jsf.controller;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="langue")
@SessionScoped
public class LangueBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Locale locale= new Locale("en");
	
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getLanguage() {
		return locale.getLanguage();
	}
	
	public void changeLanguage(String language) {
		locale=new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
	
	
}
