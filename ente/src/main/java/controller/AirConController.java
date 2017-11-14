package controller;

import interfaces.ElectricalDeviceController;
import models.AirCon;

public class AirConController implements ElectricalDeviceController{
	private AirCon ac;
	private String acAction;
	
	public AirConController(AirCon ac) {
		this.ac = ac;
	}

	public String getAction() {
		if(isTempHigh()) {// temp high
			if(isWindStrong()) {//wind high
				if(isNotNight()) {
					acAction = "off";
				}else {
					acAction = "on";
				}
			}else {
				acAction = "on";
			}
		}else { //when temp low ac always off
			acAction = "off";

		}
		
		return acAction;
	}
	
	public void setAc(AirCon ac) {
		this.ac = ac;
	}

	private boolean isNotNight() {
		return ac.getTime().equals("morning") || ac.getTime().equals("noon");
	}

	private boolean isWindStrong() {
		return ac.isWindStrength();
	}

	private boolean isTempHigh() {
		return ac.isTemp();
	}
	
}
