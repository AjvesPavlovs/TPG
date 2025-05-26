package jtt.tpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//Class is used to get Access Token, if it is expired

public class GetUserToken {
	
	RestTemplate restTemplate = new RestTemplate();

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

	private String getAccessToken() {
	    String auth = CLIENT_ID + ":" + CLIENT_SECRET;
	    String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Basic " + encodedAuth);
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);

	    ResponseEntity<String> response = restTemplate.postForEntity(
	            "https://accounts.spotify.com/api/token",
	            request,
	            String.class);

	    if (response.getStatusCode() == HttpStatus.OK) {
	        JSONObject json = new JSONObject(response.getBody());
	        return json.getString("access_token");
	    } else {
	        throw new RuntimeException("Failed to get access token");
	    }
	}
}