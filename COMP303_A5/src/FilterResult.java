import java.util.ArrayList;
import java.util.Iterator;

public class FilterResult implements Iterable<AnEvent>{
    private final ArrayList<AnEvent> aFilteredEvents=new ArrayList<>();

    public void add(AnEvent pEvent){
        aFilteredEvents.add(pEvent);
    }

    public double calcProfit(double perConcert, double perWorkshop, double perGala, double perScreening){
        ProfitVisitor visitor=new ProfitVisitor(perConcert,perWorkshop,perGala,perScreening);
        visitor.visitFilterResult(this);
        return visitor.getProfit();
    }

    public int calcVIPs() {
        VIPVisitor visitor = new VIPVisitor();
        visitor.visitFilterResult(this);
        return visitor.getVIP();
    }

    @Override
    public Iterator<AnEvent> iterator() {
        return aFilteredEvents.iterator();
    }
}
