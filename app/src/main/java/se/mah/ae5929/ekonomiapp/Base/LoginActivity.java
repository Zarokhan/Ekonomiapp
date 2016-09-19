package se.mah.ae5929.ekonomiapp.Base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.mah.ae5929.ekonomiapp.R;

/*
    Login activity
    Start activity of application
 */
public class LoginActivity extends AppCompatActivity {

    public static final int NAME = 12;

    private LoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeController();
    }

    private void initializeController() {
        controller = new LoginController(this);
    }

    // Adds fragment to overview fragment container
    public void addFragment(Fragment frag, String tag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.test_container, frag, tag);
        ft.commit();
    }

    // Removes fragment
    public void removeFragment(Fragment frag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.remove(frag);
        ft.commit();
    }

    // When MainActivity signs out
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==NAME) {
            boolean signout = data.getBooleanExtra("signout", false);
            if(signout)
                controller.resetRememberMe();
        }
    }
}
