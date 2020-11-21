import java.util.ArrayList;

public class Reader extends Actor{

    public ArrayList<News> newsPrefered = new ArrayList<News>();
    public Reader(String numeActor, Dispatcher dispatcher) {
        super(numeActor, dispatcher);
    }

    public void myNotify(Event e) {
        if (e.getEventType() == Main.addNewsEvent) {
            Event eReceived = new Event(Main.readNewsEvent, e.getCurrentNews());
            dispatcher.postEvent(eReceived);
            newsPrefered.add(e.getCurrentNews());
        }

        if (e.getEventType() == Main.eraseNewsEvent) {
            //exception!!!!!!!!!!!!!Dragaaan help
            newsPrefered.remove(e.getCurrentNews());
        }

        if (e.getEventType() == Main.modifyNewsEvent) {
            //exception!!!!!!!!!!!!!
            for(News n : newsPrefered)
            {
                if(((ModifyEvent)e).getOldNews().equals(n))
                    n.editNews(e.getCurrentNews());

            }
        }
    }
}