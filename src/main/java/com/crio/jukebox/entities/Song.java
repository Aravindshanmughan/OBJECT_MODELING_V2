package com.crio.jukebox.entities;

import java.util.List;

public class Song  {

    private final String songID;
    private final String songName;
    private final String album;
    private final String genre;
    private final String mainArtist;
    private final List<String> otherArtist;
   
    

  
    public Song(String songID, String songName, String album, String genre, String mainArtist,
            List<String> otherArtist) {
        this.songID = songID;
        this.songName = songName;
        this.album = album;
        this.genre = genre;
        this.mainArtist = mainArtist;
        this.otherArtist = otherArtist;
    }
    
    public String getSongName() {
        return songName;
    }

    public String getSongID() {
        return songID;
    }
    public String getGenre() {
        return genre;
    }
    public String getAlbum() {
        return album;
    }
    public String getMainArtist() {
        return mainArtist;
    }
    public List<String> getOtherArtist() {
        return otherArtist;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (songID == null) {
            if (other.songID != null)
                return false;
        } else if (!songID.equals(other.songID))
            return false;
        return true;
    }

    // @Override
    // public String toString() {
    //     return "Song{" +
    //     "songId='" + songID + '\'' +
    //     ", songName='" + songName + '\'' +
    //     ", album='" + album + '\'' +
    //     ", genre='" + genre + '\'' +
    //     ", mainArtist='" + mainArtist + '\'' +
    //     ", otherArtist=" + otherArtist +
    //     '}';
    // }

    @Override
    public String toString() {
         return "\nSong - " + songName + "\nAlbum - " + album + "\nArtists - " + String.join(",", otherArtist);
    }

    
    
}
