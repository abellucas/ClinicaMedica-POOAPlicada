package modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Endereco {
	
	@Id @GeneratedValue
	private Long idEndereco;
	
	private String rua;
	private String bairro;
	private Integer numero;
	private String cep;
	private String cidade;
	
	public Endereco() 
	{
		
	}

	public String getRua() 
	{
		return rua;
	}

	public void setRua(String rua) 
	{
		this.rua = rua;
	}

	public String getBairro() 
	{
		return bairro;
	}

	public void setBairro(String bairro) 
	{
		this.bairro = bairro;
	}

	public Integer getNumero() 
	{
		return numero;
	}

	public void setNumero(Integer numero) 
	{
		this.numero = numero;
	}

	public String getCep() 
	{
		return cep;
	}

	public void setCep(String cep)
	{
		this.cep = cep;
	}

	public String getCidade() 
	{
		return cidade;
	}

	public void setCidade(String cidade) 
	{
		this.cidade = cidade;
	}
	
	
}
