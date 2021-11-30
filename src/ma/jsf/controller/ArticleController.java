package ma.jsf.controller;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import lombok.Getter;
import lombok.Setter;
import ma.test.domaine.ArticleVo;
import ma.test.service.IService;


@ManagedBean(name = "articleBean", eager=false)
@RequestScoped
@Getter
@Setter
public class ArticleController {
	
	@EJB
	private IService service;
	private List<ArticleVo> ar;
	private ArticleVo a = new ArticleVo();
	
	@ManagedProperty("#{msg}")
	protected ResourceBundle bundle;
	
	public ResourceBundle getBundle() {
		return bundle;
	}
	
	public void setBundle(ResourceBundle bundle) {
		this.bundle=bundle;
	}
	
	public List<ArticleVo> getArticles(){
		if(ar==null)
			ar=service.getAll();
		return ar;
	}
	
	public String addArticle() {
		service.save(a);
		return null;
	}
	
	public String removeArticle(Long id) {
		service.delete(id);
		return null;
	}
	
	public void onRowEdit(RowEditEvent e) {
		try {
			a=e.getObject();
			addMessage("article.modifier.avec.succes", Constants.INFO_MSG);
			service.save(a);
			ar=service.getAll();
		} catch (Exception e2) {
			addMessage("probleme.tchnique",Constants.ERROR_MSG);
			e2.printStackTrace();
		}
	}
	
	public void onRowCancel(RowEditEvent e) {
		addMessage("modifications.annullees", Constants.INFO_MSG);
	}
	
	private void addMessage(String msg, String severty) {
		FacesMessage m=null;
		m=new FacesMessage(bundle.getString(msg));
		if(severty.equals(Constants.ERROR_MSG))
			m.setSeverity(FacesMessage.SEVERITY_ERROR);
		if(severty.equals(Constants.INFO_MSG))
			m.setSeverity(FacesMessage.SEVERITY_INFO);
		
		FacesContext.getCurrentInstance().addMessage(null,m);
	}
	
	
}
