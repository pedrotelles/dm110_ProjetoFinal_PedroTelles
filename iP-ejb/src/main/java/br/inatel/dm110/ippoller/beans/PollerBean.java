package br.inatel.dm110.ippoller.beans;

import java.util.ArrayList;
import java.util.List;

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

import br.inatel.dm110.hello.interfaces.PollerIntLocal;
import br.inatel.dm110.hello.interfaces.PollerIntRemote;
import br.inatel.dm110.ippoller.dao.PollerEquipmentDAO;
import br.inatel.dm110.ippoller.entities.PollerEquipment;
import br.inatel.dm110.poller.api.PollerEquipmentListTO;
import br.inatel.dm110.poller.api.PollerEquipmentTO;
import br.inatel.dm110.poller.utils.IpUtil;


@Stateless
@Local(PollerIntLocal.class)
@Remote(PollerIntRemote.class)
public class PollerBean implements PollerIntLocal,PollerIntRemote{

	@EJB
	private PollerEquipmentDAO dao;
	
	@EJB
	private ObjectMessageSender sender;

	@Override
	public void startScan(String ip, String mask) {
		// TODO Auto-generated method stub

		
		List<PollerEquipmentTO> eqps = new ArrayList<>();
		String[] ips  = IpUtil.generateIps(ip, Integer.parseInt(mask));
		for (int i = 0; i < ips.length; i++) {
			String gerado = ips[i];
			PollerEquipmentTO po = new PollerEquipmentTO();
			po.setStatus(false);
			po.setAddress(gerado);
			
			if(
					gerado.substring(gerado.length() - 2,gerado.length() - 1)==".0" ||
					gerado.substring(gerado.length() - 4,gerado.length() - 1) == ".255" 
			) {
				eqps.add(po);
			}
		}
		for (int i =0; i<eqps.size();i+=10) {
			List<PollerEquipmentTO> eqps2 = new  ArrayList<>();
			eqps2 = eqps.subList(i, Math.min(eqps.size(),i+10));
			PollerEquipmentListTO eqpsList = new PollerEquipmentListTO();
			eqpsList.setEqps(eqps2);
			sender.sendTextMessage(eqpsList);
		}
		
	}




	@Override
	public PollerEquipmentTO checkbyIP(String address) {
		// TODO Auto-generated method stub
		PollerEquipment po =  dao.checkbyip(address);
		if(po == null) {
			return null;
		}else {
			PollerEquipmentTO pto = new PollerEquipmentTO();
			pto.setAddress(po.getAddress());
			
			pto.setStatus(po.isStatus());
			return pto;
		}
		
	}

	

}
