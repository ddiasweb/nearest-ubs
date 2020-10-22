package ddiasweb.nearestubs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import ddiasweb.nearestubs.model.Ubs;
import ddiasweb.nearestubs.repo.UbsRepository;

public class CsvLoader {

	UbsRepository repository;

	public CsvLoader(UbsRepository repository) {
		this.repository = repository;
	}


	public void load(String csvFile) {

        String line = "";
        String cvsSplitBy = ",";

        List<String> scores = new ArrayList<String>();
        scores.add("Desempenho mediano ou  um pouco abaixo da média");
        scores.add("Desempenho acima da média");
        scores.add("Desempenho muito acima da média");
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

        	GeometryFactory geometryFactory = new GeometryFactory();
        	
        	boolean first = true;
            while ((line = br.readLine()) != null) {
            	if (first) {
            		first = false;
            		continue;
            	}
        		try {
                    String[] colunm = line.split(cvsSplitBy);

                    int id = Integer.parseInt(colunm[3]);
            		String name = colunm[4];
            		String address = colunm[5];
            		String city = colunm[7];
            		String phone = colunm[8];
            		int scores_size = scores.indexOf(colunm[9]) + 1;
            		int scores_adaptation_for_seniors = scores.indexOf(colunm[10]) + 1;
            		int scores_medical_equipament = scores.indexOf(colunm[11]) + 1;
            		int scores_medicine = scores.indexOf(colunm[12]) + 1;

            		float x = Float.parseFloat(colunm[1]);
            		float y = Float.parseFloat(colunm[0]);
            		Geometry location = geometryFactory.createPoint(new Coordinate(x, y));
            		
                    Ubs ubs = new Ubs(id, name, address, city, phone, location,
                    		scores_size, scores_adaptation_for_seniors,
                    		scores_medical_equipament, scores_medicine);
    		    	
                    repository.save(ubs);
				} catch (Exception e) {
					System.out.println("ERROR: " + e.getMessage());
				}
            }
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
}
