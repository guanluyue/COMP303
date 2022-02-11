public class RunDrone {
    public static void main(String[] args){

        RemoteControl r1 = new RemoteControl();

        System.out.println("=== 1.An ideal example of drone executes all moves successfully.");
        r1.add(Direction.TAKEOFF);
        r1.add(Direction.FORWARD);
        r1.add(Direction.UP);
        r1.add(Direction.BACKWARDS);
        r1.add(Direction.DOWN);
        r1.add(Direction.FOCUS);
        r1.add(Direction.CAPTURE);
        r1.add(Direction.LAND);
        r1.run("first_picture","RAW");

        System.out.println("\n=== 2.An example of drone not in focus while capturing the photo.");
        r1.reset();
        r1.add(Direction.TAKEOFF);
        r1.add(Direction.CAPTURE);
        r1.add(Direction.LAND);
        r1.run("second_picture","PDF");

        System.out.println("\n=== 3. An example of editing the sequence by adding the move FOCUS.");
        r1.insert(Direction.FOCUS,1);
        r1.run("third_picture","PNG");

        System.out.println("\n=== 4. An example of the sequence not starting with TAKEOFF. " +
                "(Ending without LAND works the same so it's omitted)");
        r1.delete(0);
        r1.run("fourth_picture","JPG");

        System.out.println("\n=== 5. An example of invalid picture format.");
        r1.insert(Direction.TAKEOFF,0);
        r1.run("fifth_picture","AAA");

        /*
        This below code failed to build since the r1.drone is declared private, which prevents corruption
        Drone testDrone = new Drone();
        testDrone = r1.drone;

        */
    }
}
