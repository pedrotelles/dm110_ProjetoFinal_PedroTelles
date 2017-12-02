package br.inatel.dm110.ippoller.entities;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name = "seq_poller", sequenceName = "seq_poller", allocationSize = 1)
public class PollerEquipment {

	@Id
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
	public PollerEquipment(String address, boolean status) {
		
		this.address = address;
		this.status = status;
	}
	public PollerEquipment() {
		
		
	}
	
}