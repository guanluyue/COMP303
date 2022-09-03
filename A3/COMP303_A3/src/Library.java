import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an Audio library, with individual Song titles, Podcasts and playlists.
 */
public class Library {
    //The name of the library
    private String aName;
    //The natural language description provided by the user
    private String aLanguage;
    private final ArrayList<Playable> aContent = new ArrayList<>();
    private static final HashMap<String, Playable> libMap = new HashMap<>();
    private static final Library INSTANCE = new Library();

    private Library(){
    }

    public static Library getInstance(){
        return INSTANCE;
    }

    public void setName(String name){
        this.aName = name;
    }

    public void setLanguage(String language){
        this.aLanguage = language;
    }

    /**
     * Adds a Song to the library. Duplicate Songs aren't added twice.
     *
     * @param pSong the Song to add
     */
    public void addSong(Song pSong) {
        // Please add you implementation here
        for (Playable content: aContent){
            if (content instanceof Song){
                if (pSong.equals(content)){
                    return;
                }
            }
        }
       aContent.add(Song.get(pSong.getaTitle(), pSong.getaArtist()));
    }

    /**
     * Adds a PlayList to the library. All Songs from the list are also added as individual Songs to the library.
     *
     * @param pList
     *            the PlayList to add
     * @pre pList!=null;
     */
    public void addPlayList(PlayList pList) {

        for (Playable content: aContent){
            if (content instanceof PlayList){
                if(pList.equals((PlayList)content)){
                    return;
                }
            }
        }
            aContent.add(pList);
    }

    /**
     * Adds a Podcast to the library. All Episodes from the list are also added as individual episodes to the library.
     *
     * @param pPodcast
     *            the Podcast to add
     * @pre pPodcast!=null;
     */
    public void addPodcast(Podcast pPodcast) {
        // Please add you implementation here
        Playable existing_podcast = libMap.get(pPodcast.getaName()+pPodcast.getaHost());
        if (!(existing_podcast instanceof Podcast)) {
            aContent.add(pPodcast);
            libMap.put(pPodcast.getaName() + pPodcast.getaHost(), pPodcast);
        }
    }

    public ArrayList<Playable> getaContent(){
        return new ArrayList<>(aContent);
    }

    public String toString(){
        return this.aName+"--"+this.aLanguage+"\nThe library contains: " + this.aContent;
    }
    public static void main(String[]args){
        Library library = getInstance();
        Song s1=Song.get("Hello","Adele");
        Song s2=Song.get("hello","adele");
        Song s3=Song.get("hi","adele");
        library.addSong(s1);
        library.addSong(s2);
        library.addSong(s3);
        System.out.println(library.aContent);

    }
}
