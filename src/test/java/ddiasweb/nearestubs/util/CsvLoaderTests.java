package ddiasweb.nearestubs.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ddiasweb.nearestubs.repo.UbsRepository;
import org.springframework.beans.factory.annotation.Autowired; 

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
