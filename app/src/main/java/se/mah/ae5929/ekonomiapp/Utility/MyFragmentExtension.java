package se.mah.ae5929.ekonomiapp.Utility;

import android.support.v4.app.Fragment;

import se.mah.ae5929.ekonomiapp.Controller;

/**
 * Created by Zarokhan on 2016-09-13.
 */
public class MyFragmentExtension extends Fragment {

    protected Controller controller;

    public void setController(Controller controller){
        this.controller = controller;
    }
}
