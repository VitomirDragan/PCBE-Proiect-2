import java.util.ArrayList;

public abstract class Actor extends Thread {
    private String numeActor;
    protected Dispatcher dispatcher;

    public Actor(String numeActor, Dispatcher dispatcher) {
        this.numeActor = numeActor;
        this.dispatcher = dispatcher;
    }

    public void subscribeToEvent(String eventType, String domain, ArrayList<Filter> filters) {
        dispatcher.acceptEventSubscription(eventType, this, domain, filters);
    }


    public void generateEvent(Event e) {
        dispatcher.postEvent(e);
    }

    public abstract void myNotify(Event e);
}
