package br.inatel.dm110.poller.api;

import java.io.Serializable;

public class PollerEquipmentTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5147679775693198016L;

	private String address;
	private boolean status;
	

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
