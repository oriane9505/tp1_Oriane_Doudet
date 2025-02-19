package ca.uqam.mgl7230.tp1.adapter.plane;

import ca.uqam.mgl7230.tp1.model.plane.PlaneInformation;
import ca.uqam.mgl7230.tp1.model.plane.PlaneType;

import java.util.EnumMap;
import java.util.Map;

public class PlaneCatalogImpl implements PlaneCatalog {

    private final Map<PlaneType, PlaneInformation> planeMap = new EnumMap<>(PlaneType.class);

    public PlaneCatalogImpl() {
        planeMap.put(PlaneType.BOEING, new PlaneInformation(2, 5, 12));
        planeMap.put(PlaneType.AIRBUS, new PlaneInformation(1, 1, 9));
        planeMap.put(PlaneType.EMBRAER, new PlaneInformation(0, 1, 4));
        planeMap.put(PlaneType.BOMBARDIER, new PlaneInformation(0, 2, 5));
    }

    @Override
    public int getNumberSeatsFirstClass(PlaneType planeType) {
        return planeMap.get(planeType).numberSeatsFirstClass();
    }

    @Override
    public int getNumberSeatsBusinessClass(PlaneType planeType) {
        return planeMap.get(planeType).numberSeatsBusinessClass();
    }

    @Override
    public int getNumberSeatsEconomyClass(PlaneType planeType) {
        return planeMap.get(planeType).numberSeatsEconomyClass();
    }

    @Override
    public int getNumberOfTotalSeats(PlaneType planeType) {
        return planeMap.get(planeType).numberSeatsFirstClass() +
                planeMap.get(planeType).numberSeatsBusinessClass() +
                planeMap.get(planeType).numberSeatsEconomyClass();
    }
}
