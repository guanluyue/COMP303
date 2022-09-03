import java.time.LocalDate;
import java.util.ArrayList;

public class Gala extends AnEvent {
    private final ArrayList<VIP> aVIPs = new ArrayList<>();

    protected Gala(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, ArrayList<VIP> pVIPs) {
        super(pName, pLocation, pDate, pPrice, pTicket);
        aVIPs.addAll(pVIPs);
    }

    public Gala(String aName, LocalDate aDate) {
        super(aName,aDate);
    }

    public ArrayList<VIP> getVIPs(){
        return new ArrayList<>(aVIPs);
    }

    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitGala(this);
    }

}
