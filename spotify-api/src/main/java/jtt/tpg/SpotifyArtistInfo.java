package jtt.tpg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import jtt.tpg.dto.Artist;
import jtt.tpg.dto.Image;

public class SpotifyArtistInfo {
	private GetUserToken token = new GetUserToken();
    private String accessToken = token.getAccessToken();

    /**
     * 
     *  Function to fetch artist info from Spotify API
     *  @param artistName - String data type
     *  @return initialized Artist object		
     *  
     */
    	public Artist getArtistStats(String artistName) {	
    	    try {
    	      
    	            JSONObject artistJson = getArtistJson(artistName);

    	            String artistNameInfo = artistJson.getString("name");
    	            int followersCount = artistJson.getJSONObject("followers").getInt("total");
    	            int popularity = artistJson.getInt("popularity");

    	            
    	            return new Artist(artistNameInfo, followersCount, popularity);

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.out.println("Error fetching data: " + e.getMessage());
    	        return null;
    	    }
    }
        /**
         * 
         *  Function to fetch artist genres info from Spotify API
         *  @param artistName - String data type
         *  @return String with all artist genres divided by ", "	
         *  
         */
    	public String getArtistGenres(String artistName) {
    	    try {
    	    	 JSONObject artistJson = getArtistJson(artistName);
    	     
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
    	
        /**
         * 
         *  Function to fetch artist images from Spotify API
         *  @param artistName - String data type
         *  @return List<Image> images 
         */
    	public List<Image> getArtistImages(String artistName) {

    		artistName = artistName.replace(' ', '-');  
    	    try {
    	        JSONObject artistJson = getArtistJson(artistName);
    	     
    	        JSONArray imagesArray = artistJson.getJSONArray("images");
    	     
    	        
    	        List<Image> images = new ArrayList<>();
    	        for (int i = 0; i < imagesArray.length(); i++) {
    	            JSONObject imageJson = imagesArray.getJSONObject(i);
    	            String url = imageJson.getString("url");
    	            int width = imageJson.getInt("width");
    	            int height = imageJson.getInt("height");
    	            images.add(new Image(url, width, height));
    	        }

    	        if(images.isEmpty()) return null;
    	        return images;

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return null;
    	    }
    	}
    	
    	 /**
         * 
         *  Function to fetch artist top tracks from Spotify API
         *  @param artistName - String data type
         *  @return List<Track> tracks
         */
    	public List<Image> getArtistTopTracks(String artistName) {
accessToken = token.getAccessToken();
    		
    		artistName = artistName.replace(' ', '-');  
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
    	            return null;
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
    	        
    	        System.out.println(artistResponse.toString());

    	        JSONObject artistJson = new JSONObject(artistResponse.toString());
    	        
    	        return null;

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return null;
    	    }
    	}
    	
    	 /**
         * 
         *  Function to fetch artist JSONObject from Spotify API
         *  @param artistName - String data type
         *  @return JSONObject artistJson
         */
    	public JSONObject getArtistJson(String artistName) {
    		accessToken = token.getAccessToken();
    		
    		artistName = artistName.replace(' ', '-');  
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
    	            return null;
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
    	        
    	      //  System.out.println(artistResponse.toString());

    	        return new JSONObject(artistResponse.toString());

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return null;
    	    }
    	}
}