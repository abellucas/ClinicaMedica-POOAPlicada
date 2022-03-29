package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import modelo.Medico;

@Stateless
public class MedicoService extends GenericService<Medico>{
	
	public MedicoService() {
		super(Medico.class);
	}
	
	
public List<Medico> OrdenaMedico(){
		
		final CriteriaBuilder criteria = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Medico> c = criteria.createQuery(Medico.class);
		
		final Root<Medico> medico = c.from(Medico.class);
		
		final Expression<String> primeironome = medico.get("primeiroNome");
		final Expression<String> sobrenome = medico.get("sobrenome");
		
		c.select(medico);
		
		c.orderBy(criteria.asc(primeironome), criteria.asc(sobrenome));
		
		
		TypedQuery<Medico> query = getEntityManager().createQuery(c);
		
		List<Medico> medicos = query.getResultList();
		
		return medicos;
	}
}
