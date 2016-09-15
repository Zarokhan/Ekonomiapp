package se.mah.ae5929.ekonomiapp;

import android.content.res.Resources;
import android.util.Log;

import se.mah.ae5929.ekonomiapp.EkonomiFragments.OverviewFragment;

/**
 * Created by Zarokhan on 2016-09-08.
 */
public class Controller {
    private EkoActivity activity;
    private LoginFragment loginFrag;
    private OverviewFragment overviewFragment;

    private int hashid;
    private String fname;
    private String lname;

    public Controller(EkoActivity activity){
        this.activity = activity;
        initializeSystem();
    }

    private void initializeSystem(){
        loginFrag = new LoginFragment();
        loginFrag.setController(this);
        activity.addFragment(loginFrag, LoginFragment.FRAGMENT_KEY);
    }

    public void loginUser(String firstname, String lastname){
        // Set first and last name
        this.fname = firstname;
        this.lname = lastname;

        Resources res = activity.getResources();
        // Check for exceptions
        if(fname == null || fname.isEmpty() || lname == null || lname.isEmpty())
        {
            loginFrag.setWarningText(res.getText(R.string.no_text_warning).toString());
            return;
        }

        // Calculate hash id
        this.hashid = (firstname.toLowerCase() + lastname.toLowerCase()).hashCode();

        activity.removeFragment(loginFrag);

        overviewFragment = new OverviewFragment();
        overviewFragment.setController(this);
        activity.addFragment(overviewFragment, "overviewfragment");
    }

    public void navSelectItem(int pos){
        switch (pos){
            case 4:
                activity.removeFragment(overviewFragment);
                loginFrag = new LoginFragment();
                loginFrag.setController(this);
                loginFrag.resetRememberMe();
                activity.addFragment(loginFrag, LoginFragment.FRAGMENT_KEY);
                break;
        }
    }
}
