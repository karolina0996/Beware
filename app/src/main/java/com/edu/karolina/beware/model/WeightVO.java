package com.edu.karolina.beware.model;

public class WeightVO {

	int iduser;
	long date;
	double value;
	
	public WeightVO(int iduser, long date, double value) {
		super();
		this.iduser = iduser;
		this.date = date;
		this.value = value;
	}
	
	public WeightVO(){
		super();
	}
	
	public WeightVO(long date, double value) {
		super();
		this.date = date;
		this.value = value;
	}
	
	
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}	
	
}
