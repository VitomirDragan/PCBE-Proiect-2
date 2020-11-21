public class Event {

    private String eventType;
    private News currentNews;


    public Event(String eventType, News currentNews) {
        this.eventType = eventType;
        this.currentNews = currentNews;
    }

    public String getEventType() {
        return eventType;
    }

    public News getCurrentNews() {
        return currentNews;
    }
}
