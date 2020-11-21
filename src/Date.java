public class Date {

    private int day;
    private int month;
    private int year;
    public Date(int d, int m, int y)
    {
        day=d;
        month=m;
        year=y;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean equals(Object o)
    {
        if(o instanceof Date)
        {
            if(((Date) o).getDay() == this.getDay() && ((Date) o).getMonth() == this.getMonth()&& ((Date) o).getYear() == this.getYear())
                return true;
            else
                return false;
        }
        return false;
    }

}
