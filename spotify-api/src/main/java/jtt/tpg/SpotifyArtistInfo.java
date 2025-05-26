package jtt.tpg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;     
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import jtt.tpg.dto.Artist;

public class SpotifyArtistInfo {
	
    private static final String CLIENT_ID = "86759f103ae94b81b77230c3cfb039fa"; // Replace with your Spotify Client ID
    private static final String CLIENT_SECRET = "4cf53223fb55487686cd0ce1431cd854"; // Replace with your Spotify Client Secret
    private static final String ACCESS_TOKEN = "BQAqcfgPb3LxYFpBRJNAmTv12holqPYkmu2_Fj2S99u4dWQuVgnXX1kOQ1q6t8dpEqYx8rIvv176efzm_Lx7kNFHm5RVcVA7O2rc44F9i1OymLYi-Oi2q7sgquKfNmOs3v7XC9iJ7KI";


    // Function to fetch artist info from Spotify API
    	private static Artist getArtistStats(String artistName) {
    	    try {
    	        String urlString = "https://api.spotify.com/v1/search?q=" + artistName + "&type=artist";
    	        URL url = new URL(urlString);
    	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	        connection.setRequestMethod("GET");
    	        connection.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
    	        connection.setRequestProperty("Content-Type", "application/json");

    	        int responseCode = connection.getResponseCode();
    	        if (responseCode == HttpURLConnection.HTTP_OK) {
    	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    	            String inputLine;
    	            StringBuilder response = new StringBuilder();
    	            while ((inputLine = in.readLine()) != null) {
    	                response.append(inputLine);
    	            }
    	            in.close();

    	            JSONObject jsonResponse = new JSONObject(response.toString());
    	            JSONArray items = jsonResponse.getJSONObject("artists").getJSONArray("items");
    	            if (items.length() == 0) {
    	                // No artist found
    	                return null;
    	            }
    	            JSONObject artist = items.getJSONObject(0);

    	            String artistNameInfo = artist.getString("name");
    	            int followersCount = artist.getJSONObject("followers").getInt("total");

    	            String genres;
    	            if (artist.has("genres") && artist.getJSONArray("genres").length() > 0) {
    	                List<Object> genresList = artist.getJSONArray("genres").toList();
    	                genres = genresList.stream()
    	                        .map(Object::toString)
    	                        .collect(Collectors.joining(", "));
    	            } else {
    	                genres = "No genres available";
    	            }

    	            int popularity = artist.getInt("popularity");

    	            // Return Artist object
    	            return new Artist(artistNameInfo, followersCount, popularity);
    	        } else {
    	            System.out.println("Error fetching artist data: HTTP " + responseCode);
    	            return null;
    	        }

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.out.println("Error fetching data: " + e.getMessage());
    	        return null;
    	    }
    }
}