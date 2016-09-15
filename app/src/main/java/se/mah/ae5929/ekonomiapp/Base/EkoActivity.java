package se.mah.ae5929.ekonomiapp.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.mah.ae5929.ekonomiapp.R;

// NOTES
// NOT USE PICCASSO
// Store name and lastname in private mode

public class EkoActivity extends AppCompatActivity {

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eko);
        initializeController();
    }

    private void initializeController() {
        controller = new Controller(this);
    }

    // Adds fragment to activity container
    public void addFragment(Fragment frag, String tag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container, frag, tag);
        ft.commit();
    }

    // Adds fragment to overview fragment container
    public void addFragmentToOverview(Fragment frag, String tag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_container, frag, tag);
        ft.commit();
    }

    // Removes fragment
    public void removeFragment(Fragment frag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.remove(frag);
        ft.commit();
    }
}
