package br.inatel.dm110.poller.api;

import java.io.Serializable;
import java.util.List;

public class PollerEquipmentListTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1361395643946286264L;
	
	private List<PollerEquipmentTO> eqps;

	public List<PollerEquipmentTO> getEqps() {
		return eqps;
	}

	public void setEqps(List<PollerEquipmentTO> eqps) {
		this.eqps = eqps;
	}
	

}
