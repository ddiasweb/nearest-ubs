package com.bionexo.nearestubs.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name = "ubs")
public class Ubs implements Serializable {

	private static final long serialVersionUID = 6623138930677887037L;

	@Id
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "phone")
	private String phone;

	@Column(name = "location")  
	private Geometry location;
	  
	@Column(name = "scores_size")
	private String scores_size;

	@Column(name = "scores_adaptation_for_seniors")
	private String scores_adaptation_for_seniors;

	@Column(name = "scores_medical_equipament")
	private String scores_medical_equipament;

	@Column(name = "scores_medicine")
	private String scores_medicine;

	protected Ubs() {}

	public Ubs(Integer id, String name, String address, String city, String phone,
			Geometry location,
			String scores_size, String scores_adaptation_for_seniors,
			String scores_medical_equipament, String scores_medicine) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.location = location;
		this.scores_size = scores_size;
		this.scores_adaptation_for_seniors = scores_adaptation_for_seniors;
		this.scores_medical_equipament = scores_medical_equipament;
		this.scores_medicine = scores_medicine;
	}

	@Override
	public String toString() {
		return String.format("Ubs[id=%d, name='%s']", id, name);
	}
	
	public Map<String, String> toJson() {
		Map<String, String> json = new HashMap<String, String>();
		json.put("id", id.toString());
		json.put("name", name);
		return json;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getCity() {
		return this.city;
	}

	public String getPhone() {
		return this.phone;
	}

	public Map<String, Double> getGeocode() {
		Map<String, Double> geocode = new HashMap<String, Double>();
		geocode.put("lat", location.getCoordinate().x);
		geocode.put("long", location.getCoordinate().y);
		return geocode;
	}

	public Map<String, String> getScores() {
		Map<String, String> scores = new HashMap<String, String>();
		scores.put("scores_size", scores_size);
		scores.put("scores_adaptation_for_seniors", scores_adaptation_for_seniors);
		scores.put("scores_medical_equipament", scores_medical_equipament);
		scores.put("scores_medicine", scores_medicine);
		return scores;
	}
}
