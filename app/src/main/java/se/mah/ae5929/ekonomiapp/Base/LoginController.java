package se.mah.ae5929.ekonomiapp.Base;

import android.content.Intent;
import android.content.res.Resources;

import se.mah.ae5929.ekonomiapp.EkonomiFragments.LoginFragment;
import se.mah.ae5929.ekonomiapp.R;

/**
 * Created by Zarokhan on 2016-09-08.
 * Handles user login
 */
public class LoginController {
    private LoginActivity activity;

    private LoginFragment loginFrag;

    public LoginController(LoginActivity activity){
        this.activity = activity;
        initializeLogin();
    }

    // Initializes the login phase
    private void initializeLogin(){
        loginFrag = new LoginFragment();
        loginFrag.setController(this);
        activity.addFragment(loginFrag, LoginFragment.FRAGMENT_KEY);
    }

    // User sign in method
    public void loginUser(String fname, String lname){
        Resources res = activity.getResources();
        // Check for exceptions
        if(fname == null || fname.isEmpty() || lname == null || lname.isEmpty())
        {
            loginFrag.setWarningText(res.getText(R.string.no_text_warning).toString());
            return;
        }

        // Calculate hash id
        int hashid = (fname.toLowerCase() + lname.toLowerCase()).hashCode();

        Intent mainIntent = new Intent(activity.getApplicationContext(), EkoActivity.class);
        mainIntent.putExtra("hashid", hashid);
        mainIntent.putExtra("fname", fname);
        mainIntent.putExtra("lname", lname);
        activity.startActivityForResult(mainIntent, LoginActivity.NAME);
    }

    public void resetRememberMe(){
        loginFrag.resetRememberMe();
    }
}
