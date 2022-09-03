import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    private final List<Playable> aList = new LinkedList<>();
    private String aName;
    private int aNext;

    /**
     * Creates a new empty playlist.
     *
     * @param pName
     *            the name of the list
     * @pre pName!=null;
     */
    public PlayList(String pName) {
        assert pName != null;
        aName = pName;
        aNext = 0;
    }

    /**
     * Adds a playable at the end of this playlist.
     *
     * @param pPlayable
     *            the content to add to the list
     * @pre pPlayable!=null;
     */
    public void addPlayable(Playable pPlayable) {
        assert pPlayable != null;
        aList.add(pPlayable);
    }


    @Override
    public void play() {
        for (Playable content: aList){
            content.play();
        }
    }


    public boolean equals(PlayList pPlayList) {
        if (pPlayList.aList.size() != this.aList.size()) {
            return false;
        } else {
            int i = 0;
            while (i < pPlayList.aList.size()) {
                Playable p1 = pPlayList.aList.get(i);
                Playable p2 = this.aList.get(i);
                if (p1.getClass() != p2.getClass()) {
                    return false;
                } else {
                    if (p1.equals(p2)) {
                        i++;
                    }
                }
            }
            return true;
        }
    }
    public String toString(){
        return "Playlist: "+this.aName+" contains "+this.aList;
    }
}
