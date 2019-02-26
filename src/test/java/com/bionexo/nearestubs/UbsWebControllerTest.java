package com.bionexo.nearestubs;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bionexo.nearestubs.controller.WebController;
import com.bionexo.nearestubs.controller.util.Version;
import com.bionexo.nearestubs.model.Ubs;
import com.bionexo.nearestubs.repo.UbsRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

@RunWith(SpringRunner.class)
@WebMvcTest(WebController.class)
public class UbsWebControllerTest {
    @Autowired
    private MockMvc mvc;
 
    @MockBean
	UbsRepository repository;

    @MockBean
	Version version;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
      throws Exception {
  
		GeometryFactory geometryFactory = new GeometryFactory();

		List<Ubs> ubsList = new ArrayList<Ubs>();

		ubsList.add(new Ubs(1, "UBS 1 Name", "UBS 1 Address", "UBS 1 City", "UBS 1 Phone",
        		geometryFactory.createPoint(new Coordinate(1000, 1000)), "", "", "", ""));
		ubsList.add(new Ubs(2, "UBS 2 Name", "UBS 2 Address", "UBS 2 City", "UBS 2 Phone",
        		geometryFactory.createPoint(new Coordinate(2000, 2000)), "", "", "", ""));
    	
        given(repository.findNearestUbs(geometryFactory.createPoint(new Coordinate(1500, 1500)))).willReturn(ubsList);
     
        mvc.perform(get("/nearest?geocode_lat=1500&geocode_long=1500")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(2)));
    }

}
