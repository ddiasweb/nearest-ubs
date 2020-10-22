package ddiasweb.nearestubs.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConverterTests {

	@Test
	public void metresToDegrees() {
		Double metres = 111139.0;
		Double actualDegrees = Converter.metresToDegrees(metres);
		Double expectedDegrees = 1.0;
		assertThat(actualDegrees).isEqualTo(expectedDegrees);
	}

	@Test
	public void degreesToMetres() {
		Double degrees = 1.0;
		Double actualMetres = Converter.degreesToMetres(degrees);
		Double expectedMetres = 111139.0;
		assertThat(actualMetres).isEqualTo(expectedMetres);
	}	

}
