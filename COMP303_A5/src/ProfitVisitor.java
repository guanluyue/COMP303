public class ProfitVisitor extends Visitor{
    private double perConcert;
    private double perGala;
    private double perScreening;
    private double perWorkshop;
    private double profit=0;

    public ProfitVisitor(double concert, double workshop, double gala, double screening){
        perConcert=concert;
        perWorkshop=workshop;
        perGala=gala;
        perScreening=screening;
    }

    @Override
    public void visitConcert(Concert pConcert){
        profit+=pConcert.getPrice()*perConcert*pConcert.getNumTickets();
    }

    @Override
    public void visitGala(Gala pGala){
        profit+=pGala.getPrice()*perGala*pGala.getNumTickets();
    }

    @Override
    public void visitScreening(Screening pScreening){
        profit+=pScreening.getPrice()*perScreening*pScreening.getNumTickets();
    }

    @Override
    public void visitWorkshop(Workshop pWorkshop){
        profit+=pWorkshop.getPrice()*perWorkshop*pWorkshop.getNumTickets();
    }

    public double getProfit(){
        return profit;
    }
}
