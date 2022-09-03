import java.util.ArrayList;

public class LocationVisitor extends Visitor{
    private final ArrayList<Location> aLocations = new ArrayList<>();
    private Location aLocation=Location.Unknown;

    @Override
    public void visitEvent(AnEvent pEvent){
        Location location = pEvent.getLocation();
        if (pEvent.getLocation()!=Location.Unknown) {
            if (!aLocations.contains(location)) {
                aLocations.add(location);
            }
        }
    }

    void visitConcert(Concert pConcert) {
        Location location = pConcert.getLocation();
        if (pConcert.getLocation() != Location.Unknown) {
            if (!aLocations.contains(location)) {
                aLocations.add(location);
            }
        }
    }
    void visitGala(Gala pGala) {
        Location location = pGala.getLocation();
        if (pGala.getLocation() != Location.Unknown) {
            if (!aLocations.contains(location)) {
                aLocations.add(location);
            }
        }
    }
    void visitScreening(Screening pScreening) {
        Location location = pScreening.getLocation();
        if (pScreening.getLocation() != Location.Unknown) {
            if (!aLocations.contains(location)) {
                aLocations.add(location);
            }
        }
    }
    void visitWorkshop(Workshop pWorkshop) {
        Location location = pWorkshop.getLocation();
        if (pWorkshop.getLocation() != Location.Unknown) {
            if (!aLocations.contains(location)) {
                aLocations.add(location);
            }
        }
    }
    public Location getLocation() {
        if (aLocations.size() == 1) {
            return aLocations.get(0);
        } else {
            return Location.Multiple;
        }
    }
}
