package com.estufa.utfpr.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class NivesLeituras implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Double ph;
	private Double temperatura;
	private Double nitrogenio;
	private Double fosforo;
	private Double potassio;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data = new Date();	
	
	public NivesLeituras() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPh() {
		return ph;
	}

	public void setPh(Double ph) {
		this.ph = ph;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getNitrogenio() {
		return nitrogenio;
	}

	public void setNitrogenio(Double nitrogenio) {
		this.nitrogenio = nitrogenio;
	}

	public Double getFosforo() {
		return fosforo;
	}

	public void setFosforo(Double fosforo) {
		this.fosforo = fosforo;
	}

	public Double getPotassio() {
		return potassio;
	}

	public void setPotassio(Double potassio) {
		this.potassio = potassio;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	

	
}
