package se.mah.ae5929.ekonomiapp.Utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import se.mah.ae5929.ekonomiapp.DBNodes.ExpenseObj;
import se.mah.ae5929.ekonomiapp.DBNodes.IncomeObj;
import se.mah.ae5929.ekonomiapp.R;

/**
 * Created by Zarokhan on 2016-09-15.
 */
public class MyDB extends SQLiteOpenHelper {

    private static MyDB instance;

    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;

    Context context;

    public static synchronized MyDB getInstance(Context context){
        if(instance == null){
            instance = new MyDB(context);
        }
        return instance;
    }

    private MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Read ekonomi_db sql resource file
        try {
            insertFromFile(db, R.raw.ekonomi_db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            // Read ekonomi_db_remove sql resource file
            try {
                insertFromFile(db, R.raw.ekonomi_db_remove);
            } catch (IOException e) {
                e.printStackTrace();
            }

            onCreate(db);
        }
    }

    // Reads sql file from resources
    private int insertFromFile(SQLiteDatabase db, int resourceId) throws IOException {
        int counter = 0;
        String sql = "";

        // Read sql file
        InputStream istream = context.getResources().openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(istream));

        // iterate through lines
        while(reader.ready()){
            String line = reader.readLine();
            sql += line;
            counter++;
        }

        reader.close();
        istream.close();

        // Format SQLite correctly
        String[] removet = sql.split("\\t");
        sql = "";
        for(int i = 0; i < removet.length; ++i){
            sql += removet[i];
        }
        String[] queries = sql.split(";");

        // Execute sql queries
        for(int i = 0; i < queries.length; ++i){
            db.execSQL(queries[i]);
        }

        return counter;
    }

    // Get total income
    public int getTotalIncome(int hashid){
        int amount = 0;
        int idIndex, hashidIndex, categoryIndex, dateIndex, titleIndex, amountIndex;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM incomes WHERE hashid=" + hashid + ";", null);
        idIndex = c.getColumnIndex("id");
        hashidIndex = c.getColumnIndex("hashid");
        categoryIndex = c.getColumnIndex("mydate");
        titleIndex = c.getColumnIndex("title");
        amountIndex = c.getColumnIndex("amount");

        for(int i = 0; i < c.getCount(); ++i){
            c.moveToPosition(i);
            amount += c.getInt(amountIndex);
        }
        return amount;
    }

    // Get total expense
    public int getTotalExpenses(int hashid){
        int price = 0;
        int priceIndex;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM expenses WHERE hashid=" + hashid + ";", null);
        priceIndex = c.getColumnIndex("price");

        for(int i = 0; i < c.getCount(); ++i){
            c.moveToPosition(i);
            price += c.getInt(priceIndex);
        }
        return price;
    }

    // Get all income categories
    public String[] getIncomeCategories(){
        List<String> cats = new ArrayList<String>();
        int titleIndex, removableIndex;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM icategory", null);
        titleIndex = c.getColumnIndex("title");
        removableIndex = c.getColumnIndex("removable");

        for(int i = 0; i < c.getCount(); ++i){
            c.moveToPosition(i);
            cats.add(c.getString(titleIndex));
        }

        String[] stringarray = new String[cats.size()];
        for(int i = 0; i < stringarray.length; ++i){
            stringarray[i] = cats.get(i);
        }

        return stringarray;
    }

    // Get all expense categories
    public String[] getExpenseCategories(){
        List<String> cats = new ArrayList<String>();
        int titleIndex, removableIndex;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ecategory", null);
        titleIndex = c.getColumnIndex("title");
        removableIndex = c.getColumnIndex("removable");

        for(int i = 0; i < c.getCount(); ++i){
            c.moveToPosition(i);
            cats.add(c.getString(titleIndex));
        }

        String[] stringarray = new String[cats.size()];
        for(int i = 0; i < stringarray.length; ++i){
            stringarray[i] = cats.get(i);
        }

        return stringarray;
    }

    public IncomeObj[] getIncomeFromCategory(String category, int hashid){
        List<IncomeObj> objs = new ArrayList<IncomeObj>();
        int idIndex, catIndex, dateIndex, titleIndex, amountIndex;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM incomes WHERE category='" + category + "' AND hashid=" + hashid, null);
        idIndex = c.getColumnIndex("id");
        catIndex = c.getColumnIndex("category");
        dateIndex = c.getColumnIndex("mydate");
        titleIndex = c.getColumnIndex("title");
        amountIndex = c.getColumnIndex("amount");

        for(int i = 0; i < c.getCount(); ++i){
            c.moveToPosition(i);
            objs.add(new IncomeObj(c.getInt(idIndex), c.getString(catIndex), c.getString(dateIndex), c.getString(titleIndex), c.getInt(amountIndex)));
        }

        IncomeObj[] objsarray = new IncomeObj[objs.size()];
        for(int i = 0; i < objsarray.length; ++i)
            objsarray[i] = objs.get(i);

        return objsarray;
    }

    public ExpenseObj[] getExpenseFromCategory(String category, int hashid){
        List<ExpenseObj> objs = new ArrayList<ExpenseObj>();
        int idIndex, catIndex, dateIndex, titleIndex, priceIndex;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM expenses WHERE category='" + category + "' AND hashid=" + hashid, null);
        idIndex = c.getColumnIndex("id");
        catIndex = c.getColumnIndex("category");
        dateIndex = c.getColumnIndex("mydate");
        titleIndex = c.getColumnIndex("title");
        priceIndex = c.getColumnIndex("price");

        for(int i = 0; i < c.getCount(); ++i){
            c.moveToPosition(i);
            objs.add(new ExpenseObj(c.getInt(idIndex), c.getString(catIndex), c.getString(dateIndex), c.getString(titleIndex), c.getInt(priceIndex)));
        }

        ExpenseObj[] objsarray = new ExpenseObj[objs.size()];
        for(int i = 0; i < objsarray.length; ++i)
            objsarray[i] = objs.get(i);

        return objsarray;
    }
}
