package br.inatel.dm110.hello.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.dm110.hello.api.ProductTO;
import br.inatel.dm110.hello.dao.ProductDAO;
import br.inatel.dm110.hello.entities.Product;
import br.inatel.dm110.hello.interfaces.InventoryLocal;
import br.inatel.dm110.hello.interfaces.InventoryRemote;

@Stateless
@Remote(InventoryRemote.class)
@Local(InventoryLocal.class)
public class InventoryBean implements InventoryRemote, InventoryLocal {

	@EJB
	private ProductDAO productDAO;

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/productQueue")
	private Queue queue;

	@Override
	public List<String> listProductNames() {
		return productDAO.listAll().stream().map(Product::getName).collect(Collectors.toList());
	}

	@Override
	public void createNewProduct(ProductTO productTO) {
		try (
				Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession();
				MessageProducer producer = session.createProducer(queue);
		) {
			ObjectMessage objMessage = session.createObjectMessage(productTO);
			producer.send(objMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}







