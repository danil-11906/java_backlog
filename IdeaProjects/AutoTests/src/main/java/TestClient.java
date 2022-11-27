import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class TestClient {

    public static void main(String[] args) throws Exception {
//{"name":"Александров Дмитрий Витальевич, 11-903","currency1":"CHF","currency2": "EUR","currency3": "GPB","currency4": "USD","strategy": "S2"}
        // {"name":"ZA","currency1":"JPY","currency2":"AUD","currency3":"EUR","currency4":"USD","strategy":"s3"}
        String data="{\"name\":\"Барков Данил Николаевич, 11-906\",\"currency1\":\"CHF\",\"currency2\": \"RMB\",\"currency3\": \"JPY\",\"currency4\": \"USD\",\"strategy\": \"S2\"}";

        try {
            //            URL url = new URL("http://188.93.211.195/bac/newblock?block=" +
            URL url = new URL("http://188.93.211.195/bac/newblock?block=" +
                    URLEncoder.encode(data,"UTF-8"));

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            int rcode = con.getResponseCode();

            if (rcode == 200) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                System.out.println(reader.readLine());
            } else {
                System.out.println("response code = " + rcode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
