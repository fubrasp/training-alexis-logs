import faker.DataFaker;
import model.Temperature;

import java.io.IOException;
import java.util.HashSet;

public class Main {
    public static void main(String [] args) throws InterruptedException, IOException {
        // 1 - We obtain something to log
        HashSet<Temperature> temperatures = DataFaker.getRandomTemperatureDataSet(100);

        // 2 - We decide to log it
        // In java ;)
        System.out.println(temperatures);

        // 3 - On elastic search
        ElasticService.createTemperatureIndexIfNotExists();
        for (Temperature temperature :
             temperatures) {
            ElasticService.indexATemperature(temperature);
        }
    }
}
