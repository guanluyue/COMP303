import java.time.LocalDate;

public class Screening extends AnEvent{
    private String aRating;

    protected Screening(String pName, Location pLocation, LocalDate pDate, Double pPrice, Integer pTicket, String pLabel) {
        super(pName, pLocation, pDate, pPrice, pTicket);
        for (Rating rating: Rating.values()){
            if (rating.label == pLabel) {
                aRating = pLabel;
                break;
            }
        }
    }

    public Screening(String aName, LocalDate aDate) {
        super(aName,aDate);
    }

    public String getRating(){
        return aRating;
    }

    @Override
    public void accept(Visitor pVisitor) {
        pVisitor.visitScreening(this);
    }


}
