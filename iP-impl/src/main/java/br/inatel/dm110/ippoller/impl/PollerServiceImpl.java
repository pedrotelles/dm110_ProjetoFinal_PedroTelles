package br.inatel.dm110.ippoller.impl;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.ippoller.interfaces.PollerIntRemote;
import br.inatel.dm110.poller.api.PollerEquipmentTO;
import br.inatel.dm110.poller.api.PollerService;


@RequestScoped
public class PollerServiceImpl implements PollerService{

	@EJB(lookup = "java:app/iP-ejb-0.1-SNAPSHOT/PollerBean!br.inatel.dm110.ippoller.interfaces.PollerIntRemote")
	private PollerIntRemote remote;
	
	

	@Override
	public void scan(String ip, String mask) {
		// TODO Auto-generated method stub
			remote.startScan(ip, mask);
			
		}
	

	@Override
	public PollerEquipmentTO checkstatus(String ip) {
		// TODO Auto-generated method stub
		return remote.checkbyIP(ip);
		 
	}

	

}
