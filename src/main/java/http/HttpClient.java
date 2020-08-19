package http;

import com.google.gson.Gson;
import org.apache.poi.ss.formula.functions.T;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*klasa T = database.Slip response.
Zamieniliśmy na T bo będziemy wielokrotnie powtarzać, zachowująć zasadę DRY - don't repeat yourself*/

public class HttpClient {
    public String fetch(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            /*Gson gson = new Gson();
            T object = gson.fromJson(content.toString(), clazz);
            return object;*/
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
