package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Medico;
import service.MedicoService;

@ViewScoped
@ManagedBean
public class MedicoBean {
	
	@EJB
	private MedicoService medicoService;
	
	private Medico medico = new Medico();
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private Long medicoAtual = 0L;
	
	@PostConstruct
	public void init() {
		listaMedico();
	}
	
	private void listaMedico() {
		medicos = medicoService.OrdenaMedico();
	}
	
	public void gravarMedico() {
		String msg = "";
		
		if(medico.getIdPessoa() == null) {
			medicoService.create(medico);
			msg=" gravado";
		} else {
			medicoService.merge(medico);
			msg=" atualizado";
		}
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Medico(a) "+ medico.getNomeCompleto() + msg + " com sucesso!"));
		medico = new Medico();
		listaMedico();
	}
	
	public void carregarMedico(Medico m) {
		setMedico(m);
		medicoAtual = m.getIdPessoa();
	}
	
	public void cancelar() {
		medico = new Medico();
		listaMedico();
	}
	
	public void excluirMedico(Medico m ) {
		String msg = "";
		try{
			msg = m.getNomeCompleto()+", removido(a) com sucesso!";
			medicoService.remove(m);		
		}catch (Exception e) {
			msg = m.getNomeCompleto()+", Não pode ser removido(a)!";
		}
		
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Medico(a): "+ msg));
		listaMedico();
	}

	public MedicoService getMedicoService() {
		return medicoService;
	}

	public void setMedicoService(MedicoService medicoService) {
		this.medicoService = medicoService;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Long getMedicoAtual() {
		return medicoAtual;
	}

	public void setMedicoAtual(Long medicoAtual) {
		this.medicoAtual = medicoAtual;
	}
}
