package br.inatel.dm110.hello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.dm110.hello.entities.Product;

@Stateless
public class ProductDAO {

	@PersistenceContext(unitName = "inventory")
	private EntityManager em;

	public List<Product> listAll() {
		return em.createQuery("from Product p", Product.class).getResultList();
	}

	public void insert(Product product) {
		em.persist(product);
	}

}
