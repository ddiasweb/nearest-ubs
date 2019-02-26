package com.bionexo.nearestubs.controller.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.bionexo.nearestubs.model.Ubs;
import com.bionexo.nearestubs.repo.UbsRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class CsvLoader {

	public void load(UbsRepository repository) {

        String csvFile = "database/ubs.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

        	GeometryFactory geometryFactory = new GeometryFactory();

            while ((line = br.readLine()) != null) {

                String[] colunm = line.split(cvsSplitBy);
                
        		try {
            		int id = Integer.parseInt(colunm[3]);
            		String name = colunm[4];
            		String address = colunm[5];
            		String city = colunm[7];
            		String phone = colunm[8];
            		int scores_size = 1;
            		int scores_adaptation_for_seniors = 1;
            		int scores_medical_equipament = 1;
            		int scores_medicine = 1;

            		float x = Float.parseFloat(colunm[0]);
            		float y = Float.parseFloat(colunm[1]);
            		Geometry location = geometryFactory.createPoint(new Coordinate(x, y));
            		
                    Ubs ubs = new Ubs(id, name, address, city, phone, location,
                    		scores_size, scores_adaptation_for_seniors, scores_medical_equipament, scores_medicine);
    		    	repository.save(ubs);
				} catch (Exception e) {
					e.printStackTrace();
				}

            }
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
