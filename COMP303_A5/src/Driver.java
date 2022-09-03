import java.time.LocalDate;
import java.util.ArrayList;

public class Driver {
    public static void main(String[]args){
        EventManagement management=new EventManagement();
        ArrayList<VIP> VIPs=new ArrayList<>();
        management.addConcertEvent("c1",Location.BellCentre, LocalDate.of(2022,4,16),
                100.0,100, VIPs, "artist1");
        management.addConcertEvent("c2",Location.BellCentre, LocalDate.of(2022,4,16),
                100.0,100, VIPs, "artist2");//Test for duplicates
        management.addGalaEvent("g1", Location.BellCentre, LocalDate.of(2022, 4, 17),
                100.0, 100, VIPs);
        management.addScreeningEvent("s1", Location.BellCentre, LocalDate.of(2022, 4, 18),
                100.0, 100, "R");
        management.addWorkshopEvent("w1", Location.BellCentre, LocalDate.of(2022, 4, 19),
                100.0, 100, new ArrayList<Workshop>());
        management.addFestival("f1",management.getHostedEvents());

        management.addComingConcert("c1",LocalDate.of(2022,5,16));
        Concert comingConcert = (Concert) management.getHostedEvents().get(5);
        management.setTickets(comingConcert, 100);
        management.setTickets(comingConcert,200);//This will not change the number of tickets since it's immutable
        management.setLocation(comingConcert,Location.PlaceDesArts);
        management.setPrice(comingConcert,100.0);

        FilterResult locationResult=management.filterByLocation(Location.PlaceDesArts);
        FilterResult priceResult=management.filterByPrice(0,100);

        double profit = management.calcProfit(locationResult, 0.20,0.30,0.25,0.25);
        int VIP = management.countVIP(priceResult);
        System.out.println(profit);
        System.out.println(VIP);//Will be 0 because no VIP was added (for simplicity)
    }
}
