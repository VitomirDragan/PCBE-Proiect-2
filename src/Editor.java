import java.util.HashMap;
public class Editor extends Actor{

    HashMap<News, Integer>  readNewsCounter = new HashMap<News, Integer>();
    public Editor(String numeActor, Dispatcher dispatcher) {
        super(numeActor, dispatcher);
    }

    public void myNotify(Event e)
    {
       if(e.getEventType() == Main.readNewsEvent)
           readNewsCounter.put(e.getCurrentNews(), readNewsCounter.get(e.getCurrentNews()) + 1);

    }

    public void addNews(News n)
    {
        Event e = new Event(Main.addNewsEvent, n);
        dispatcher.postEvent(e);
        readNewsCounter.put(e.getCurrentNews(), 0);
    }

    public void eraseNews(News n)
    {
        Event e = new Event(Main.eraseNewsEvent, n);
        dispatcher.postEvent(e);
    }

    public void modifyNews(News n, News oldN)
    {
        Event e = new ModifyEvent(Main.modifyNewsEvent, n, oldN);
        dispatcher.postEvent(e);
    }

}
