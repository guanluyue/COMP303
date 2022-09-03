public class PriceVisitor extends Visitor{
    private double aPrice = 0;

    @Override
    public void visitEvent(AnEvent pEvent){
        if (pEvent.getPrice()>=0) {
            aPrice += pEvent.getPrice();
        }
    }
    @Override
    void visitConcert(Concert pConcert){
        if (pConcert.getPrice()>=0) {
            aPrice += pConcert.getPrice();
        }
    }
    @Override
    void visitGala(Gala pGala){
        if (pGala.getPrice()>=0) {
            aPrice += pGala.getPrice();
        }
    }
    @Override
    void visitScreening(Screening pScreening){
        if (pScreening.getPrice()>=0) {
            aPrice += pScreening.getPrice();
        }
    }
    @Override
    void visitWorkshop(Workshop pWorkshop){
        if (pWorkshop.getPrice()>=0) {
            aPrice += pWorkshop.getPrice();
        }
    }


    public double getPrice(){
        return aPrice * 0.2;
    }
}
