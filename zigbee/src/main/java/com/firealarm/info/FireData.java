package com.firealarm.info;

public class FireData {
	private int sensor_id;
	private int temperature;
	private int smoke;
	private Boolean warning;
	private String time;
	private String position;

	public int getId() {
		return sensor_id;
	}

	public void setId(int id) {
		this.sensor_id = id;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getSmoke() {
		return smoke;
	}

	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}

	public Boolean getWarning() {
		return warning;
	}

	public void setWraning(Boolean warning) {
		this.warning = warning;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public FireData() {

	}

	public FireData(int id, int temperature, int smoke) {
		this.sensor_id = id;
		this.temperature = temperature;
		this.smoke = smoke;
	}

}
