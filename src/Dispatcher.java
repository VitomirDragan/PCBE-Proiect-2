import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Dispatcher implements Runnable {

    private Queue<Event> eventQueue;
    private HashMap<String, ArrayList<Subscription>> eventSubscriptions = new HashMap<String, ArrayList<Subscription>>();

    public void run() {
        while (true) {
            processNextEvent();
        }
    }

    public void processNextEvent() {
        try {
            Event e = eventQueue.remove();
            ArrayList<Subscription> subscriptions = eventSubscriptions.get(e.getEventType());
            for (Subscription subscription : subscriptions) {
                int i = 0;
                for (Filter f : subscription.getFilter()) {
                    if (!f.checkFilter(e.getCurrentNews()))
                        i++;
                }
                if (i == 0 && subscription.getDomain().equals(e.getCurrentNews().getDomain()))
                    subscription.getActor().myNotify(e);
            }
        } catch (NoSuchElementException exec) {
            System.out.println(exec);
        }

    }

    public void postEvent(Event e) {
        eventQueue.add(e);
    }

    public void acceptEventSubscription(String eventType, Actor actor, String domainType, ArrayList<Filter> filter) {
        Subscription subscription = new Subscription(actor, domainType, filter);
        ArrayList<Subscription> subscriptions = eventSubscriptions.get(eventType);
        subscriptions.add(subscription);
    }
}
