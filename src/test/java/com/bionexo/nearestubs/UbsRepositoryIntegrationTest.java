package com.bionexo.nearestubs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.bionexo.nearestubs.model.Ubs;
import com.bionexo.nearestubs.repo.UbsRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UbsRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UbsRepository ubsRepository;
 
    @Test
    public void whenFindByName_thenReturnEmployee() {
    	ubsRepository.deleteAll();
    	
		GeometryFactory geometryFactory = new GeometryFactory();
        entityManager.persist(new Ubs(1, "UBS 1 Name", "UBS 1 Address", "UBS 1 City", "UBS 1 Phone",
        		geometryFactory.createPoint(new Coordinate(1000, 1000)), "", "", "", ""));
        entityManager.persist(new Ubs(2, "UBS 2 Name", "UBS 2 Address", "UBS 2 City", "UBS 2 Phone",
        		geometryFactory.createPoint(new Coordinate(2000, 2000)), "", "", "", ""));
        entityManager.persist(new Ubs(3, "UBS 3 Name", "UBS 3 Address", "UBS 3 City", "UBS 3 Phone",
        		geometryFactory.createPoint(new Coordinate(3000, 3000)), "", "", "", ""));
        entityManager.flush();
     
        List<Ubs> found = ubsRepository.findNearestUbs(geometryFactory.createPoint(new Coordinate(1500, 1500)));
     
        assertThat(found.size()).isEqualTo(2);
    }
}
