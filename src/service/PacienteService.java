package service;

import javax.ejb.Stateless;

import modelo.Paciente;

@Stateless
public class PacienteService extends GenericService<Paciente>{
	
	public PacienteService() {
		super(Paciente.class);
	}
}
