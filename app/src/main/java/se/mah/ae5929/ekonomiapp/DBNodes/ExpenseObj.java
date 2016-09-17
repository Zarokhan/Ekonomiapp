package se.mah.ae5929.ekonomiapp.DBNodes;

import java.util.Date;

/**
 * Created by Zarokhan on 2016-09-17.
 */
public class ExpenseObj {
    int id;
    String category;
    String mydate;
    String title;
    int price;

    public ExpenseObj(int id, String category, String mydate, String title, int price){
        this.id = id;
        this.category = category;
        this.mydate = mydate;
        this.title = title;
        this.price = price;
    }

    public int getId() {
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

    public int getPrice() {
        return price;
    }
}
