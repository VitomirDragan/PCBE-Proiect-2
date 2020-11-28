import java.util.HashMap;
public class Editor extends Actor{

    HashMap<News, Integer>  readNewsCounter = new HashMap<News, Integer>();
    public Editor(String numeActor, Dispatcher dispatcher) {
        super(numeActor, dispatcher);
    }

    public void myNotify(Event e)
    {
       if(e.getEventType() == Main.readNewsEvent) {
           if(readNewsCounter.get(e.getCurrentNews()) == null){
               readNewsCounter.put(e.getCurrentNews(), 1);
           }else {
               readNewsCounter.put(e.getCurrentNews(), readNewsCounter.get(e.getCurrentNews()) + 1);
           }
           System.out.println(e.getEventType() + " - " + e.getCurrentNews().getContent() + " - " + readNewsCounter.get(e.getCurrentNews()));
       }
    }

    public void addNews(News n)
    {
        Event e = new Event(Main.addNewsEvent, n);
        //System.out.println("Adding " + e.getEventType() + " - " + e.getCurrentNews().getContent());
        readNewsCounter.put(e.getCurrentNews(), 0);
        dispatcher.postEvent(e);

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
