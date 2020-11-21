public abstract class Actor extends Thread{
    private String numeActor;
    protected Dispatcher dispatcher;

    public Actor(String numeActor, Dispatcher dispatcher) {
        this.numeActor = numeActor;
        this.dispatcher = dispatcher;
    }

    public void subscribeToEvent(String eventType)
    {
        dispatcher.acceptEventSubscription(eventType, this);
    }

    public void subscribeToDomain(String domain, Filter filter)
    {
        dispatcher.acceptDomainSubscription(domain, this, filter);
    }

    public void generateEvent(Event e)
    {
        dispatcher.postEvent(e);
    }

    public abstract void myNotify(Event e);
}
