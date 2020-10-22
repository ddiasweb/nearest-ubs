package ddiasweb.nearestubs.repo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ddiasweb.nearestubs.model.Ubs;
import ddiasweb.nearestubs.util.CsvLoader;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UbsRepositoryIntegrationTestsIT {

    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UbsRepository ubsRepository;

    @Before
    public void whenReload_thenSuccess() {
	    new CsvLoader(ubsRepository).load("database/ubs-sample.csv");
    }

    @Test
    public void whenFindNearestUbs_thenReturnUbsList() {
    	ubsRepository.deleteAll();
    	
		GeometryFactory geometryFactory = new GeometryFactory();
        entityManager.persist(new Ubs(1, "UBS 1 Name", "UBS 1 Address", "UBS 1 City", "UBS 1 Phone",
        		geometryFactory.createPoint(new Coordinate(10, 10)), 1, 1, 1, 1));
        entityManager.persist(new Ubs(2, "UBS 2 Name", "UBS 2 Address", "UBS 2 City", "UBS 2 Phone",
        		geometryFactory.createPoint(new Coordinate(20, 20)), 1, 1, 1, 1));
        entityManager.persist(new Ubs(3, "UBS 3 Name", "UBS 3 Address", "UBS 3 City", "UBS 3 Phone",
        		geometryFactory.createPoint(new Coordinate(30, 30)), 1, 1, 1, 1));
        entityManager.flush();
     
        List<Ubs> found = ubsRepository.findNearestUbs(geometryFactory.createPoint(new Coordinate(15, 15)), 15);
     
        assertThat(found.size()).isEqualTo(2);
    }
}
