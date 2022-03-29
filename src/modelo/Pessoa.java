package modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa {
	
	@Id @GeneratedValue
	private Long idPessoa;
	
	private String primeiroNome;
	private String sobrenome;
	private String telefone;
	private String celular;
	private Date nascimento;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Endereco endereco = new Endereco();
	
	public Pessoa() {
		
	}
	
	//metodo
	public String getNomeCompleto() 
	{
		return primeiroNome + " "+ sobrenome;
	}
	
	public String getPrimeiroNome() 
	{
		return primeiroNome;
	}
	public void setPrimeiroNome(String primeiroNome) 
	{
		this.primeiroNome = primeiroNome;
	}
	public String getSobrenome() 
	{
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) 
	{
		this.sobrenome = sobrenome;
	}
	public String getTelefone() 
	{
		return telefone;
	}
	public void setTelefone(String telefone) 
	{
		this.telefone = telefone;
	}
	public Date getNascimento() 
	{
		return nascimento;
	}
	public void setNascimento(Date nascimento) 
	{
		this.nascimento = nascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	
}
