package jtt.tpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.json.JSONObject;

//Class is used to get Access Token, if it is expired

public class GetUserToken {

	private static final String CLIENT_ID = "86759f103ae94b81b77230c3cfb039fa"; // Replace with your Spotify Client ID
    private static final String CLIENT_SECRET = "4cf53223fb55487686cd0ce1431cd854"; // Replace with your Spotify Client Secret
    
	public static void main(String[] args) {
		try {
            String accessToken = getAccessToken();
            System.out.println("Access Token: " + accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAccessToken() throws IOException {
        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        URL url = new URL("https://accounts.spotify.com/api/token");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");

        conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        String body = "grant_type=client_credentials";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.getBytes());
            os.flush();
        }

        if (conn.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String input;
            while ((input = in.readLine()) != null) {
                response.append(input);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            return json.getString("access_token");
        } else {
            throw new IOException("Failed to get token. HTTP code: " + conn.getResponseCode());
        }
        
    }
}