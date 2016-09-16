package se.mah.ae5929.ekonomiapp.Base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import se.mah.ae5929.ekonomiapp.EkonomiFragments.ListViewFragment;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.MainFragment;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.OverviewFragment;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.ViewPagerFragment;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.MyDB;

/**
 * Created by Zarokhan on 2016-09-16.
 */
public class MainController {
    private static final String SELECT_ITEM_KEY = "selectitem";

    private EkoActivity activity;

    private MainFragment mainFragment;

    private List<Fragment> activeFragments;

    private int selectItem;
    private int hashid;
    private String fname;
    private String lname;

    private static MyDB db;

    public MainController(EkoActivity activity){
        this.activity = activity;
        initializeSystem();
    }

    // Initializes the system
    private void initializeSystem() {
        // get intent data
        Intent passedData = activity.getIntent();
        this.hashid = passedData.getIntExtra("hashid", 0);
        this.fname = passedData.getStringExtra("fname");
        this.lname = passedData.getStringExtra("lname");

        mainFragment = new MainFragment();
        mainFragment.setController(this);
        activity.addFragment(mainFragment, MainFragment.TAG);
        activeFragments = new ArrayList<Fragment>();
        db = MyDB.getInstance(activity.getApplicationContext());

        onMainFragmentCreated();
    }

    private void onMainFragmentCreated(){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(MainFragment.FRAGMENT_KEY, Activity.MODE_PRIVATE);
        selectItem = sharedPreferences.getInt(SELECT_ITEM_KEY, 0);

        // Add first overview fragment to main
        initOverview(selectItem);
    }

    // Save function
    private void saveSelectItem(){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(MainFragment.FRAGMENT_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECT_ITEM_KEY, selectItem);
        editor.apply();
    }

    // Removes active fragments in main container
    private void removeActiveFragments(){
        for(int i = 0; i < activeFragments.size(); ++i){
            activity.removeFragment(activeFragments.get(i));
        }
    }

    // Adds overview fragment into main container
    private void addOverview(){
        Bundle bundle = new Bundle();
        bundle.putString("fname", fname);
        bundle.putString("lname", lname);
        bundle.putInt("total_income", db.getTotalIncome(hashid));
        bundle.putInt("total_expense", db.getTotalExpenses(hashid));

        OverviewFragment overfrag = new OverviewFragment();
        overfrag.setController(this);
        overfrag.setArguments(bundle);

        activeFragments.add(overfrag);
        activity.addMainFragment(overfrag, OverviewFragment.TAG);
    }

    // Adds income fragment into main container
    private void addIncomeOverview(){
        ListViewFragment.ListViewMode mode = ListViewFragment.ListViewMode.IncomeListView;
        Bundle bundle = new Bundle();
        bundle.putInt("total_income", db.getTotalIncome(hashid));
        bundle.putInt("mode", mode.ordinal());

        ListViewFragment incomefrag = new ListViewFragment();
        incomefrag.setController(this);
        incomefrag.setArguments(bundle);

        activeFragments.add(incomefrag);
        activity.addMainFragment(incomefrag, ListViewFragment.TAG);
    }

    // Adds expense fragment into main container
    private void addExpenseOverview(){
        ListViewFragment.ListViewMode mode = ListViewFragment.ListViewMode.ExpenseListView;
        Bundle bundle = new Bundle();
        bundle.putInt("total_expense", db.getTotalExpenses(hashid));
        bundle.putInt("mode", mode.ordinal());

        ListViewFragment expensefrag = new ListViewFragment();
        expensefrag.setController(this);
        expensefrag.setArguments(bundle);

        activeFragments.add(expensefrag);
        activity.addMainFragment(expensefrag, ListViewFragment.TAG);
    }

    private void addViewPager(){
        ViewPagerFragment frag = new ViewPagerFragment();
        frag.setController(this);

        activeFragments.add(frag);
        activity.addMainFragment(frag, ViewPagerFragment.TAG);
    }

    // Adds image to actionbar
    private void actionBarImage(){
        // Action bar test
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions() | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.ic_action_expand);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.RIGHT | Gravity.CENTER_VERTICAL
        );
        imageView.setLayoutParams(params);
        actionBar.setCustomView(imageView);
    }

    // Navigator on click
    public void navSelectItem(int pos, ListView mDrawerList, String[] mPlanetTitles, DrawerLayout mDrawerLayout){
        if(pos != selectItem)
            initOverview(pos);

        // Handles navigator return
        mDrawerList.setItemChecked(pos, true);
        activity.setTitle(mPlanetTitles[pos]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void initOverview(int pos){
        removeActiveFragments();
        selectItem = pos;
        switch (pos){
            // Overview
            case 0:
                addOverview();
                break;
            // Incomes
            case 1:
                addIncomeOverview();
                break;
            // Expenses
            case 2:
                //addExpenseOverview();
                addViewPager();
                break;
            // Sign out
            case 3:
                signOut();
                return;
        }
        saveSelectItem();
    }

    // Signs outs the user
    private void signOut(){
        removeActiveFragments();
        activity.removeFragment(mainFragment);

        Intent result = new Intent();
        result.putExtra("signout", true);
        activity.setResult(Activity.RESULT_OK, result);
        activity.finish();
    }


}
