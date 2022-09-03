public enum Rating {
    G("G"),
    PG("PG"),
    PG_THIRTEEN("PG-13"),
    R("R");
    public final String label;

    private Rating(String label) {
        this.label = label;
    }
}
