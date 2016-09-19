package se.mah.ae5929.ekonomiapp.DBNodes;

/**
 * Created by Zarokhan on 2016-09-17.
 * Database node for income table
 */
public class IncomeObj {

    private int id;
    private String category;
    private String date;
    private String title;
    private int amount;

    public IncomeObj(int id, String category, String date, String title, int amount){
        this.id = id;
        this.category = category;
        this.date = date;
        this.title = title;
        this.amount = amount;
    }

    public int getId(){
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getVisualDate(){
        String visual = getDate().substring(0, 4) + "-" + getDate().substring(4, 6) + "-" + getDate().substring(6, 8);
        return visual;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }
}
