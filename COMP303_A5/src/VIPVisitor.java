public class VIPVisitor extends Visitor{
    private int VIP;

    @Override
    public void visitConcert(Concert pConcert){
        VIP+=pConcert.getVIPs().size();
    }
    @Override
    public void visitGala(Gala pGala){
            VIP+=pGala.getVIPs().size();
    }

    public int getVIP(){
        return VIP;
    }
}
