import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataFetcher {
    public static void main(String[] args) {
        try {

            URL url = new URL("http://localhost:8080/v1/dataset");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int attempts = 0;
            int maxAttempts = 5;
            int responseCode = 0;

            while (attempts < maxAttempts) {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                try {
                    conn.connect();
                    responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("Attempt " + (attempts + 1) + " failed. Retrying...");
                    Thread.sleep(2000);
                }
                attempts++;
            }

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
