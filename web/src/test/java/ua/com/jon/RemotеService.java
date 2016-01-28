package ua.com.jon;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 24.03.15
 */
public class RemotеService {
    private String login;
    private String password;
    private final String baseUrl = "http://localhost:8081";
    private final String USER_AGENT = "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36";
    private String cookies;

    public RemotеService(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static void main(String[] args) {
        RemotеService service = new RemotеService("it-centre", "prof3");
        for (int i = 0; i < 3; i++) {
            System.out.println(service.getSprintsAsJSON());
        }
    }

    public String getSprintsAsJSON() {
        String urlParameters = "j_username=" + login + "&j_password=" + password;

        String jonAddr = baseUrl + "/user/groups";
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(jonAddr);


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");

//            conn.addRequestProperty("User-Agent", USER_AGENT);
//            conn.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setInstanceFollowRedirects(false);
            conn.setUseCaches(false);

            if (conn.getResponseCode() == 302) {

                URL url1 = conn.getURL();
                cookies = conn.getHeaderField("Set-Cookie");

                conn = (HttpURLConnection) new URL(baseUrl + "/login").openConnection();
//                conn.addRequestProperty("Connection", "keep-alive");
//                conn.addRequestProperty("Host", "localhost:8081");
//                conn.addRequestProperty("Origin", "http://localhost:8081");
                conn.addRequestProperty("Cookie", cookies);
//                conn.addRequestProperty("Referer", url1.toString());
//                conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("POST");  //  http://localhost:8081/login?j_username=it-centre&j_password=prof3
                conn.setReadTimeout(5000);
//                conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                conn.addRequestProperty("User-Agent", USER_AGENT);
                conn.setDoOutput(true);
                DataOutputStream wr1 = new DataOutputStream(conn.getOutputStream());
                wr1.writeBytes(urlParameters);
                wr1.flush();

                cookies = conn.getHeaderField("Set-Cookie");

                conn = (HttpURLConnection) new URL(jonAddr).openConnection();
//                conn.addRequestProperty("Connection", "keep-alive");
//                conn.addRequestProperty("Host", "localhost:8081");
                conn.addRequestProperty("Cookie", cookies);
//                conn.addRequestProperty("Referer", "http://localhost:8081/index.html");
//                conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                conn.addRequestProperty("User-Agent", USER_AGENT);
                conn.setDoOutput(true);

//                System.out.println("code " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}