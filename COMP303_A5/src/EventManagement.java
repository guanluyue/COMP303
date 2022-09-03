import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/*
Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
    private final ArrayList<AnEvent> aHostedEvents = new ArrayList<AnEvent>();

    /**
     * Method to host a new concert event if there's no duplicate
     * @pre all parameters != null
     **/
    public void addConcertEvent(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, ArrayList<VIP> pVIPs, String pArtist){
        if (dupCheck(pDate, pLocation)){
            Concert concert=new Concert(pName,pLocation,pDate,pPrice,pTicket,pVIPs,pArtist);
            aHostedEvents.add(concert);
        }else{
            System.out.println("There's a duplicated event.");
        }
    }

    /**
     * Method to host a new gala event if there's no duplicate
     * @pre all parameters != null
     **/
    public void addGalaEvent(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, ArrayList<VIP> pVIPs){
        if (dupCheck(pDate,pLocation)){
            Gala gala=new Gala(pName,pLocation,pDate,pPrice,pTicket,pVIPs);
            aHostedEvents.add(gala);
        }else{
            System.out.println("There's a duplicated event.");
        }

    }

    /**
     * Method to host a new Screening event if there's no duplicate
     * @pre all parameters != null
     **/
    public void addScreeningEvent(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, String pLabel){
        if (dupCheck(pDate,pLocation)){
            Screening screening=new Screening(pName,pLocation,pDate,pPrice,pTicket,pLabel);
            aHostedEvents.add(screening);
        }else{
            System.out.println("There's a duplicated event.");
        }

    }

    /**
     *  Method to host a new Workshop event if there's no duplicate
     * @pre all parameters != null
     **/
    public void addWorkshopEvent(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, ArrayList<Workshop> pPre){
        if (dupCheck(pDate,pLocation)){
            Workshop workshop=new Workshop(pName,pLocation,pDate,pPrice,pTicket,pPre);
            aHostedEvents.add(workshop);
        }else{
            System.out.println("There's a duplicated event.");
        }
    }

    public void addFestival(String pName, ArrayList<AnEvent> pEvents){
        Festival festival = new Festival(pName, pEvents);
        aHostedEvents.add(festival);
    }

    /**
     * The block below are methods to host Coming Soon events
     * @pre all parameters != null
     **/
    public void addComingConcert(String pName, LocalDate pDate){
        Concert concert=new Concert(pName,pDate);
        aHostedEvents.add(concert);
    }
    public void addComingGala(String pName, LocalDate pDate){
       Gala gala=new Gala(pName,pDate);
       aHostedEvents.add(gala);
    }
    public void addComingScreening(String pName, LocalDate pDate){
        Screening screening=new Screening(pName,pDate);
        aHostedEvents.add(screening);
    }
    public void addComingWorkshop(String pName, LocalDate pDate){
        Workshop workshop=new Workshop(pName,pDate);
        aHostedEvents.add(workshop);
    }


    /**
     * Method to set the Location, Price, and number of Tickets for a Coming Soon Event
     * If the field has been set already, it will not be changed. (Immutable)
     * @pre all parameters != null
     **/
    public void setLocation(AnEvent pEvent, Location pLocation){
        pEvent.setLocation(pLocation);
    }
    public void setPrice(AnEvent pEvent, double pPrice){
        pEvent.setPrice(pPrice);
    }
    public void setTickets(AnEvent pEvent, int pTicket){
        pEvent.setTicket(pTicket);
    }

    /*
    Return the list of hosted events on EventBrite.
    This method assumes that Events are immutable.
     */
    public ArrayList<AnEvent> getHostedEvents(){
        return new ArrayList<AnEvent>(aHostedEvents);
    }

    /**
     * Helper method to check if there's a duplicated event. If there is a duplicate, the event will not be constructed.
     * @pre all parameters != null
     **/
    private boolean dupCheck(LocalDate pDate, Location pLocation){
        for (AnEvent e: aHostedEvents){
            if (e.getDate().equals(pDate) && e.getLocation().equals(pLocation)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to filter aHostedEvents by an inclusive price range.
     * @param min the minimum price
     * @param max the maximum price
     * @pre min smaller than or equal to max
     * @return a FilterResult object containing the field aEvent as the filtered events
     **/
    public FilterResult filterByPrice(double min, double max){
        FilterResult result = new FilterResult();
        Predicate<AnEvent> priceFilter = (AnEvent e) -> e.getPrice()>=min && e.getPrice()<=max;
        for (AnEvent e:aHostedEvents){
            if(priceFilter.test(e)){
                result.add(e);
            }
        }
        return result;
    }

    /**
     * Method to filter aHostedEvents by an Location.
     * @param pLocation the Location of the event
     * @pre pLocation!=null
     * @return a FilterResult object containing the field aEvent as the filtered events
     **/
   public FilterResult filterByLocation(Location pLocation){
        FilterResult result = new FilterResult();
        Predicate<AnEvent> locationFilter = (AnEvent e) -> e.getLocation().equals(pLocation);
        for (AnEvent e:aHostedEvents){
            if(locationFilter.test(e)){
                result.add(e);
            }
        }
        return result;
   }
    /**
     * Method to filter aHostedEvents by an Location.
     * @param pResult the FilterResult
     * @pre perConcert+perWorkshop+perGala+perScreening=1.00
     * @return the profit for the events
     **/
   public double calcProfit(FilterResult pResult, double perConcert, double perWorkshop, double perGala, double perScreening){
        return pResult.calcProfit(perConcert,perWorkshop,perGala,perScreening);
   }
   public int countVIP(FilterResult pResult){
        return pResult.calcVIPs();
   }


}
