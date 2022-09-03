import java.time.LocalDate;
import java.util.ArrayList;

public class Workshop extends AnEvent{
    private ArrayList<Workshop> aPrerequisites = new ArrayList<>();

    protected Workshop(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, ArrayList<Workshop> pPrerequisite) {
        super(pName, pLocation, pDate, pPrice, pTicket);
        assert pPrerequisite!=null;
        aPrerequisites.addAll(pPrerequisite);
    }

    public Workshop(String aName, LocalDate aDate) {
        super(aName,aDate);
    }

    public ArrayList<Workshop> getPrerequisites() {
        return new ArrayList<>(aPrerequisites);
    }

    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitWorkshop(this);
    }

}
