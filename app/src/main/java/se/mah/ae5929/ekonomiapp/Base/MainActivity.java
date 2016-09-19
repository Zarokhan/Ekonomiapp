package se.mah.ae5929.ekonomiapp.Base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.mah.ae5929.ekonomiapp.R;

// NOTES
// NOT USE PICCASSO

/*
    Main activity of application
 */
public class MainActivity extends AppCompatActivity {

    public static final int NAME = 16;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eko);
        initializeController();
    }

    private void initializeController() {
        controller = new MainController(this);
    }

    // Adds fragment to MainActivity container
    public void addFragment(Fragment frag, String tag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container, frag, tag);
        ft.commit();
    }

    // Adds fragment to NavigatorFragment container
    public void addMainFragment(Fragment frag, String tag){
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

    // When MainActivity signs out
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==NAME) {
            boolean inserted = data.getBooleanExtra("inserted", false);
            if(inserted)
            {
                controller.refreshPage();
                return;
            }

            String mode = data.getStringExtra("mode");
            String date = data.getStringExtra("date");
            if(mode == null || date == null)
                return;

            if(mode.equals("to")){
                controller.setDateTo(date);
            }else if (mode.equals("from")){
                controller.setDateFrom(date);
            }

            controller.refreshPage();
        }
    }

    @Override
    public void onBackPressed(){
        controller.signOut();
    }
}
