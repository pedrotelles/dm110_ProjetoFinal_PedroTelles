package br.inatel.dm110.hello.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.dm110.hello.api.ProductTO;
import br.inatel.dm110.hello.dao.ProductDAO;
import br.inatel.dm110.hello.entities.Product;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/productQueue")
})
public class InventoryMDB implements MessageListener {

	@EJB
	private ProductDAO productDao;

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objMessage = (ObjectMessage) message;
				Object object = objMessage.getObject();
				if (object instanceof ProductTO) {
					ProductTO to = (ProductTO) object;
					saveProduct(to);
				} else {
					System.out.println("!!!!!!!!!! A MENSAGEM NÃO CONTÉM UM PRODUTO !!!!!!!!!!");
				}
			} else {
				System.out.println("!!!!!!!!!! A MENSAGEM NÃO É UM OBJETO !!!!!!!!!!");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void saveProduct(ProductTO to) {
		System.out.println("#### Salvando produto...");
		Product product = new Product();
		product.setName(to.getName());
		product.setQuantity(to.getQuantity());
		productDao.insert(product);
	}

}







