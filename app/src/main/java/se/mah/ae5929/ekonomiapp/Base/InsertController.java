package se.mah.ae5929.ekonomiapp.Base;

import android.content.Intent;
import android.os.Bundle;

import se.mah.ae5929.ekonomiapp.EkonomiFragments.InsertFragment;
import se.mah.ae5929.ekonomiapp.Utility.BaseController;
import se.mah.ae5929.ekonomiapp.Utility.ViewPagerMode;

/**
 * Created by Zarokhan on 2016-09-18.
 */
public class InsertController extends BaseController<InsertActivity> {

    private ViewPagerMode mode;
    private String category;
    private String date;

    public InsertController(InsertActivity activity){
        super(activity);
        initializeInsert();
    }

    private void initializeInsert() {
        // Get intent data
        Intent intent = activity.getIntent();

        mode = ViewPagerMode.values()[intent.getIntExtra("mode", 0)];
        category = intent.getStringExtra("category");
        date = intent.getStringExtra("date");

        InsertFragment frag = new InsertFragment();
        frag.setArguments(intent.getExtras());
        frag.setController(this);
        activity.addFragment(frag, InsertFragment.TAG);
    }
}
