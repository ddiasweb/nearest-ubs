package ddiasweb.nearestubs.util;

public class Converter {

	public static double metresToDegrees(double metres) {
		// One degree latitude is approximately 111,139 metres on a spherical earth
		return metres / 111139;
	}

    public static double degreesToMetres(double degrees) {
		return degrees * 111139;
	}
    
}
