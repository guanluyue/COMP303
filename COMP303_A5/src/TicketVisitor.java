import java.util.ArrayList;
import java.util.Collections;

public class TicketVisitor extends Visitor {
    private final ArrayList<Integer> aNumbers=new ArrayList<>();

    @Override
    public void visitEvent(AnEvent pEvent) {
        if (pEvent.getNumTickets() >= 0) {
            aNumbers.add(pEvent.getNumTickets());
        }
    }

    @Override
    void visitConcert(Concert pConcert){
        if (pConcert.getNumTickets() >= 0) {
            aNumbers.add(pConcert.getNumTickets());
        }
    }
    @Override
    void visitGala(Gala pGala){
        if (pGala.getNumTickets() >= 0) {
            aNumbers.add(pGala.getNumTickets());
        }
    }
    @Override
    void visitScreening(Screening pScreening){
        if (pScreening.getNumTickets() >= 0) {
            aNumbers.add(pScreening.getNumTickets());
        }
    }
    @Override
    void visitWorkshop(Workshop pWorkshop){
        if (pWorkshop.getNumTickets() >= 0) {
            aNumbers.add(pWorkshop.getNumTickets());
        }
    }

    public Integer getTicket(){
        if (aNumbers.size()==0){
            return 0;
        }
        return Collections.min(aNumbers);
    }

}
