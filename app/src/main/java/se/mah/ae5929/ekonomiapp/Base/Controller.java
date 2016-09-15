package se.mah.ae5929.ekonomiapp.Base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import se.mah.ae5929.ekonomiapp.EkonomiFragments.OverviewFragment;
import se.mah.ae5929.ekonomiapp.R;

/**
 * Created by Zarokhan on 2016-09-08.
 */
public class Controller {
    private EkoActivity activity;
    private LoginFragment loginFrag;
    private MainFragment mainFragment;

    private List<Fragment> activeFragments;

    private int hashid;
    private String fname;
    private String lname;

    public Controller(EkoActivity activity){
        this.activity = activity;
        initializeSystem();
    }

    private void initializeSystem(){
        activeFragments = new ArrayList<Fragment>();
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

        mainFragment = new MainFragment();
        mainFragment.setController(this);
        activity.addFragment(mainFragment, "overviewfragment");

        // Add first overview fragment to main
        addOverview();
    }

    private void removeActiveFragments(){
        for(int i = 0; i < activeFragments.size(); ++i){
            activity.removeFragment(activeFragments.get(i));
        }
    }

    private void addOverview(){
        Bundle bundle = new Bundle();
        bundle.putString("fname", fname);
        bundle.putString("lname", lname);
        OverviewFragment overfrag = new OverviewFragment();
        overfrag.setController(this);
        overfrag.setArguments(bundle);
        activity.addFragmentToOverview(overfrag, OverviewFragment.TAG);
    }

    public void navSelectItem(int pos, ListView mDrawerList, String[] mPlanetTitles, DrawerLayout mDrawerLayout){
        removeActiveFragments();
        switch (pos){
            // Overview
            case 0:
                addOverview();
                break;
            // Incomes
            case 1:
                break;
            // Expenses
            case 2:
                break;
            // About/Settings
            case 3:
                break;
            // Sign out
            case 4:
                activity.removeFragment(mainFragment);
                loginFrag = new LoginFragment();
                loginFrag.setController(this);
                loginFrag.resetRememberMe();
                activity.addFragment(loginFrag, LoginFragment.FRAGMENT_KEY);
                return;
        }

        mDrawerList.setItemChecked(pos, true);
        activity.setTitle(mPlanetTitles[pos]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
