package ddiasweb.nearestubs.repo;

import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ddiasweb.nearestubs.model.Ubs;

@Repository
public interface UbsRepository extends CrudRepository<Ubs, Integer>{

	@Query("SELECT ubs FROM Ubs ubs where distance(location, :location) <= :radius")
	public List<Ubs> findNearestUbs(
		@Param("location") Geometry location,
		@Param("radius") double radius
	);;
}
