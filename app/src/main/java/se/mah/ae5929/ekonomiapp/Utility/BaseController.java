package se.mah.ae5929.ekonomiapp.Utility;

import android.app.Activity;

/**
 * Created by Zarokhan on 2016-09-18.
 */
public class BaseController<MyActivity extends Activity> {
    protected MyActivity activity;

    public BaseController(MyActivity activity){
        this.activity = activity;
    }

    protected MyActivity getActivity(){
        return activity;
    }
}
