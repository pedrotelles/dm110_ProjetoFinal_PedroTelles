package br.inatel.dm110.ippoller.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.dm110.ippoller.dao.PollerEquipmentDAO;
import br.inatel.dm110.ippoller.entities.PollerEquipment;
import br.inatel.dm110.ippoller.interfaces.PollerIntLocal;
import br.inatel.dm110.ippoller.interfaces.PollerIntRemote;
import br.inatel.dm110.poller.api.PollerEquipmentListTO;
import br.inatel.dm110.poller.api.PollerEquipmentTO;
import br.inatel.dm110.poller.utils.IpUtil;


@Stateless
@Remote(PollerIntRemote.class)
@Local(PollerIntLocal.class)
public class PollerBean implements PollerIntRemote,PollerIntLocal{

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
			sender.sendTextMessage(gerado.substring(gerado.length() - 4,gerado.length() ));
			if(
					gerado.substring(gerado.length() - 2,gerado.length() )!=".0" &&
					gerado.substring(gerado.length() - 4,gerado.length() ) != ".255" 
			) {
				
				eqps.add(po);
			}
		}
		for (int i =0; i<eqps.size();i+=10) {
			List<PollerEquipmentTO> eqps2 = new  ArrayList<>();
			eqps2 = eqps.subList(i, Math.min(eqps.size(),i+10));
			PollerEquipmentListTO eqpsList = new PollerEquipmentListTO();
			eqpsList.setEqps(eqps2);
			sender.sendObjectMessage(eqpsList);
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
