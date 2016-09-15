package se.mah.ae5929.ekonomiapp.Utility;

/**
 * Created by Zarokhan on 2016-09-15.
 */
public class MyDB {
    private static MyDB instance = null;

    protected MyDB(){}

    public static MyDB getInstance(){
        if(instance == null){
            instance = new MyDB();
        }
        return instance;
    }


}
