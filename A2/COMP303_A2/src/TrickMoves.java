import java.util.*;

public class TrickMoves implements Movement{
    private final Trick name;
    private ArrayList<IndividualMove> moves;

    /**
     * @pre dis>0 && s>0
     */
    TrickMoves(Trick trick, Speed s, int dis, Boolean r, Format f){
        switch (trick) {
            //Add individual movements to the trick depending on the trick name the client enters
            case TAKEOFF -> {
                this.moves = new ArrayList<>();
                this.moves.add(new IndividualMove(Direction.UP, Speed.LOW, dis, r,f));
                this.moves.add(new IndividualMove(Direction.UP, Speed.MODERATE, dis, r,f));
                this.moves.add(new IndividualMove(Direction.UP, s, dis,r,f));
            }
            case PUCKER -> {
                this.moves = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    this.moves.add(new IndividualMove(Direction.LEFT, s, dis,r,f));
                }
            }
            case SIDEWINDER -> {
                this.moves = new ArrayList<>();
                this.moves.add(new IndividualMove(Direction.LEFT, s, dis,r,f));
                this.moves.add(new IndividualMove(Direction.RIGHT, s, dis,r,f));
            }
            default -> System.out.println("Please enter a valid trick.");
        }
       this.name = trick;
    }

    public ArrayList<IndividualMove> getMoves(){
        ArrayList<IndividualMove> movesCopy = new ArrayList<>();
        for (IndividualMove move: moves){
            movesCopy.add(new IndividualMove(move.getDirection(), move.getSpeed(), move.getDistance(),
                    move.getRecord(), move.getFormat()));
        }
        return movesCopy;
    }

    public Trick getName(){
        return this.name;
    }

    @Override
    public void execute() {
        System.out.println();
        for (IndividualMove m: moves) {
            m.execute();
        }
    }

    public Set<Direction> uniqueDir(){
        Set<Direction> dir = new HashSet<>();
        for (IndividualMove move: moves){
            dir.add(move.getDirection());
        }
        return dir;
    }


}




