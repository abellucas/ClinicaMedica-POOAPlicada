package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Atendimento;
import modelo.Medico;
import modelo.Paciente;
import service.AtendimentoService;
import service.MedicoService;
import service.PacienteService;

@ViewScoped
@ManagedBean
public class AtendimentoBean {

	@EJB
	private PacienteService pacienteService;
	
	@EJB
	private AtendimentoService atendimentoService;

	@EJB
	private MedicoService medicoService;
	
	private Paciente paciente = new Paciente();
	private Atendimento atendimento = new Atendimento();
	private Medico medico = new Medico();
	
	private String cpf = null;
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private Long pacienteAtual = 0L;
	private Long atendimentoAtual = 0L;
	
	@PostConstruct
	public void init() {
		listaMedico();
		listaPaciente();
		listaAtendimento();
	}
	
	private void listaMedico() {
		medicos = medicoService.listAll();
	}
	
	private void listaPaciente() {
		pacientes = pacienteService.listAll();	
	}
	
	private void listaAtendimento() {
		atendimentos = atendimentoService.listAll();
	}
	
	public void gravarAtendimento() {
		String msg = "";
		
		if(atendimento.getIdAtendimento() == null) {
			
			pacienteService.create(paciente);
			medicoService.create(medico);
			atendimentoService.create(atendimento);
			
			msg=" gravado";
		} else {
			/*atendimentoService.merge(atendimento);
			msg=" atualizado";*/
		}
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Atendimento do Paciente "+ paciente.getNomeCompleto() + msg + " com sucesso!"));
		
		medico = new Medico();
		paciente = new Paciente();
		atendimento = new Atendimento();
		
		listaMedico();
		listaPaciente();
		listaAtendimento();
	}
	
	public void buscarPorCPF() {
		System.out.println(cpf);
		paciente = atendimentoService.BuscarPorCPF(cpf);
		/*if(paciente.getCpf() == null) {
			listaPaciente();
		} else {
			
		}*/
		
	}
		
	public void carregarAtendimento(Atendimento a) {
		setAtendimento(a);
		atendimentoAtual = a.getIdAtendimento();
	}
	
	public void cancelar() {
		paciente = new Paciente();
		listaPaciente();
	}
	
	public void excluirAtendimento(Paciente p, Medico m, Atendimento a ) {
		String msg = "";
		try{
			msg = p.getNomeCompleto()+", removido(a) com sucesso!";
			pacienteService.remove(p);
			medicoService.remove(m);
			atendimentoService.remove(a);
			
		}catch (Exception e) {
			msg = p.getNomeCompleto()+", Não pode ser removido(a)!";
		}
		
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Paciente: "+ msg));
		listaPaciente();
		listaMedico();
		listaAtendimento();
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
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public AtendimentoService getAtendimentoService() {
		return atendimentoService;
	}

	public void setAtendimentoService(AtendimentoService atendimentoService) {
		this.atendimentoService = atendimentoService;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
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

	public Long getAtendimentoAtual() {
		return atendimentoAtual;
	}

	public void setAtendimentoAtual(Long atendimentoAtual) {
		this.atendimentoAtual = atendimentoAtual;
	}
	
	
}
