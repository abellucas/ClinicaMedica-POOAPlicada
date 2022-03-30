package service;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modelo.Atendimento;
import modelo.Paciente;

@Stateless
public class AtendimentoService extends GenericService<Atendimento>{
	
	public AtendimentoService() {
		super(Atendimento.class);
	}
	
	public Paciente BuscarPorCPF(String cpf){
			
			final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
			final CriteriaQuery<Paciente> criteriaQuery = criteriaBuilder.createQuery(Paciente.class);
			
			final Root<Paciente> paciente = criteriaQuery.from(Paciente.class);
			
			criteriaQuery.select(paciente);
			
			criteriaQuery.where(criteriaBuilder.equal(paciente.get("cpf"), cpf));
			Paciente p = getEntityManager().createQuery(criteriaQuery).getSingleResult();
			
			return p;
		}
}
