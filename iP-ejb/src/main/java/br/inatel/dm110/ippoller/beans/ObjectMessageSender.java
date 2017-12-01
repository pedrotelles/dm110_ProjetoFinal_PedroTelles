package br.inatel.dm110.ippoller.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import br.inatel.dm110.poller.api.PollerEquipmentListTO;

@Stateless
public class ObjectMessageSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/dm110queue")
	private Queue queue;

	public void sendTextMessage(PollerEquipmentListTO list) {
		try (
				Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(queue);
		) {
			ObjectMessage textMessage = session.createObjectMessage(list);
			producer.send(textMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}








