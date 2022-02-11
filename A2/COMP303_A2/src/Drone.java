import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Drone implements Movement{
    private final ArrayList<Flight> flights;
    final private String name;

    /**
     * Constructor
     * @param name of the drone
     */
    public Drone(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }

    /**
     * Name getter
     * @return drone name
     */
    public String getName() {
        return this.name;
    }

    public void execute(){
        for (Flight f: flights)
        {
            f.execute();
            System.out.println("=====Flight executed.=====");
        }

    }

    public void addFlight(Flight f){
        this.flights.add(f);
    }

    public static Comparator<Flight> createByMoveComparator(){
        /*
        Compare the flights based on the unique movements provided by the
        method inside the flight class.
        */
        return new Comparator<Flight>(){
            public int compare(Flight f1,Flight f2){
                int mov1=f1.uniqueMoves();
                int mov2=f2.uniqueMoves();
                return Integer.compare(mov1, mov2);

            }

        };
    }
    public static Comparator<Flight> createByDirComparator(){
        return new Comparator<Flight>() {
            public int compare(Flight f1, Flight f2) {
                int dir1 = f1.uniqueDir();
                int dir2 = f2.uniqueDir();
                return Integer.compare(dir1, dir2);
            }
        };
    }
    public static Comparator<Flight> createByNumComparator() {
        return new Comparator<Flight>() {
            public int compare(Flight f1, Flight f2) {
                return f1.compareTo(f2);
            }

        };
    }
    public void sortByNum(){
        Collections.sort(this.flights, createByNumComparator());
        for (Flight f: flights){
            f.displayFlight();
        }
        System.out.println();
    }

    public void sortByMove(){
        Collections.sort(this.flights, createByMoveComparator());
        for (Flight f: flights){
            f.displayFlight();
        }
        System.out.println();
    }

    public void sortByDir(){
        Collections.sort(this.flights, createByDirComparator());
        for (Flight f: flights){
            f.displayFlight();
        }
        System.out.println();
    }

}
