package jtt.tpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import jtt.tpg.dto.Artist;

public class SpotifyArtistInfo {
	GetUserToken token = new GetUserToken();
    private String accessToken = token.getAccessToken();

    // Function to fetch artist info from Spotify API
    	public Artist getArtistStats(String artistName) {
    		accessToken = token.getAccessToken();
    	    try {
    	        String urlString = "https://api.spotify.com/v1/search?q=" + artistName + "&type=artist";
    	        URL url = new URL(urlString);
    	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	        connection.setRequestMethod("GET");
    	        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
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

    	            /*
    	            String genres;
    	            if (artist.has("genres") && artist.getJSONArray("genres").length() > 0) {
    	                List<Object> genresList = artist.getJSONArray("genres").toList();
    	                genres = genresList.stream()
    	                        .map(Object::toString)
    	                        .collect(Collectors.joining(", "));
    	            } else {
    	                genres = "No genres available";
    	            }
					*/
						
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
    	public String getArtistGenres(String artistName) {
    		accessToken = token.getAccessToken();
    	    try {
    	        
    	        String searchUrl = "https://api.spotify.com/v1/search?q=" + URLEncoder.encode(artistName, "UTF-8") + "&type=artist&limit=1";
    	        HttpURLConnection searchConnection = (HttpURLConnection) new URL(searchUrl).openConnection();
    	        searchConnection.setRequestMethod("GET");
    	        searchConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
    	        searchConnection.setRequestProperty("Content-Type", "application/json");

    	        int searchResponseCode = searchConnection.getResponseCode();
    	        if (searchResponseCode != HttpURLConnection.HTTP_OK) {
    	            System.out.println("Error searching artist: HTTP " + searchResponseCode);
    	            return null;
    	        }

    	        BufferedReader searchIn = new BufferedReader(new InputStreamReader(searchConnection.getInputStream()));
    	        StringBuilder searchResponse = new StringBuilder();
    	        String inputLine;
    	        while ((inputLine = searchIn.readLine()) != null) {
    	            searchResponse.append(inputLine);
    	        }
    	        searchIn.close();

    	        JSONObject searchJson = new JSONObject(searchResponse.toString());
    	        JSONArray items = searchJson.getJSONObject("artists").getJSONArray("items");
    	        if (items.length() == 0) {
    	            return "Artist not found";
    	        }

    	        String artistId = items.getJSONObject(0).getString("id");

    	        
    	        String artistUrl = "https://api.spotify.com/v1/artists/" + artistId;
    	        HttpURLConnection artistConnection = (HttpURLConnection) new URL(artistUrl).openConnection();
    	        artistConnection.setRequestMethod("GET");
    	        artistConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
    	        artistConnection.setRequestProperty("Content-Type", "application/json");

    	        int artistResponseCode = artistConnection.getResponseCode();
    	        if (artistResponseCode != HttpURLConnection.HTTP_OK) {
    	            System.out.println("Error fetching artist details: HTTP " + artistResponseCode);
    	            return null;
    	        }

    	        BufferedReader artistIn = new BufferedReader(new InputStreamReader(artistConnection.getInputStream()));
    	        StringBuilder artistResponse = new StringBuilder();
    	        while ((inputLine = artistIn.readLine()) != null) {
    	            artistResponse.append(inputLine);
    	        }
    	        artistIn.close();

    	        JSONObject artistJson = new JSONObject(artistResponse.toString());
    	     
    	        JSONArray genresArray = artistJson.getJSONArray("genres");
    	        
    	        if (genresArray.length() > 0) {
    	            List<Object> genresList = genresArray.toList();
    	            return genresList.stream()
    	                    .map(Object::toString)
    	                    .collect(Collectors.joining(", "));
    	        } else {
    	            return "No genres available";
    	        }

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return "Error: " + e.getMessage();
    	    }
    	}
    	
}