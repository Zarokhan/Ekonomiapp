package se.mah.ae5929.ekonomiapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    public void addFragment(Fragment frag, String tag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container, frag, tag);
        ft.commit();
    }

    public void removeFragment(Fragment frag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.remove(frag);
        ft.commit();
    }
}
