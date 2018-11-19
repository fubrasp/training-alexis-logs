package faker;

import model.Location;
import model.Temperature;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.UUID;

public class DataFaker {

    /**
     * Build a fake dataset
     * @param size
     * @return HashSet<Temperature> set of random temperatures
     */
    public static HashSet<Temperature> getRandomTemperatureDataSet(int size) throws InterruptedException {
        HashSet<Temperature> temperatures = new HashSet<Temperature>();
        for (int i = 0; i<size; i++){
            temperatures.add(getRandomTemperature());
            Thread.sleep(1000);
        }
        return temperatures;
    }

    private static Temperature getRandomTemperature() {
        Location [] locations = getAvailableLocations();
        Location randomLocation = locations[getRandomNumber(0, locations.length-1)];
        return new Temperature(UUID.randomUUID().toString(), new GregorianCalendar(), getRandomNumber(0, 45), randomLocation);
    }

    private static Location[] getAvailableLocations() {
        Location princeton_nj = new Location("PRINCETON_NJ", 40.366633, 74.640832);
        Location ithaca_ny = new Location("ITHACA_NY",    42.443087, 76.488707);
        Location paris = new Location("PARIS",    48.866667, 2.333333);
        Location newYork = new Location("NEW_YORK",    40.7127753, -74.0059728);
        Location zurich = new Location("ZURICH",    47.3768866, 8.541694000000007);
        Location [] locations = {princeton_nj, ithaca_ny, paris, newYork, zurich};
        return locations;
    }

    private static int getRandomNumber(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
