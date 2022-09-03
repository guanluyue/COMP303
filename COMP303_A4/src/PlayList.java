import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {
    private final CommandProcessor aProcessor = new CommandProcessor();
    private final List<Playable> aList = new ArrayList<>();
    private String aName;

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
        aProcessor.call(createAddCommand(pPlayable));
    }

    private Command createAddCommand(Playable pPlayable){

        return new Command(){
            @Override
            public void execute() {
                aList.add(pPlayable);
            }
            @Override
            public void undo(){
                aList.remove(aList.size()-1);
            }

        };
    }

    /**
     * remove a playable from the Playlist given its index
     * @param pIndex
     *          the index of playable to be removed
     * @return the removed playable
     */
    public Playable removePlayable(int pIndex) {
        assert pIndex >= 0 && pIndex < aList.size();
        Playable removed = aList.get(pIndex);
        Command command = createRemoveCommand(pIndex);
        aProcessor.call(command);
        return removed;
    }

    private Command createRemoveCommand(int pIndex){
        return new Command(){
            Playable aPlayable;
            @Override
            public void execute() {
                assert pIndex >= 0 && pIndex < aList.size();
                aPlayable = aList.remove(pIndex);
            }
            @Override
            public void undo(){
                aList.add(pIndex, aPlayable);
            }

        };
    }
    /**
     * @return The name of the playlist.
     */
    public String getName() {
        aProcessor.clear();
        return aName;
    }

    /**
     * modify the name of the playlist
     * @param pName
     *          the new name of the playlist
     */
    public void setName(String pName) {
        assert pName != null;
        aProcessor.call(createSetNameCommand(pName));
    }

    private Command createSetNameCommand(String pName){
        return new Command(){
            String prevName;
            @Override
            public void execute() {
                prevName = aName;
                aName = pName;
            }
            @Override
            public void undo(){
                aName = prevName;
                prevName = aName;
            }
        };
    }

    public ArrayList<Playable> getList(){
        aProcessor.clear();
        return new ArrayList<>(this.aList);
    }
    /**
     * Iterating through the playlist to play playable content.
     */
    @Override
    public void play() {
        for(Playable playable:aList){
            playable.play();
        }
        aProcessor.clear();
    }

    @Override
    public Playable clone() {
        try{
            PlayList clone = (PlayList) super.clone();
            /*for (Playable playable: aList){
                clone.aList.add(playable.clone());
            }

             */
            return clone;
        }catch(CloneNotSupportedException e){
            return null;
        }
    }

    /**
     * change the order how playlist play the playables it contains
     */
    public void shuffle() {
        aProcessor.call(createShuffleCommand());
    }

    private Command createShuffleCommand(){
        return new Command(){
            List<Playable> beforeShuffle = new ArrayList<>();
            @Override
            public void execute() {
                beforeShuffle.clear();
                beforeShuffle.addAll(aList);
                while(aList.equals(beforeShuffle)){
                    Collections.shuffle(aList);
                }

            }
            @Override
            public void undo(){
                aList.clear();
                aList.addAll(beforeShuffle);
                beforeShuffle.clear();
            }
        };
    }

    public void undo(){
        this.aProcessor.undo();
    }

    public void redo(){
        this.aProcessor.redo();
    }

    /**
     * Checks is two playlists are equal based on playable objects and their order
     *
     * @param o
     *            The object to compare a playlist to
     * @return    This method returns true if the playlist is the same as the obj argument; false otherwise.
    **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return this.aList.equals(playList.aList);
    }



    /**
     * Equal playlists have the same hashcode
    **/
    @Override
    public int hashCode() {
        return Objects.hash(aList);
    }

    public void clear(){
        aList.clear();
        aProcessor.clear();
    }
}
