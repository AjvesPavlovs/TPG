CREATE DATABASE IF NOT EXISTS spotifyapi CHARACTER SET utf8mb4 COLLATE utf8mb4_latvian_ci;

CREATE TABLE IF NOT EXISTS spotifyapi.artists(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
	followers INT NOT NULL,
	popularity INT NOT NULL);
	
	CREATE TABLE IF NOT EXISTS spotifyapi.genres(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL);
    
    	CREATE TABLE IF NOT EXISTS spotifyapi.artist_genres(
    id INT AUTO_INCREMENT PRIMARY KEY,
    artist_id INT NOT NULL,
    genre_id INT NOT NULL,
    CONSTRAINT fk_artists_genres_artists FOREIGN KEY(artist_id) REFERENCES artists(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_genres_genres_artists FOREIGN KEY(genre_id) REFERENCES genres(id) ON UPDATE CASCADE ON DELETE CASCADE);