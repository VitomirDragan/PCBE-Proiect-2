import java.util.*;

public class Dispatcher implements Runnable {
    Synchronize synchronize = new Synchronize();

    private Queue<Event> eventQueue = new LinkedList<Event>();
    private HashMap<String, ArrayList<Subscription>> eventSubscriptions = new HashMap<String, ArrayList<Subscription>>();

    public Dispatcher()
    {
        eventSubscriptions.put(Main.addNewsEvent, new ArrayList<Subscription>());
        eventSubscriptions.put(Main.modifyNewsEvent, new ArrayList<Subscription>());
        eventSubscriptions.put(Main.eraseNewsEvent, new ArrayList<Subscription>());
        eventSubscriptions.put(Main.readNewsEvent, new ArrayList<Subscription>());
    }

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
            System.out.println("Processing " + e.getEventType() + " - " + e.getCurrentNews().getContent());

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
            synchronize.unlockEventQueue();
        }

    }

    public void postEvent(Event e) {
        //System.out.println("Posting " + e.getEventType() + " - " + e.getCurrentNews().getContent());
        synchronize.lockEventQueue();
        eventQueue.add(e);
        synchronize.unlockEventQueue();
        //System.out.println("Posted " + e.getEventType() + " - " + e.getCurrentNews().getContent());
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
