import java.util.Comparator;

public class IndividualMove implements Movement,Comparable<IndividualMove>{
    private final Direction direction;
    private final Speed speed;
    private final int distance;
    private final Boolean record;
    private final Format format;

    IndividualMove(Direction d, Speed s, int dis, Boolean r, Format f){
        this.direction = d;
        this.speed = s;
        this.distance = dis;
        this.record = r;
        this.format = f;
    }
    IndividualMove(Direction d, Speed s, int dis){
        this.direction = d;
        this.speed = s;
        this.distance = dis;
        this.record = false;
        this.format = Format.NULL;
    }

    public Speed getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDistance() {
        return distance;
    }

    public Boolean getRecord(){
        return record;
    }

    public Format getFormat(){
        return format;
    }
    public int compareTo(IndividualMove m){
        if (m.getDistance()==this.getDistance() && m.getSpeed()==this.getSpeed()
        && m.getDirection()==this.getDirection()){
            return 0;
        }else{
            return 1;
        }
    }

    public void execute(){
        System.out.println("Moving "+this.getDirection()+" "+ this.getDistance()+ "m at "+this.getSpeed()+" speed.");
        if (this.record){
            System.out.println("The movement has been recorded and saved as "+this.format);
        }
    }
    public static void main (String[]args){

    }
}
