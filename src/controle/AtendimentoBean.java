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
	
	private Atendimento atendimento = new Atendimento();
	
	
	private String cpf;
	
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	private List<Medico> medicos = new ArrayList<Medico>();
	
	private Long pacienteAtual = 0L;
	private Long medicoAtual = 0L;
	private Long atendimentoAtual = 0L;
	
	@PostConstruct
	public void init() {
		listaPaciente();
		listaMedico();
		listaAtendimento();
	}
	
	private void listaPaciente() {
		pacientes = pacienteService.listAll();
	}
	
	private void listaMedico() {
		medicos = medicoService.listAll();
	}
	
	private void listaAtendimento() {
		atendimentos = atendimentoService.listAll();
	}
	
	public void gravarAtendimento() {
		String msg = "";
		Paciente p = pacienteService.obtemPorId(pacienteAtual);
		
		if(atendimento.getIdAtendimento() == null) {
			
			atendimento.setPaciente(p);
			atendimento.setMedicos(medicos);
			atendimentoService.create(atendimento);
			msg=" gravado";
			
		} else {
			/*atendimentoService.merge(atendimento);
			msg=" atualizado";*/
		}
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Atendimento do Paciente "+ p.getNomeCompleto() + msg + " com sucesso!"));
		atendimento = new Atendimento();
		pacienteAtual = 0L;
		listaPaciente();
		listaMedico();
		listaAtendimento();
	}
	
	/*public void buscarPorCPF() {
		System.out.println(cpf);
		atendimento = atendimentoService.BuscarPorCPF(cpf);
	}*/
		
	public void carregarAtendimento(Atendimento a) {
		setAtendimento(a);
		atendimentoAtual = a.getIdAtendimento();
	}
	
	public void excluirAtendimento(Paciente p, Medico m, Atendimento a ) {
		String msg = "";
		try{
			msg = p.getNomeCompleto()+", removido(a) com sucesso!";
			atendimento.setPaciente(p);
			atendimento.setMedicos(medicos);
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

	public Long getMedicoAtual() {
		return medicoAtual;
	}

	public void setMedicoAtual(Long medicoAtual) {
		this.medicoAtual = medicoAtual;
	}
}
