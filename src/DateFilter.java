public class DateFilter extends Filter {

    private Date datePublished;
    private Date endDatePublished;

    public DateFilter(Date datePublished, Date endDatePublished) {
        this.datePublished = datePublished;
        this.endDatePublished = endDatePublished;
    }

    @Override
    public boolean checkFilter(News news) {
        boolean result = false;

        if (news.getDatePublished().getYear() >= datePublished.getYear() &&
                news.getDatePublished().getYear() <= endDatePublished.getYear() ||
                (
                        news.getDatePublished().getYear() == datePublished.getYear() &&
                                news.getDatePublished().getYear() == endDatePublished.getYear() &&
                                news.getDatePublished().getMonth() >= datePublished.getMonth() &&
                                news.getDatePublished().getMonth() <= endDatePublished.getMonth() ||
                                (
                                        news.getDatePublished().getMonth() == datePublished.getMonth() &&
                                                news.getDatePublished().getMonth() == endDatePublished.getMonth() &&
                                                news.getDatePublished().getDay() >= datePublished.getDay() &&
                                                news.getDatePublished().getDay() <= endDatePublished.getDay()
                                )
                )
        )

            result = true;


        return result;
    }
}
