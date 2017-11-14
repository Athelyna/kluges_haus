package models;

public class SensorAbstract{

	public SensorAbstract(boolean temp, boolean windStrength, String time) {
		this.temp = temp;
		this.windStrength = windStrength;
		this.time = time;
	}

	public boolean isTemp() {
		return temp;
	}
	public void setTemp(boolean temp) {
		this.temp = temp;
	}
	public boolean isWindStrength() {
		return windStrength;
	}
	public void setWindStrength(boolean windStrength) {
		this.windStrength = windStrength;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	private boolean temp; //true for high false for low;
	private boolean windStrength; //true for strong wind, false for weak wind
	private String time; // 3 choices: morning noon night
}
