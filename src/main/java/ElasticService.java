import model.Temperature;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElasticService {

    private static final String HOST = "localhost";
    private static final String INDEX_NAME = "temperatures";
    private static final int PORT = 9200;

    private static class ElasticWrapper{
        private static RestHighLevelClient instanceClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(HOST, PORT, "http")));
    }

    public static RestHighLevelClient getInstance(){
        return ElasticWrapper.instanceClient;
    }

    public static void createTemperatureIndexIfNotExists() throws IOException {
        Response response = getInstance().getLowLevelClient().performRequest("HEAD", "/" + INDEX_NAME);
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 404) {
            CreateIndexRequest cireq=new CreateIndexRequest(INDEX_NAME);
            CreateIndexResponse ciresp=getInstance().indices().create(cireq);
            System.out.println("***Temperature index has been created**");
        } else {
            System.out.println("***Temperature index already exists***");
        }
    }

    /**
     * Method which index a temperature
     * We need a given index (in our case it is temperatures index)
     * We need a content type
     * We need an id (our uuid)
     * We need document source provided as a String
     */
    public static void indexATemperature(Temperature temperature) throws IOException {

        System.out.println("Index a temperature");

        // put raw JSON
        //IndexRequest request = new IndexRequest(
        //        INDEX_NAME,
        //        "doc",
        //        "1");
        //String jsonString = "{" +
        //        "\"id\":\"1\"," +
        //        "\"date\":\"1542658557\"," +
        //        "\"celsius_value\":\"10.0\"" +
        //        "\"latitude\":\"40.1\"" +
        //        "\"longitude\":\"40.2\"" +
        //        "}";
        //request.source(jsonString, XContentType.JSON);

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", temperature.getId());
        jsonMap.put("date", temperature.getDate().getTime());
        jsonMap.put("celsius_value", temperature.getCelsiusValue());
        jsonMap.put("city", temperature.getLocation().getName());
        jsonMap.put("latitude", temperature.getLocation().getLatitude());
        jsonMap.put("longitude", temperature.getLocation().getLongitude());

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME, "doc", temperature.getId())
                .source(jsonMap);

        IndexResponse indexResponse = getInstance().index(indexRequest, RequestOptions.DEFAULT);
    }

    public static void closeInstance() throws IOException {
            getInstance().close();
    }
}
