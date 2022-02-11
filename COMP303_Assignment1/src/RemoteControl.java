import java.util.ArrayList;

public class RemoteControl {
    private final Drone drone;

    public RemoteControl() {
        this.drone = new Drone();
    }

    public void add(Direction d){
        //Adds a move to the pre-programmed sequence through the remote control
        this.drone.addMove(d);
        //After editing the sequence, the current sequence of moves will be displayed
        this.displayMoves();
    }
    public void insert(Direction d, int position){
        //Inserts a move tot the pre-programmed sequence through the remote control
        this.drone.insertMove(d,position);
        this.displayMoves();
    }
    public void delete(int position){
        //Deletes a move tot the pre-programmed sequence through the remote control
        this.drone.deleteMove(position);
        this.displayMoves();
    }
    public void reset(){
        //Resets the drone through the remote control
        this.drone.clearMove();
        this.displayMoves();
    }

    public void displayMoves() {
        //Displays the current sequence of moves on the remote control's screen
        ArrayList<Direction> copy = this.drone.getCopy();
        System.out.println("Current sequence: " + copy);
    }
    public void run(String filename, String format){
        //Turns on the drone after pre-programming all the moves, like a POWER button on the remote control
        this.drone.run(filename,format);
    }

}
