import java.util.*;

public class Flight implements Comparable<Flight>,Movement{
    private final ArrayList<TrickMoves> tricks;

    public Flight(){
        this.tricks = new ArrayList<>();
    }
    @Override
    public int compareTo(Flight f) {
        /*
        Compares a flight with another flight by their number of tricks
         */
        int s1 = this.tricks.size();
        int s2 = f.tricks.size();
        if (s1 == s2) {
            return 0;
        } else if (s1 < s2) {
            return -1;
        } else {
            return 1;
        }
    }
    public void addTrick(Trick trick, Speed s, int dis, Boolean r, Format f){
        tricks.add(new TrickMoves(trick, s, dis, r, f));
    }

    public int uniqueDir(){
        Set<Direction> dir = new HashSet<>();
        for (TrickMoves trick: tricks){
            dir.addAll(trick.uniqueDir());
        }
        return dir.size();
}

    public int uniqueMoves(){
        /*
        Returns the number of unique moves inside a flight
         */
        ArrayList<IndividualMove> moves = new ArrayList<>();
        ArrayList<IndividualMove> uniqueMoves = new ArrayList<>();
        for (TrickMoves trick:tricks){
            moves.addAll(trick.getMoves());
        }
        for (IndividualMove move: moves){
            uniqueMoves.add(new IndividualMove(move.getDirection(),move.getSpeed(),move.getDistance()));
        }
        for (int i=0; i<uniqueMoves.size(); i++){
            IndividualMove m1 = uniqueMoves.get(i);
            for (int j=i+1; j<uniqueMoves.size(); j++){
                IndividualMove m2 = uniqueMoves.get(j);
                if (m1.compareTo(m2)==0){
                    uniqueMoves.remove(j);
                    j=j-1;
                }
            }
        }
        return uniqueMoves.size();
    }

    public void execute(){
        for (TrickMoves t: tricks){
            t.execute();
        }
    }


    public void displayFlight(){
        ArrayList<Trick> trickNames = new ArrayList<>();
        for (TrickMoves trick:tricks){
            trickNames.add(trick.getName());
        }
        System.out.println(trickNames);
    }

}

