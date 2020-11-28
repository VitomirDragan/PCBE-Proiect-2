public class News {

    private int idNews;
    private String content;
    private Date datePublished;
    private Date dateLastEdited;
    private String autor;
    private String domain;

    public News(int id, String content, Date datePublished, Date dateLastEdited, String autor, String domain) {
        this.idNews = id;
        this.content = content;
        this.datePublished = datePublished;
        this.dateLastEdited = dateLastEdited;
        this.autor = autor;
        this.domain = domain;
    }

    public News(int id, String content, Date datePublished, String autor, String domain) {
        this.idNews = id;
        this.content = content;
        this.datePublished = datePublished;
        this.dateLastEdited = datePublished;
        this.autor = autor;
        this.domain = domain;
    }

    public String getContent() {
        return content;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public Date getDateLastEdited() {
        return dateLastEdited;
    }

    public String getAutor() {
        return autor;
    }

    public int getIdNews() {
        return idNews;
    }

    public  String getDomain(){return domain;}

    public void editNews(News n)
    {
        this.content = n.content;
        this.dateLastEdited = n.datePublished;
    }

    public boolean equals(Object o)
    {
        if( o instanceof News)
        {
            if(((News) o).getAutor().equals(this.getAutor())
                && ((News) o).getIdNews()== this.getIdNews())
                return true;
            else
                return false;
        }
        return false;
    }
}
