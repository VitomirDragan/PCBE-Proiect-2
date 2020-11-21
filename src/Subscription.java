public class Subscription {
    private Actor actor;
    private String domain;
    private Filter filter;

    public Subscription(Actor actor, String domain, Filter filter) {
        this.actor = actor;
        this.domain = domain;
        this.filter = filter;
    }

    public Actor getActor() {
        return actor;
    }

    public String getDomain() {
        return domain;
    }

    public Filter getFilter() {
        return filter;
    }
}
