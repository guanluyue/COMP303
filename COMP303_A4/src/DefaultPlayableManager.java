public class DefaultPlayableManager {
    private Playable aDefaultPlayable;
    public  void setDefaultPlayable(Playable pPlayable){
        assert pPlayable!=null;
        aDefaultPlayable = pPlayable;
    }

    public Playable getDefaultPlayable(){
        return aDefaultPlayable.clone();
    }

}
