import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class DateVisitor extends Visitor {
    private LocalDate aDate = LocalDate.MAX;

    void visitConcert(Concert pConcert){
        if (pConcert.getDate().isBefore(aDate)){
            aDate=pConcert.getDate();
        }
    }
    void visitGala(Gala pGala){
        if (pGala.getDate().isBefore(aDate)){
            aDate=pGala.getDate();
        }
    }
    void visitScreening(Screening pScreening){
        if (pScreening.getDate().isBefore(aDate)){
            aDate=pScreening.getDate();
        }
    }
    void visitWorkshop(Workshop pWorkshop){
        if (pWorkshop.getDate().isBefore(aDate)){
            aDate=pWorkshop.getDate();
        }
    }

    public LocalDate getDate(){
        return aDate;
    }

}
