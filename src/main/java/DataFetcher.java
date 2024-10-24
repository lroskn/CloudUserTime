import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataFetcher {
    public static void main(String[] args) {
        try {

            URL url = new URL("http://assessment:8080/v1/dataset");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responseCode = conn.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder sb = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                }
                System.out.println(sb);
                ObjectMapper mapper = new ObjectMapper();
                Event[] events = new Event[]{mapper.readValue(sb.toString(), new TypeReference<Event>() {
                })};
                System.out.println(TimeSpanCalculator.calculateTimeSpent(Arrays.stream(events).toList()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
