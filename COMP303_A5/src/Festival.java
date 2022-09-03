import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Festival extends AnEvent implements Iterable<AnEvent>{
    private final ArrayList<AnEvent> aEvents=new ArrayList<>();

    public Festival(String pName, ArrayList<AnEvent>pEvents){
        super(pName);
        aEvents.addAll(pEvents);

        LocationVisitor locationVisitor = new LocationVisitor();
        DateVisitor dateVisitor = new DateVisitor();
        PriceVisitor priceVisitor = new PriceVisitor();
        TicketVisitor ticketVisitor = new TicketVisitor();
        ticketVisitor.visitFestival(this);
        dateVisitor.visitFestival(this);
        priceVisitor.visitFestival(this);
        locationVisitor.visitFestival(this);

        aLocation=locationVisitor.getLocation();
        aDate=dateVisitor.getDate();
        aPrice=priceVisitor.getPrice();
        aTicket=ticketVisitor.getTicket();
    }

    public void setName(String pName){
        aName = pName;
    }

    @Override
    public String getName() {
        return aName;
    }

    @Override
    public Location getLocation() {
        return aLocation;
    }

    @Override
    public LocalDate getDate() {
        return aDate;
    }

    @Override
    public double getPrice() {
        return aPrice;
    }

    @Override
    public int getNumTickets() {
        return aTicket;
    }

    @Override
    public void accept(Visitor pVisitor){
        pVisitor.visitFestival(this);
    }

    @Override
    public Iterator<AnEvent> iterator() {
        return aEvents.iterator();
    }

}
