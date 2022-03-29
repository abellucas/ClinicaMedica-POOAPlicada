package modelo;

import javax.persistence.Entity;

@Entity
public class Paciente extends Pessoa{
	
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
