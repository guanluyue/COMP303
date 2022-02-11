
public class RunDrone {
    public static void main(String args[]) {
        // TODO: illustrate your implementations here
        Flight f1=new Flight();
        Flight f2=new Flight();
        Flight f3=new Flight();


        //f1 contains the trick TAKEOFF, which consists of 3 unique movements, 1 unique direction, 1 trick
        f1.addTrick(Trick.TAKEOFF, Speed.HIGH, 5, true, Format.AVI);
        //Illustrate that the client can query the number of unique directions.
        System.out.println("The number of unique directions of f1: "+f1.uniqueDir());

        //f2 contains the trick SIDEWINDER, which consists of 2 unique movements, 2 unique direction, 1 trick
        f2.addTrick(Trick.SIDEWINDER, Speed.LOW, 3, false, Format.NULL);
        System.out.println("The number of unique directions of f2: "+f2.uniqueDir());

        //f3 contains the trick PUCKER, which consists of 1 unique movements, 1 unique direction, 2 tricks
        f3.addTrick(Trick.PUCKER, Speed.MODERATE, 4,true, Format.MP4);
        f3.addTrick(Trick.PUCKER, Speed.MODERATE, 4,false, Format.NULL);
        System.out.println("The number of unique directions of f3: "+f3.uniqueDir());


        Drone d = new Drone("Tester");
        d.addFlight(f1);
        d.addFlight(f2);
        d.addFlight(f3);

        d.sortByMove();//Sorted order: [f3,f2,f1]
        d.sortByNum(); //Sorted order: [f1,f2,f3]

        /*
        Note that here the client also has the option to sort the flights by unique DIRECTIONS.
        It's an additional feature. Since the client is able to query the number of unique directions
        in Question 3, it makes intuitive sense if the client is also able to sort the flights based
        on the unique directions.
         */
        d.sortByDir(); //Sorted order: [f3,f1,f2]



        //Lastly execute all the flights in the drone
        d.execute();
    }
}
