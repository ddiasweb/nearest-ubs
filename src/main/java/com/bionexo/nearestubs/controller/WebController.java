package com.bionexo.nearestubs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bionexo.nearestubs.controller.util.CustomErrorType;
import com.bionexo.nearestubs.model.Ubs;
import com.bionexo.nearestubs.repo.UbsRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;


@RestController
public class WebController {
	@Autowired
	UbsRepository repository;
	
	@GetMapping(path = "/reload", produces=MediaType.APPLICATION_JSON_VALUE)
	public String process(){
		GeometryFactory geometryFactory = new GeometryFactory();
		repository.deleteAll();
		repository.save(new Ubs(1, "UBS 1", "Rua teste 1", "Cidade Teste 1", "",
				geometryFactory.createPoint(new Coordinate(1000, 1000)), "1", "2", "3", "4"));
		repository.save(new Ubs(2, "UBS 2", "Rua teste 2", "Cidade Teste 2", "",
				geometryFactory.createPoint(new Coordinate(2000, 2000)), "4", "3", "2", "1"));
		return "Done";
	}
		
	@GetMapping(path = "/find/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable("id") Integer id){
		Optional<Ubs> result = repository.findById(id);
		if (result.isPresent()) {
			Ubs ubs = result.get();
			return new ResponseEntity<Ubs>(ubs, HttpStatus.OK);
		}
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType("User with id " + id + " not found"),
			HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/nearest", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ubs>> fetchNearestUbs(
			@RequestParam("geocode_lat") double geocode_lat,
			@RequestParam("geocode_long") double geocode_long){
		GeometryFactory geometryFactory = new GeometryFactory();
		Geometry geometry = geometryFactory.createPoint(new Coordinate(geocode_lat, geocode_long));
		List<Ubs> ubsList = new ArrayList<Ubs>();
		for(Ubs ubs: repository.findNearestUbs(geometry)){
			ubsList.add(ubs); 
		}
		return new ResponseEntity<List<Ubs>>(ubsList, HttpStatus.OK);
	}
}

