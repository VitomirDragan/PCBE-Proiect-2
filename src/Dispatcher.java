import java.util.*;

public class Dispatcher implements Runnable {
    Synchronize synchronize = new Synchronize();

    private Queue<Event> eventQueue = new LinkedList<Event>();
    private HashMap<String, ArrayList<Subscription>> eventSubscriptions = new HashMap<String, ArrayList<Subscription>>();

    public void run() {
        while (true) {
            processNextEvent();
        }
    }

    public void processNextEvent() {
        try {
            synchronize.lockEventQueue();
            Event e = eventQueue.remove();
            synchronize.unlockEventQueue();

            synchronize.startReadEventSubscription();
            ArrayList<Subscription> subscriptions = new ArrayList<>(eventSubscriptions.get(e.getEventType()));
            synchronize.endReadEventSubscriptions();

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
        synchronize.lockEventQueue();
        eventQueue.add(e);
        synchronize.unlockEventQueue();
    }

    public void acceptEventSubscription(String eventType, Actor actor, String domainType, ArrayList<Filter> filter) {
        Subscription subscription = new Subscription(actor, domainType, filter);

        synchronize.startReadEventSubscription();
        ArrayList<Subscription> subscriptions = eventSubscriptions.get(eventType);
        synchronize.endReadEventSubscriptions();

        synchronize.startWriteEventSubscriptions();
        subscriptions.add(subscription);
        synchronize.endWriteEventSubscriptions();
    }
}
