package jtt.tpg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;     
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class SpotifyArtistInfo {
	
    private static final String CLIENT_ID = "86759f103ae94b81b77230c3cfb039fa"; // Replace with your Spotify Client ID
    private static final String CLIENT_SECRET = "4cf53223fb55487686cd0ce1431cd854"; // Replace with your Spotify Client Secret
    private static final String ACCESS_TOKEN = "BQBD6LyiAQjUAKW7NTEhxXNKiK58USzgiTPrGSq58I7UyHgx7DNBhgMUn1vxC17Meh9WxnXlMb9W4oWqWTGNfIFX11eXPLW8TrOC9I85RDuIDDIB1zFVWuDTrBdfRP5vgtDpc049XrQ";

    public static void main(String[] args) {
    	
        Scanner input = new Scanner(System.in);
        
        // Test with a sample artist name
        String artistName = input.next() + input.nextLine();; // Replace with user input

        if (artistName != null && !artistName.trim().isEmpty()) {
            getArtistStats(artistName.trim());
        } else {
            System.out.println("Please enter an artist name");
        }
    }

    // Function to fetch artist info from Spotify API
    private static void getArtistStats(String artistName) {
        try {
            // Spotify Search API endpoint
            String urlString = "https://api.spotify.com/v1/search?q=" + artistName + "&type=artist";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
            connection.setRequestProperty("Content-Type", "application/json");

            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject artist = jsonResponse.getJSONObject("artists").getJSONArray("items").getJSONObject(0);

                if (artist != null) {
                    String artistNameInfo = artist.getString("name");
                    int followersCount = artist.getJSONObject("followers").getInt("total");

                    String genres;
                    if (artist.has("genres")) {
                        List<Object> genresList = artist.getJSONArray("genres").toList();
                        genres = genresList.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(", "));
                    } else {
                        genres = "No genres available";
                    }

                    int popularity = artist.getInt("popularity");
 
                    // Display the artist info
                    System.out.println("Artist Name: " + artistNameInfo);
                    System.out.println("Followers: " + String.format("%,d", followersCount));
                    System.out.println("Genres: " + genres);
                    System.out.println("Popularity: " + popularity);
                } else {
                    System.out.println("Artist not found");
                }
            } else {
                System.out.println("Error fetching artist data");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
}