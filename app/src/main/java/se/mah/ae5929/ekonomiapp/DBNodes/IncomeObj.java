package se.mah.ae5929.ekonomiapp.DBNodes;

/**
 * Created by Zarokhan on 2016-09-17.
 * Database node for income table
 */
public class IncomeObj {
    int id;
    String category;
    String mydate;
    String title;
    int amount;

    public IncomeObj(int id, String category, String mydate, String title, int amount){
        this.id = id;
        this.category = category;
        this.mydate = mydate;
        this.title = title;
        this.amount = amount;
    }

    public int getId(){
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getMydate() {
        return mydate;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }
}
