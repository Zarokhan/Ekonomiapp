package se.mah.ae5929.ekonomiapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// NOTES
// NOT USE PICCASSO
// Store name and lastname in private mode

public class EkoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eko);
        initializeSystem();
    }

    private void initializeSystem() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ExpensesFragment frag = new ExpensesFragment();
        ft.add(R.id.container, frag, "expenses");
        ft.commit();
    }
}
