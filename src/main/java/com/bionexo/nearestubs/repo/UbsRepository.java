package com.bionexo.nearestubs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bionexo.nearestubs.model.Ubs;
import com.vividsolutions.jts.geom.Geometry;

@Repository
public interface UbsRepository extends CrudRepository<Ubs, Integer>{

	@Query("SELECT ubs FROM Ubs ubs where distance(location, :location) <= :radius")
	public List<Ubs> findNearestUbs(
		@Param("location") Geometry location,
		@Param("radius") double radius
	);;
}
