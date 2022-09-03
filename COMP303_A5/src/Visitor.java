public abstract class Visitor {
    void visitFestival(Festival pFestival){
        for (AnEvent event: pFestival){
            event.accept(this);
        }
    }

    void visitFilterResult(FilterResult result){
        for (AnEvent event: result){
            event.accept(this);
        }
    }
    void visitEvent(AnEvent pEvent){}
    void visitConcert(Concert pConcert){}
    void visitGala(Gala pGala){}
    void visitScreening(Screening pScreening){}
    void visitWorkshop(Workshop pWorkshop){}

}
