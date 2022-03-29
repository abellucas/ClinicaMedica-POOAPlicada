package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Paciente;
import service.PacienteService;

@ViewScoped
@ManagedBean
public class PacienteBean {
	
	@EJB
	private PacienteService pacienteService;
	
	private Paciente paciente = new Paciente();
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	
	private Long pacienteAtual = 0L;
	
	@PostConstruct
	public void init() {
		listaPaciente();
	}
	
	private void listaPaciente() {
		pacientes = pacienteService.listAll();
	}
	
	public void gravarPaciente() {
		String msg = "";
		
		if(paciente.getIdPessoa() == null) {
			pacienteService.create(paciente);
			msg=" gravado";
		} else {
			pacienteService.merge(paciente);
			msg=" atualizado";
		}
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Paciente "+ paciente.getNomeCompleto() + msg + " com sucesso!"));
		paciente = new Paciente();
		listaPaciente();
	}
	
	public void carregarPaciente(Paciente p) {
		setPaciente(p);
		pacienteAtual = p.getIdPessoa();
	}
	
	public void cancelar() {
		paciente = new Paciente();
		listaPaciente();
	}
	
	public void excluirMedico(Paciente p ) {
		String msg = "";
		try{
			msg = p.getNomeCompleto()+", removido(a) com sucesso!";
			pacienteService.remove(p);		
		}catch (Exception e) {
			msg = p.getNomeCompleto()+", Não pode ser removido(a)!";
		}
		
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Paciente: "+ msg));
		listaPaciente();
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Long getPacienteAtual() {
		return pacienteAtual;
	}

	public void setPacienteAtual(Long pacienteAtual) {
		this.pacienteAtual = pacienteAtual;
	}
}
