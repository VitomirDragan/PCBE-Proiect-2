public class News {

    private int idNews;
    private String content;
    private Date datePublished;
    private Date dateLastEdited;
    private String autor;

    public News(int id, String content, Date datePublished, Date dateLastEdited, String autor) {
        this.idNews = id;
        this.content = content;
        this.datePublished = datePublished;
        this.dateLastEdited = dateLastEdited;
        this.autor = autor;
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
