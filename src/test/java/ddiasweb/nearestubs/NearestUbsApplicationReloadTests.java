package ddiasweb.nearestubs;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringRunner.class)
@SpringBootTest(args = "--file=database/ubs-sample.csv")
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class NearestUbsApplicationReloadTests {
	
	@Autowired
	ApplicationArguments args;	
	
	@Test
	public void reload() {
		Set<String> optionNames = args.getOptionNames();
		System.out.println("optionsNames: "+optionNames);
		assertThat(optionNames).containsOnly("file");
		List<String> optionValues = args.getOptionValues("file");
		System.out.println("optionsValues for processor.name: "+optionValues);
		assertThat(optionValues).containsOnly("database/ubs-sample.csv");
	}

}
