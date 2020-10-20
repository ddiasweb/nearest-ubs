package com.nearestubs.nearestubs;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.nearestubs.nearestubs.util.Converter;
import com.nearestubs.nearestubs.repo.UbsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.nearestubs.nearestubs.util.CsvLoader; 

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.ANY)
public class CsvLoaderTests {

	@Autowired
	private UbsRepository ubsRepository;

	@Test
	public void load() {
		String csvFile = "database/ubs-sample.csv";
		new CsvLoader(ubsRepository).load(csvFile);
	}



}
