import java.util.HashMap;

/**
 * Represents a single Song, with at least a title, and an artist name.
 */
public class Song implements Playable{
    private static final HashMap<String, Song> song_Map = new HashMap<>();
    private final String aTitle;
    private final String aArtist;

    /**
     * Creates a Song.
     * Add your constructor here
     */
    private Song(String title, String artist){
        this.aTitle = title.toLowerCase();
        this.aArtist = artist.toLowerCase();
    }

    /**
     *
     * @param pTitle The title of the song
     * @param pArtist The artist of the song
     * @return A Song object that has no duplicates in the library
     * @pre pTitle!=null && pArtist!=null
     */
    public static Song get(String pTitle, String pArtist){
        assert pTitle != null && pArtist != null;
        pTitle = pTitle.toLowerCase();
        pArtist = pArtist.toLowerCase();
        Song song = song_Map.get(pTitle+pArtist);
        if (song == null){
            song = new Song(pTitle, pArtist);
            song_Map.put(pTitle+pArtist, song);
        }
        return song;
    }

    public String getaArtist() {
        return aArtist;
    }

    public String getaTitle(){
        return aTitle;
    }

    public boolean equals(Song pSong){
        if (pSong == null){
            return false;
        }else{
            return pSong==this;
        }
    }

    public String toString(){
        return "Song: "+this.aTitle+"-"+this.aArtist;
    }

    public void play() {
        // Just a stub.
        // We don't expect you to implement an actual music player!
        System.out.println("Now playing " + this.getaTitle() + " by " + aArtist);
    }
}