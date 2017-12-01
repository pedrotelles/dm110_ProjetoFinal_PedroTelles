package br.inatel.dm110.hello.interfaces;

import br.inatel.dm110.poller.api.PollerEquipmentTO;


public interface PollerInt {

	void startScan(String ip, String mask);

	PollerEquipmentTO checkbyIP(String address);

}
