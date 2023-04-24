package app.Util;

public enum Cabin {
    Business("Business"),
    Economy("Economy"),
    First("First"),
    PremiumEconomy("Premium Economy");

    private final String displayName;

    Cabin(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return this.displayName;
    }

    @Override
    public String toString() {
        return displayName();
    }
}
