package se.mah.ae5929.ekonomiapp;

import se.mah.ae5929.ekonomiapp.EkonomiFragments.OverviewFragment;

/**
 * Created by Zarokhan on 2016-09-08.
 */
public class Controller {
    private EkoActivity activity;
    private LoginFragment loginFrag;
    private OverviewFragment overviewFragment;

    private int hashid;

    public Controller(EkoActivity activity){
        this.activity = activity;
        initializeSystem();
    }

    private void initializeSystem(){
        loginFrag = new LoginFragment();
        loginFrag.setController(this);
        activity.addFragment(loginFrag, "loginfrag");
    }

    public void loginUser(int hashid){
        this.hashid = hashid;
        activity.removeFragment(loginFrag);

        overviewFragment = new OverviewFragment();
        activity.addFragment(overviewFragment, "overviewfragment");
    }
}
