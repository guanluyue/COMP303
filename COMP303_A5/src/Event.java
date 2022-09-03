import java.time.LocalDate;

/*
Representation of a type of Event that can exist
 */
public interface Event {
    String getName();
    Location getLocation();
    LocalDate getDate();
    double getPrice();
    int getNumTickets();
    void accept(Visitor pVisitor);
}
