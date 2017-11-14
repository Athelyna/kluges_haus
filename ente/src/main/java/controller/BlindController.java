package controller;

import interfaces.ElectricalDeviceController;
import models.Blind;

public class BlindController implements ElectricalDeviceController{
	private Blind blindSensor;
	private String action;
	
	public BlindController(){
		
	}

	public BlindController(Blind blindSensor) {
		this.blindSensor = blindSensor;
	}

	public String getAction() {
		if(isTempHigh()) {// temp high
			if(isWindStrong()) {//wind high
				action = "close";
			}else {
				if(isNotNight()) {
					action = "open";
				}else {
					action = "close";
				}
			}
		}else { //temp low
			if(isWindStrong()) { //wind high
				action = "close";
			}else {
				if(isNotNight()) {
					action = "open";
				}else {
					action = "close";
				}
			}

		}
		
		return action;
	}
	
	public void setBlindSensor(Blind blindSensor) {
		this.blindSensor = blindSensor;
	}

	private boolean isNotNight() {
		return blindSensor.getTime().equals("morning") || blindSensor.getTime().equals("noon");
	}

	private boolean isWindStrong() {
		return blindSensor.isWindStrength();
	}

	private boolean isTempHigh() {
		return blindSensor.isTemp();
	}
}
