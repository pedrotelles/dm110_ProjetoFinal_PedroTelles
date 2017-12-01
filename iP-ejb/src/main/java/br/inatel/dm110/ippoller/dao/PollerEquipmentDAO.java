package br.inatel.dm110.ippoller.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.inatel.dm110.ippoller.entities.PollerEquipment;

public class PollerEquipmentDAO {
	@PersistenceContext(unitName = "pollerequipment")
	private EntityManager em;
	
	public List<PollerEquipment> consultar() {

		List<PollerEquipment> eqps  = null;
		eqps = em.createQuery("from PollerEquipment e", PollerEquipment.class).getResultList();
		return eqps;
	}
	public void insert(PollerEquipment eqp) {
		em.persist(eqp);
	}
	public PollerEquipment checkbyip(String address) {
		PollerEquipment poller = null;
		TypedQuery<PollerEquipment> tq= em.createQuery("from PollerEquipment e where address=?", PollerEquipment.class);
		poller = tq.setParameter(0, address).getSingleResult();
		return poller;
	}
	public PollerEquipment edit(PollerEquipment eqp) {
		
		em.merge(eqp);
		return eqp;
		
	}
}
