public class ModifyEvent extends Event{

    private News oldNews;

    public ModifyEvent(String eventType, News currentNews, News oldNews) {
        super(eventType, currentNews);
        this.oldNews = oldNews;
    }

    public News getOldNews() {
        return oldNews;
    }
}

