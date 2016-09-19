package se.mah.ae5929.ekonomiapp.Base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.mah.ae5929.ekonomiapp.R;

public class InsertActivity extends AppCompatActivity {

    public static final int NAME = 14;

    private InsertController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        initializeController();
    }

    private void initializeController() {
        controller = new InsertController(this);
    }

    // Adds fragment to overview fragment container
    public void addFragment(Fragment frag, String tag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.insert_container, frag, tag);
        ft.commit();
    }

    // Removes fragment
    public void removeFragment(Fragment frag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.remove(frag);
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==NAME) {
            String date = data.getStringExtra("date");
            if(date == null)
                return;
            controller.setDate(date);
        }
    }
}
