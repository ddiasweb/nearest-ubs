package ddiasweb.nearestubs.controller;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ddiasweb.nearestubs.model.Ubs;
import ddiasweb.nearestubs.repo.UbsRepository;
import ddiasweb.nearestubs.util.Converter;
import ddiasweb.nearestubs.util.Version;
import io.swagger.annotations.ApiOperation;


@RestController
public class WebController {
	@Autowired
	UbsRepository repository;

	@Autowired
	Version version;

	@GetMapping(path = "/version", produces=MediaType.TEXT_PLAIN_VALUE)
	public String getVersion(){
		return version.getVersion();
	}
			
	@GetMapping(path = "/nearest", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get nearest UBS from location point by radius in metres")
	public ResponseEntity<List<Ubs>> fetchNearestUbs(
			@RequestParam("location") List<Double> locationParam,
			@RequestParam("radius") double radiusMeters){
		GeometryFactory geometryFactory = new GeometryFactory();
		Geometry location = geometryFactory.createPoint(new Coordinate(locationParam.get(1), locationParam.get(0)));
		List<Ubs> ubsList = new ArrayList<Ubs>();
		for (Ubs ubs: repository.findNearestUbs(location, Converter.metresToDegrees(radiusMeters))) {
			ubsList.add(ubs); 
		}
		return new ResponseEntity<List<Ubs>>(ubsList, HttpStatus.OK);
	}
}

