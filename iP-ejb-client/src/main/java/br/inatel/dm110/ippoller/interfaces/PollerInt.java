package br.inatel.dm110.ippoller.interfaces;

import br.inatel.dm110.poller.api.PollerEquipmentTO;


public interface PollerInt {

	void startScan(String ip, String mask);

	String checkbyIP(String address);

}
