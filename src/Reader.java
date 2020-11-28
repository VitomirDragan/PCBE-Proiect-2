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
            System.out.println("Received " + e.getEventType() + " - " + e.getCurrentNews().getContent());
        }

        if (e.getEventType() == Main.eraseNewsEvent) {
            //exception!!!!!!!!!!!!!Dragaaan help
            newsPrefered.remove(e.getCurrentNews());
            System.out.println("Deleted " + e.getEventType() + " - " + e.getCurrentNews().getContent());
        }

        if (e.getEventType() == Main.modifyNewsEvent) {
            //exception!!!!!!!!!!!!!
            for(News n : newsPrefered)
            {
                if(((ModifyEvent)e).getOldNews().equals(n)) {
                    n.editNews(e.getCurrentNews());
                    System.out.println("Modified " + e.getEventType() + " - " + e.getCurrentNews().getContent() + " - " + ((ModifyEvent) e).getOldNews().getContent());
                }

            }
        }
    }
}