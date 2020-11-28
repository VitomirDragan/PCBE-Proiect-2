import java.util.ArrayList;

public class Subscription {
    private Actor actor;
    private String domain;
    private ArrayList<Filter> filters = new ArrayList<Filter>();

    public Subscription(Actor actor, String domain, ArrayList<Filter> filters) {

        this.actor = actor;
        this.domain = domain;
        this.filters = filters;
    }

    public Actor getActor() {
        return actor;
    }

    public String getDomain() {
        return domain;
    }

    public  ArrayList<Filter>  getFilter() {
        return filters;
    }
}
