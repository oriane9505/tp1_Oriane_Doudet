package ca.uqam.mgl7230.tp1.model.passenger;

public abstract class Passenger {

    private final String passport;
    private final String name;
    private final int age;
    private final int millagePoints;

    protected Passenger(String passport, String name, int age, int millagePoints) {
        this.passport = passport;
        this.name = name;
        this.age = age;
        this.millagePoints = millagePoints;
    }

    public abstract PassengerClass getType();

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMillagePoints() {
        return millagePoints;
    }
}
