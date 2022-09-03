import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisitorTest {
    private EventManagement management=new EventManagement();
    private Concert concert=new Concert("c1", LocalDate.now());
    private Gala gala=new Gala("g1", LocalDate.now());
    private Screening screening=new Screening("s1", LocalDate.now());
    private Workshop workshop=new Workshop("w1", LocalDate.now());

    private static class StubVisitor extends Visitor{
        private boolean visited=false;
        private int EventsVisited=0;
        public boolean isVisited() {
            return visited;
        }
        public int getEventsVisited(){
            return EventsVisited;
        }
        @Override
        public void visitConcert(Concert concert){
            visited=true;
            EventsVisited++;
        }
        @Override
        public void visitGala(Gala gala){
            visited=true;
            EventsVisited++;
        }
        @Override
        public void visitScreening(Screening screening){
            visited=true;
            EventsVisited++;
        }
        @Override
        public void visitWorkshop(Workshop workshop){
            visited=true;
            EventsVisited++;
        }



    }

    @Test
    public void testVisitConcert(){
        StubVisitor visitor=new StubVisitor();
        visitor.visitConcert(concert);
        assertTrue(visitor.isVisited());
    }
    @Test
    public void testVisitGala(){
        StubVisitor visitor=new StubVisitor();
        visitor.visitGala(gala);
        assertTrue(visitor.isVisited());
    }
    @Test
    public void testVisitScreening(){
        StubVisitor visitor=new StubVisitor();
        visitor.visitScreening(screening);
        assertTrue(visitor.isVisited());
    }
    @Test
    public void testVisitWorkshop(){
        StubVisitor visitor=new StubVisitor();
        visitor.visitWorkshop(workshop);
        assertTrue(visitor.isVisited());
    }

    @Test
    public void testVisitFestival(){
        StubVisitor visitor=new StubVisitor();
        ArrayList<AnEvent> events=new ArrayList<>();
        management.addComingConcert("c1",LocalDate.now());
        management.addComingGala("g1",LocalDate.now());
        management.addComingScreening("s1",LocalDate.now());
        management.addComingWorkshop("w1",LocalDate.now());
        Festival f=new Festival("f",management.getHostedEvents());
        visitor.visitFestival(f);
        assertEquals(4,visitor.getEventsVisited());
    }

}
