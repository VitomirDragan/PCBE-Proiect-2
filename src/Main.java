import java.util.ArrayList;

public class Main {

    static final String addNewsEvent = "addNews";
    static final String eraseNewsEvent = "eraseNews";
    static final String modifyNewsEvent = "modifyNews";
    static final String readNewsEvent = "readNews";

    static final String domainHealthCare = "HealthCare";
    static final String domainFitness = "Fitness";
    static final String domainPolitics = "Politics";
    static final String domainSport = "Sport";
    static final String domainHoroscope = "Horoscope";



    public static void main(String[] args)
    {
        Dispatcher dispatcher = new Dispatcher();
        Thread dispatcherThread = new Thread(dispatcher);
        dispatcherThread.start();
        
        Editor e1 = new Editor("Gigel", dispatcher);
        Editor e2 = new Editor("Maria", dispatcher);
        Editor e3 = new Editor("Cosmin", dispatcher);
        e1.start();
        e2.start();
        e3.start();

        e1.subscribeToEvent(readNewsEvent, domainFitness, new ArrayList<>());
        e1.subscribeToEvent(readNewsEvent, domainPolitics, new ArrayList<>());
        e2.subscribeToEvent(readNewsEvent, domainHoroscope, new ArrayList<>());
        e2.subscribeToEvent(readNewsEvent, domainHealthCare, new ArrayList<>());
        e3.subscribeToEvent(readNewsEvent, domainSport, new ArrayList<>());

        Reader claudia = new Reader("Claudia", dispatcher);
        Reader lorena = new Reader("Lorena", dispatcher);
        Reader dragan = new Reader("Dragan", dispatcher);
        Reader harris = new Reader("Harris", dispatcher);
        claudia.start();
        lorena.start();
        dragan.start();
        harris.start();

        ArrayList<Filter> filersClaudia_Fitness = new ArrayList<>();
        filersClaudia_Fitness.add(new DateFilter(new Date(20, 10, 2020), new Date(28, 11, 2020)));
        filersClaudia_Fitness.add(new EditorFilter(e1));
        claudia.subscribeToEvent(addNewsEvent, domainFitness, filersClaudia_Fitness);
        claudia.subscribeToEvent(modifyNewsEvent, domainFitness, filersClaudia_Fitness);
        claudia.subscribeToEvent(eraseNewsEvent, domainFitness, filersClaudia_Fitness);

        ArrayList<Filter> filersClaudia_Horoscope = new ArrayList<>();
        filersClaudia_Horoscope.add(new DateFilter(new Date(12, 10, 2020), new Date(12, 11, 2020)));
        filersClaudia_Horoscope.add(new EditorFilter(e2));
        claudia.subscribeToEvent(addNewsEvent, domainHoroscope, filersClaudia_Horoscope);
        claudia.subscribeToEvent(modifyNewsEvent, domainHoroscope, filersClaudia_Horoscope);
        claudia.subscribeToEvent(eraseNewsEvent, domainHoroscope, filersClaudia_Horoscope);

        ArrayList<Filter> filersLorena_HealthCare = new ArrayList<>();
        filersLorena_HealthCare.add(new DateFilter(new Date(12, 10, 2020), new Date(12, 11, 2020)));
        filersLorena_HealthCare.add(new EditorFilter(e3));
        lorena.subscribeToEvent(addNewsEvent, domainHealthCare, filersLorena_HealthCare);
        lorena.subscribeToEvent(modifyNewsEvent, domainHealthCare, filersLorena_HealthCare);
        lorena.subscribeToEvent(eraseNewsEvent, domainHealthCare, filersLorena_HealthCare);

        ArrayList<Filter> filersLorena_Fitness = new ArrayList<>();
        filersLorena_Fitness.add(new DateFilter(new Date(12, 10, 2020), new Date(12, 11, 2020)));
        filersLorena_Fitness.add(new EditorFilter(e1));
        lorena.subscribeToEvent(addNewsEvent, domainFitness, filersLorena_Fitness);
        lorena.subscribeToEvent(modifyNewsEvent, domainFitness, filersLorena_Fitness);
        lorena.subscribeToEvent(eraseNewsEvent, domainFitness, filersLorena_Fitness);

        ArrayList<Filter> filersDragan_Politics = new ArrayList<>();
        filersDragan_Politics.add(new DateFilter(new Date(12, 10, 2020), new Date(12, 11, 2020)));
        filersDragan_Politics.add(new EditorFilter(e1));
        dragan.subscribeToEvent(addNewsEvent, domainPolitics, filersDragan_Politics);
        dragan.subscribeToEvent(modifyNewsEvent, domainPolitics, filersDragan_Politics);
        dragan.subscribeToEvent(eraseNewsEvent, domainPolitics, filersDragan_Politics);

        ArrayList<Filter> filersDragan_Sport = new ArrayList<>();
        filersDragan_Sport.add(new DateFilter(new Date(12, 10, 2020), new Date(12, 11, 2020)));
        filersDragan_Sport.add(new EditorFilter(e1));
        dragan.subscribeToEvent(addNewsEvent, domainSport, filersDragan_Sport);
        dragan.subscribeToEvent(modifyNewsEvent, domainSport, filersDragan_Sport);
        dragan.subscribeToEvent(eraseNewsEvent, domainSport, filersDragan_Sport);

        ArrayList<Filter> filersDragan_HealthCare = new ArrayList<>();
        filersDragan_HealthCare.add(new DateFilter(new Date(12, 10, 2020), new Date(12, 11, 2020)));
        filersDragan_HealthCare.add(new EditorFilter(e2));
        dragan.subscribeToEvent(addNewsEvent, domainHealthCare, filersDragan_HealthCare);
        dragan.subscribeToEvent(modifyNewsEvent, domainHealthCare, filersDragan_HealthCare);
        dragan.subscribeToEvent(eraseNewsEvent, domainHealthCare, filersDragan_HealthCare);

        ArrayList<Filter> filersHarris_Sport = new ArrayList<>();
        filersHarris_Sport.add(new DateFilter(new Date(12, 10, 2020), new Date(12, 11, 2020)));
        filersHarris_Sport.add(new EditorFilter(e2));
        harris.subscribeToEvent(addNewsEvent, domainSport, filersHarris_Sport);
        harris.subscribeToEvent(modifyNewsEvent, domainSport, filersHarris_Sport);
        harris.subscribeToEvent(eraseNewsEvent, domainSport, filersHarris_Sport);

        ArrayList<Filter> filersHarris_HealthCare = new ArrayList<>();
        filersHarris_HealthCare.add(new EditorFilter(e2));
        harris.subscribeToEvent(addNewsEvent, domainHealthCare, filersHarris_HealthCare);
        harris.subscribeToEvent(modifyNewsEvent, domainHealthCare, filersHarris_HealthCare);
        harris.subscribeToEvent(eraseNewsEvent, domainHealthCare, filersHarris_HealthCare);



        News n11 = new News(1, "S-a deschis o noua scoala de machiaj", new Date(15, 10, 2020), e2.getName(), domainHealthCare);
        News n12 = new News(2, "Noul tratament pentru tratarea acneei este un succes", new Date(20, 10, 2020), e2.getName(), domainHealthCare);
        News n21 = new News(3, "10 minute de cardio pentru imbunatatirea tonusului muscular", new Date(15, 2, 2020), e2.getName(), domainFitness);
        News n22 = new News(4, "Intermittent fasting recipe", new Date(15, 10, 2019), e2.getName(), domainFitness);
        News n31 = new News(5, "Fritz a inchis gradina zoologica pentru reamenagare", new Date(8, 9, 2019), e1.getName(), domainPolitics);
        News n32 = new News(6, "Alegerile parlamentare vor avea un mare impact asupra epidemiei Covid", new Date(7, 8, 2019), e3.getName(), domainPolitics);
        News n4 = new News(7, "Antrenamentele in sali s-au sistat", new Date(15, 2, 2020), e1.getName(), domainSport);
        News n5 = new News(8, "Ghinion pentru zodia maimuta", new Date(15, 2, 2020), e2.getName(), domainHoroscope);


        e2.addNews(n11);
        e2.addNews(n12);
        e2.addNews(n21);
        e2.modifyNews(n22, n21);
        e1.addNews(n31);
        e3.addNews(n32);
        e1.addNews(n4);
        e2.addNews(n5);

        e3.eraseNews(n32);
    }
}
