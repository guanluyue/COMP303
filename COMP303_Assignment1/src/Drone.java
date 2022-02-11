import java.util.ArrayList;

public class Drone {
    private final ArrayList<Direction> moves;
    private boolean in_focus;

    Drone() {
        this.moves = new ArrayList<>();
        this.in_focus = false;
    }


    /*
    ============== Methods that allows the remote control to edit the sequence of moves ================
     */
    public void addMove(Direction d) {
        //Add from the end of the sequence
        this.moves.add(d);
    }

    public void insertMove(Direction d, int position) {
        //Insert in the specified index of the sequence
        this.moves.add(position, d);
    }

    public void deleteMove(int position) {
        //Delete move in specified position
        this.moves.remove(position);
    }

    public void clearMove(){
        //Reset the drone by clearing up the sequence and set the focus to false
        this.moves.clear();
        this.in_focus = false;
    }



    /*
    ============== Methods that simulate actual functionalities of the drone ================
     */
    private void focus() {
        //Sets the in_focus field to true, so that the drone is ready to capture
        //Declared private because these are functionalities carried out by the drone, not the remote control
        this.in_focus = true;
        System.out.println("Focusing...");
    }

    private void capture(String filename, String format) {
        //Capture a picture only when the object is in focus and save picture given filename and format
        //Declared private because these are functionalities carried out by the drone, not the remote control
        if (!this.in_focus) {
            System.out.println("The object is not in focus.");
        } else {
            if (format == "JPG" || format == "PNG" || format == "RAW" || format == "PDF") {
                System.out.println(filename + "." + format + " is successfully saved.");
            } else {
                System.out.println("Saving failed. Please enter a valid format.");
            }
        }
    }

    public ArrayList<Direction> getCopy() {
        //Returning a copy to the remote control so that the current sequence of moves can be displayed
        //Since it's not returning a reference to the original Drone.moves field, it will remain safe and unaltered
        ArrayList<Direction> copy = new ArrayList<>();
        for (Direction move : moves) {
            copy.add(move);
        }
        return copy;
    }

    public void run(String filename, String format) {
        //Drone executes all moves in the Drone.moves field
        if (this.moves.get(0) != Direction.TAKEOFF || this.moves.get(moves.size() - 1) != Direction.LAND) {
            System.out.println("The sequence must start with TAKEOFF and end with LAND");
        } else {
            System.out.println("----Power on----");
            for (Direction move : this.moves) {
                switch (move) {
                    case TAKEOFF -> {
                        System.out.println("Taking off.");
                        //The drone will lose its focus whenever it moves
                        this.in_focus = false;
                    } case UP -> {
                        System.out.println("Moving up.");
                        this.in_focus = false;
                    } case DOWN -> {
                        System.out.println("Moving down.");
                        this.in_focus = false;
                    } case FORWARD -> {
                        System.out.println("Moving forward.");
                        this.in_focus = false;
                    } case BACKWARDS -> {
                        System.out.println("Moving backwards.");
                        this.in_focus = false;
                    } case LAND -> {
                        System.out.println("Landing.");
                        this.in_focus = false;
                    }
                    case FOCUS -> this.focus();
                    case CAPTURE -> this.capture(filename, format);
                    default -> System.out.println("Invalid instruction detected.");
                }
            }
        }
    }
}

