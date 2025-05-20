package jtt.tpg.api;

import java.io.IOException;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SpotifyAPI {
    private static final String CLIENT_ID = "a0d482e0cd5444118eea994bacea9068";
    private static final String CLIENT_SECRET = "298f15569fbf4560ac5e29863d04cfab";
    private static final String AUTH_URL = "https://accounts.spotify.com/api/token";
    private static final String API_URL = "https://api.spotify.com/v1/";

    public static void main(String[] args) throws IOException, JSONException {
        String accessToken = getAccessToken();
        if (accessToken != null) {
            searchTrack(accessToken, "Imagine");
        }
    }

    // Get access token from Spotify
    public static String getAccessToken() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        // Build the authentication request
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .build();

        // Create the request with the client ID and secret as authorization headers
        Request request = new Request.Builder()
                .url(AUTH_URL)
                .post(body)
                .addHeader("Authorization", "Basic " + getBase64EncodedClientCredentials())
                .build();

        // Execute the request and parse the response
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            JSONObject jsonResponse = new JSONObject(response.body().string());
            return jsonResponse.getString("access_token");
        } else {
            System.out.println("Failed to get access token");
            return null;
        }
    }

    // Base64 encode client ID and secret
    private static String getBase64EncodedClientCredentials() {
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        return java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    // Search a track by name
    public static void searchTrack(String accessToken, String trackName) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        // Build the search request
        Request request = new Request.Builder()
                .url(API_URL + "search?q=" + trackName + "&type=track&limit=1")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        // Execute the request
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            JSONObject jsonResponse = new JSONObject(response.body().string());
            System.out.println("Track Found: " + jsonResponse.toString());
        } else {
            System.out.println("Failed to search for track");
        }
    }
}
