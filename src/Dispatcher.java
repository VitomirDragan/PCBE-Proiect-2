import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Dispatcher implements Runnable {

    private Queue<Event> eventQueue;
    private HashMap<String, ArrayList<Actor>> subscribedReaders = new HashMap<String, ArrayList<Actor>>();
    private HashMap<String, ArrayList<Subscription>> eventSubscriptions = new HashMap<String, ArrayList<Subscription>>();

    public void run()
    {
        while(true){
            processNextEvent();
        }
    }

    public void processNextEvent(){
        try {
            Event event = eventQueue.remove();
        }
        catch(NoSuchElementException exec){
            System.out.println(exec);
        }

        Event e = eventQueue.remove();
        ArrayList<Subscription> subscriptions = eventSubscriptions.get(e.getEventType());
        for(Subscription subscription : subscriptions){
            subscription.getActor().myNotify(e);
        }
    }

    public void postEvent(Event e)
    {
        eventQueue.add(e);
    }

    public void acceptEventSubscription(String domainType, Actor actor)
    {
        for(Event e : eventQueue) {
            if (e.getEventType().equals(domainType))
            {
                ArrayList<Actor> readers = subscribedReaders.get(domainType);
                readers.add(actor);
                subscribedReaders.put(domainType, readers);
            }
        }
    }

    public void acceptDomainSubscription(String domain, Actor actor, Filter filter)
    {
        Subscription subscription = new Subscription(actor, domain, filter);
        ArrayList<Subscription> subscriptions = eventSubscriptions.get()
    }

}
