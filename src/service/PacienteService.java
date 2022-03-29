package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modelo.Paciente;

@Stateless
public class PacienteService extends GenericService<Paciente>{
	
	public PacienteService() {
		super(Paciente.class);
	}
	
	public List<Paciente> BuscarPorCPF(String cpf){
		
		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Paciente> criteriaQuery = criteriaBuilder.createQuery(Paciente.class);
		
		final Root<Paciente> paciente = criteriaQuery.from(Paciente.class);
		
		criteriaQuery.select(paciente);
		
		criteriaQuery.where(criteriaBuilder.equal(paciente.get("cpf"), cpf));
		List<Paciente> p = getEntityManager().createQuery(criteriaQuery).getResultList();
		
		return p;
	}
}
