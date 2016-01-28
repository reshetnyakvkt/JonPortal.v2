package ua.com.jon;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 22.03.15
 */
public class ConnectTest {
    public static void main(String[] args) {
        String urlParameters = "j_username=it-centre&j_password=prof3";

        // This is the REST Web Service to be invoked
        String baseUrl = "http://localhost:8081";     // http://localhost:8081/user/groups
        String jonAddr = baseUrl + "/user/groups";

        try {
            URL url = new URL(jonAddr);

            System.out.println(jonAddr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(5000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
            conn.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setInstanceFollowRedirects(false);
            conn.setUseCaches (false);

            if (conn.getResponseCode() != 300) {
                System.out.println("error " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            Map<String, List<String>> headerFields = conn.getHeaderFields();

            URL url1 = ((HttpURLConnection) conn).getURL();
            String cookie = url1.getPath().substring(url1.getPath().indexOf(";") + 1);
            String cookies = conn.getHeaderField("Set-Cookie");

            conn = (HttpURLConnection) new URL(baseUrl + "/login").openConnection();
            conn.addRequestProperty("Connection", "keep-alive");
            conn.addRequestProperty("Host", "localhost:8081");
            conn.addRequestProperty("Origin", "http://localhost:8081");
            conn.addRequestProperty("Cookie", cookies);
            conn.addRequestProperty("Referer", url1.toString());
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");  //  http://localhost:8081/login?j_username=it-centre&j_password=prof3
            conn.setReadTimeout(5000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
            conn.setDoOutput(true);
            DataOutputStream wr1 = new DataOutputStream(conn.getOutputStream ());
            wr1.writeBytes(urlParameters);
            wr1.flush();
//            wr1.close();
            if (conn.getResponseCode() != 200) {
                System.out.println("error " + conn.getResponseCode());
            }

            cookies = conn.getHeaderField("Set-Cookie");

            conn = (HttpURLConnection) new URL(jonAddr).openConnection();
            conn.addRequestProperty("Connection", "keep-alive");
            conn.addRequestProperty("Host", "localhost:8081");
//            conn.addRequestProperty("Origin", "http://localhost:8081");
            conn.addRequestProperty("Cookie", cookies);
            conn.addRequestProperty("Referer", "http://localhost:8081/index.html");
//            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setInstanceFollowRedirects(false);
//            conn.setReadTimeout(5000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
            conn.setDoOutput(true);

            System.out.println("code " + conn.getResponseCode());
            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
