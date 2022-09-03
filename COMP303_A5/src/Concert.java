import java.time.LocalDate;
import java.util.ArrayList;

public class Concert extends AnEvent {
    private String aArtist;
    private final ArrayList<VIP> aVIPs = new ArrayList<>();

    protected Concert(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, ArrayList<VIP> pVIPs, String pArtist) {
        super(pName, pLocation, pDate, pPrice, pTicket);
        aVIPs.addAll(pVIPs);
        aArtist=pArtist;
    }

    public Concert(String aName, LocalDate aDate) {
        super(aName,aDate);
    }

    public ArrayList<VIP> getVIPs(){
        return new ArrayList<>(aVIPs);
    }

    public String getArtist(){
        return aArtist;
    }

    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitConcert(this);
    }
}
