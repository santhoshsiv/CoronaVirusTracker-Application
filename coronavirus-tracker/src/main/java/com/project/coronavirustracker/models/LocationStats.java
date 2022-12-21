package com.project.coronavirustracker.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Component
@Entity
public class LocationStats {
	
	@Id
	private int id;
	private String state;
	private String country;
	private int LatestTotalCases;
	private int DiffFromPrevDay;
	 

	public LocationStats() {
		
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiffFromPrevDay() {
		return DiffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.DiffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) { 
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return LatestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.LatestTotalCases = latestTotalCases;
	}
	
	@Override
	public String toString() {
		return "LocationStats [id=" + id + ", state=" + state + ", country=" + country + ", LatestTotalCases="
				+ LatestTotalCases + "]";
	}

}
