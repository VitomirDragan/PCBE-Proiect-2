public class DateFilter extends Filter {

    private Date datePublished;
    private Date endDatePublished;

    public DateFilter(Date datePublished, Date endDatePublished)
    {
        this.datePublished = datePublished;
        this.endDatePublished = endDatePublished;
    }

    @Override
    public boolean checkFilter(News news) {
        boolean result =false;

        if((news.getDatePublished().getDay() >= datePublished.getDay() ||
            news.getDatePublished().getMonth() >= datePublished.getMonth() ||
            news.getDatePublished().getYear() >= datePublished.getYear() )&&
            (news.getDatePublished().getDay() <= endDatePublished.getDay() ||
            news.getDatePublished().getMonth() <= endDatePublished.getMonth() ||
            news.getDatePublished().getYear() <= endDatePublished.getYear()))

            result = true;


        return result;
    }
}
