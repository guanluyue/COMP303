import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EventManagementTest {
    private EventManagement management=new EventManagement();
    private VIP v1=new VIP();
    private VIP v2=new VIP();
    private ArrayList<VIP> VIPs=new ArrayList<>();

    private boolean invokeDupCheck(LocalDate pDate, Location pLocation){
        try{
            Method method = management.getClass().getDeclaredMethod("dupCheck", LocalDate.class, Location.class);
            method.setAccessible(true);
            return (boolean) method.invoke(management, pDate, pLocation);
        }catch (ReflectiveOperationException e){
            e.printStackTrace();;
            fail();
            return false;
        }
    }

    @Test
    public void testAddConcert(){
        VIPs.add(v1);
        VIPs.add(v2);
        management.addConcertEvent("c1",Location.BellCentre, LocalDate.of(2022,4,16),
                100.0,100, VIPs, "artist1");
        assertTrue(management.getHostedEvents().get(0) instanceof Concert);
    }

    @Test
    public void testAddComingConcert(){
        management.addComingConcert("c1",LocalDate.of(2022,4,16));
        assertTrue(management.getHostedEvents().get(0) instanceof Concert);
    }

    @Test
    public void testDupAddEvent(){
        management.addConcertEvent("c1",Location.BellCentre, LocalDate.of(2022,4,16),
                100.0,100, VIPs, "artist1");
        management.addConcertEvent("c2",Location.BellCentre, LocalDate.of(2022,4,16),
                50.0,50, VIPs, "artist1");
        management.addGalaEvent("g1", Location.BellCentre, LocalDate.of(2022, 4, 16),
                100.0, 100, VIPs);
        management.addScreeningEvent("s1", Location.BellCentre, LocalDate.of(2022, 4, 16),
                100.0, 100, "R");
        management.addWorkshopEvent("w1", Location.BellCentre, LocalDate.of(2022, 4, 16),
                100.0, 100, new ArrayList<Workshop>());
        assertEquals(management.getHostedEvents().size(),1);
    }

    @Test
    public void testDupCheck_True(){
        management.addConcertEvent("c1",Location.BellCentre, LocalDate.of(2022,4,16),
                100.0,100, VIPs, "artist1");
        assertTrue(invokeDupCheck(LocalDate.of(2020,1,1),Location.BellCentre));
    }

    @Test
    public void testDupCheck_False(){
        management.addConcertEvent("c1",Location.BellCentre, LocalDate.of(2022,4,16),
                100.0,100, VIPs, "artist1");
        assertFalse(invokeDupCheck(LocalDate.of(2022,4,16),Location.BellCentre));
    }

    @Test
    public void testAddGala() {
        VIPs.add(v1);
        VIPs.add(v2);
        management.addGalaEvent("g1", Location.BellCentre, LocalDate.of(2022, 4, 16),
                100.0, 100, VIPs);
        assertTrue(management.getHostedEvents().get(0) instanceof Gala);
    }

    @Test
    public void testAddComingGala() {
        management.addComingGala("g1", LocalDate.of(2022, 4, 16));
        assertTrue(management.getHostedEvents().get(0) instanceof Gala);
    }

    @Test
    public void testAddScreening() {
        management.addScreeningEvent("s1", Location.BellCentre, LocalDate.of(2022, 4, 16),
                100.0, 100, "R");
        assertTrue(management.getHostedEvents().get(0) instanceof Screening);
    }

    @Test
    public void testAddComingScreening() {
        management.addComingScreening("s1",LocalDate.of(2022, 4, 16));
        assertTrue(management.getHostedEvents().get(0) instanceof Screening);
    }

    @Test
    public void testAddWorkshop() {
        management.addWorkshopEvent("w1", Location.BellCentre, LocalDate.of(2022, 4, 16),
                100.0, 100, new ArrayList<Workshop>());
        assertTrue(management.getHostedEvents().get(0) instanceof Workshop);
    }

    @Test
    public void testAddComingWorkshop() {
        management.addComingWorkshop("w1", LocalDate.of(2022, 4, 16));
        assertTrue(management.getHostedEvents().get(0) instanceof Workshop);
    }

    @Test
    public void testFestival(){
        VIPs.add(v1);
        VIPs.add(v2);
        management.addConcertEvent("c1",Location.BellCentre, LocalDate.of(2022,4,19),
                100.0,10, VIPs, "artist1");
        management.addWorkshopEvent("w1", Location.BellCentre, LocalDate.of(2022, 4, 18),
                100.0, 100, new ArrayList<Workshop>());
        management.addScreeningEvent("s1", Location.BellCentre, LocalDate.of(2022, 4, 17),
                100.0, 100, "R");
        management.addGalaEvent("g1", Location.PlaceDesArts, LocalDate.of(2022, 4, 16),
                100.0, 100, VIPs);
        management.addFestival("f1",management.getHostedEvents());
        assertTrue(management.getHostedEvents().get(4) instanceof Festival);
        assertEquals(management.getHostedEvents().get(4).getLocation(),Location.Multiple);
        assertEquals(management.getHostedEvents().get(4).getDate(), LocalDate.of(2022,4,16));
        assertEquals(management.getHostedEvents().get(4).getPrice(),80.0);
        assertEquals(management.getHostedEvents().get(4).getNumTickets(),10);
        FilterResult result = management.filterByLocation(Location.BellCentre);
        double profit = management.calcProfit(result,
                0.25,0.25,0.25,0.25);
        assertEquals(profit, 5250.0);
        int vip = management.countVIP(management.filterByPrice(0,100));
        assertEquals(vip,8);
    }

    @Test
    public void testSetComingSoon(){
        management.addComingConcert("c1",LocalDate.of(2022,4,16));
        Concert c1= (Concert) management.getHostedEvents().get(0);
        assertEquals(c1.getLocation(),Location.Unknown);
        management.setLocation(c1,Location.BellCentre);
        assertEquals(c1.getLocation(),Location.BellCentre);
        management.setLocation(c1,Location.PlaceDesArts);//This will fail because location cannot be changed once been set.
        assertEquals(c1.getLocation(),Location.BellCentre);

        management.setPrice(c1,100.0);
        assertEquals(c1.getPrice(),100.0);

        management.setTickets(c1,100);
        assertEquals(c1.getNumTickets(),100);
    }
}
