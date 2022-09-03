import java.time.LocalDate;
import java.util.Optional;

public abstract class AnEvent implements Event{
    protected String aName;
    protected Location aLocation = Location.Unknown;
    protected LocalDate aDate;
    protected Double aPrice = (double) -1;
    protected Integer aTicket = -1;

    protected AnEvent(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket){
        aName=pName;
        aLocation=pLocation;
        aDate=pDate;
        aPrice=pPrice;
        aTicket=pTicket;
    }

    protected AnEvent(String pName, LocalDate pDate){
        aName=pName;
        aDate=pDate;
    }
    protected AnEvent(String pName){
        aName=pName;
    }

    @Override
    public double getPrice() {
        return aPrice;
    }

    public void setPrice(double pPrice){
        if (aPrice<0){
            aPrice=pPrice;
        }else{
            System.out.println("Price has been set already and cannot be changed. (Immutable)");
        }
    }

    @Override
    public String getName(){
        return aName;
    }

    @Override
    public Location getLocation(){
        return aLocation;
    }

    public void setLocation(Location pLocation){
        if (aLocation==Location.Unknown){
            aLocation=pLocation;
        }else{
            System.out.println("Location has been set already and cannot be changed. (Immutable)");
        }
    }
    @Override
    public int getNumTickets(){
        return aTicket;
    }

    public void setTicket(int pTicket){
        if (aTicket<0){
            aTicket=pTicket;
        }else{
            System.out.println("Number of tickets has been set already and cannot be changed. (Immutable)");
        }
    }
    @Override
    public LocalDate getDate(){
        return aDate;
    }

    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitEvent(this);
    }



    public boolean equals(AnEvent event) {
        if (this.getLocation() == event.getLocation() && this.getDate() == event.getDate()) {
            return false;
        } else {
            return true;
        }
    }
}


