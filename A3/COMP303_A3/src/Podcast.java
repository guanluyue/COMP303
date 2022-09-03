import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a single Podcast, with at least a name and a host. Each Podcast aggregates episodes.
 */
public class Podcast implements Playable{
    private static final HashMap<String, Podcast> podcast_Map = new HashMap<>();
    private final String aName;
    private final String aHost;

    private final List<Episode> aEpisodes = new ArrayList<>();

    /**
     * Creates a Podcast
     * Add your constructor below
     *
     */
    private Podcast(String name, String host){
        this.aName = name.toLowerCase();
        this.aHost = host.toLowerCase();
    }

    public static Podcast get(String pName, String pHost){
        assert pName != null && pHost != null;
        pName = pName.toLowerCase();
        pHost = pHost.toLowerCase();
        Podcast podcast = podcast_Map.get(pName+pHost);
        if (podcast == null){
            podcast = new Podcast(pName, pHost);
            podcast_Map.put(pName+pHost, podcast);
        }
        return podcast;
    }

    /**
     * Add one episode to the podcast
     * @param pEpisode to be put into the podcast
     * @pre pEpisode!=null
     */
    protected void addEpisode(Episode pEpisode) {
        if(!aEpisodes.contains(pEpisode)) {
            aEpisodes.add(pEpisode);
        }
    }

    /**
     * retrieve one episode from the podcast
     * @param pIndex
     *
     */

    public Episode getEpisode(int pIndex) {
       //
        Episode episode = aEpisodes.get(pIndex);
        return episode;
    }


    public String getaName() {
        return aName;
    }

    public String getaHost() {
        return aHost;
    }

    @Override
    public void play() {
        for (Episode episode: aEpisodes){
            episode.play();
        }
    }
    public String toString(){
        return "Podcast: "+this.aName+"-"+this.aHost;
    }
}