package ddiasweb.nearestubs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ubs")
public class Ubs implements Serializable {

	private static final long serialVersionUID = 6623138930677887037L;

	@Id
	private int id;

	@Column(name = "name")
    @ApiModelProperty(notes = "The name of the UBS")
	private String name;

	@Column(name = "address")
    @ApiModelProperty(notes = "The address of the UBS")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "phone")
	private String phone;

	@Column(name = "location")  
    @ApiModelProperty(notes = "The location cordinates of the UBS")
	private Geometry geometryLocation;
	  
	@Column(name = "scores_size")
	private int scores_size;

	@Column(name = "scores_adaptation_for_seniors")
	private int scores_adaptation_for_seniors;

	@Column(name = "scores_medical_equipament")
	private int scores_medical_equipament;

	@Column(name = "scores_medicine")
	private int scores_medicine;

	protected Ubs() {}

	public Ubs(int id, String name, String address, String city, String phone,
			Geometry geometryLocation,
			int scores_size, int scores_adaptation_for_seniors,
			int scores_medical_equipament, int scores_medicine) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.geometryLocation = geometryLocation;
		this.scores_size = scores_size;
		this.scores_adaptation_for_seniors = scores_adaptation_for_seniors;
		this.scores_medical_equipament = scores_medical_equipament;
		this.scores_medicine = scores_medicine;
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

	public Location getGeocode() {
		Location location = new Location();
		location.setX(geometryLocation.getCoordinate().x);
		location.setY(geometryLocation.getCoordinate().y);
		return location;
	}

	public Scores getScores() {
		Scores scores = new Scores();
		scores.setScores_size(scores_size);
		scores.setScores_adaptation_for_seniors(scores_adaptation_for_seniors);
		scores.setScores_medical_equipament(scores_medical_equipament);
		scores.setScores_medicine(scores_medicine);
		return scores;
	}
}
