package se.mah.ae5929.ekonomiapp.DBNodes;

/**
 * Created by Zarokhan on 2016-09-17.
 * Database node for expense table
 */
public class ExpenseObj {

    private int id;
    private int price;
    private String category;
    private String date;
    private String title;

    public ExpenseObj(int id, String category, String date, String title, int price){
        this.id = id;
        this.category = category;
        this.date = date;
        this.title = title;
        this.price = price;
    }

    public int getId() {
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

    public int getPrice() {
        return price;
    }
}
