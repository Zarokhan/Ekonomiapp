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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import se.mah.ae5929.ekonomiapp.EkonomiFragments.NavigatorFragment;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.OverviewFragment;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.ViewPagerFragment;
import se.mah.ae5929.ekonomiapp.Utility.BaseController;
import se.mah.ae5929.ekonomiapp.Utility.MyDatabase;
import se.mah.ae5929.ekonomiapp.Utility.ViewPagerMode;

/**
 * Created by Zarokhan on 2016-09-16.
 * Handles main part of application
 */
public class MainController extends BaseController<MainActivity> {
    private static final String SELECT_ITEM_KEY = "selectitem";
    public static final String SELECT_TAB_KEY = "selecttabhabbi";

    private NavigatorFragment navFragment;
    private List<Fragment> activeFragments;
    private ViewPagerMode mode;

    private int selectedItem;
    private int selectedTab;
    private int hashid;
    private String fname;
    private String lname;
    private String dateFrom;
    private String dateTo;

    private static MyDatabase db;

    public MainController(MainActivity activity){
        super(activity);
        initializeSystem();
    }

    // Initializes the system
    private void initializeSystem() {
        // get intent data
        Intent passedData = activity.getIntent();
        this.hashid = passedData.getIntExtra("hashid", 0);
        this.fname = passedData.getStringExtra("fname");
        this.lname = passedData.getStringExtra("lname");

        navFragment = new NavigatorFragment();
        navFragment.setController(this);
        getActivity().addFragment(navFragment, NavigatorFragment.TAG);
        activeFragments = new ArrayList<Fragment>();
        db = MyDatabase.getInstance(activity.getApplicationContext());

        onMainFragmentCreated();
    }

    private void onMainFragmentCreated(){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(NavigatorFragment.FRAGMENT_KEY, Activity.MODE_PRIVATE);
        selectedItem = sharedPreferences.getInt(SELECT_ITEM_KEY, 0);
        selectedTab = sharedPreferences.getInt(SELECT_TAB_KEY, 0);

        // Add first overview fragment to main
        initOverview(selectedItem);
    }

    // Save function
    private void saveSelectItem(){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(NavigatorFragment.FRAGMENT_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECT_ITEM_KEY, selectedItem);
        editor.apply();
    }

    public void saveSelectTab(int currentItem){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(NavigatorFragment.FRAGMENT_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        selectedTab = currentItem;
        editor.putInt(SELECT_TAB_KEY, selectedTab);
        editor.apply();
    }

    // Removes active fragments in main container
    private void removeActiveFragments(){
        for(int i = 0; i < activeFragments.size(); ++i){
            getActivity().removeFragment(activeFragments.get(i));
        }
    }

    private ViewPagerFragment getViewPagerFragment(){
        for(int i = 0; i < activeFragments.size(); ++i){
            if(activeFragments.get(i) instanceof  ViewPagerFragment){
                return (ViewPagerFragment)activeFragments.get(i);
            }
        }
        return null;
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
        getActivity().addMainFragment(overfrag, OverviewFragment.TAG);
    }

    // Adds income fragment into main container
    private void addIncomeOverview(){
        ViewPagerMode mode = ViewPagerMode.Incomes;
        Bundle bundle = new Bundle();
        bundle.putInt("total_income", db.getTotalIncome(hashid));
        bundle.putInt("mode", mode.ordinal());
        bundle.putInt("hashid", hashid);
        bundle.putInt(SELECT_TAB_KEY, selectedTab);
        bundle.putString("from", dateFrom);
        bundle.putString("to", dateTo);

        saveSelectTab(selectedTab);

        ViewPagerFragment frag = new ViewPagerFragment();
        frag.setController(this);
        frag.setArguments(bundle);

        activeFragments.add(frag);
        getActivity().addMainFragment(frag, ViewPagerFragment.TAG);
    }

    // Adds expense fragment into main container
    private void addExpenseOverview(){
        ViewPagerMode mode = ViewPagerMode.Expenses;
        Bundle bundle = new Bundle();
        bundle.putInt("total_expense", db.getTotalExpenses(hashid));
        bundle.putInt("mode", mode.ordinal());
        bundle.putInt("hashid", hashid);
        bundle.putInt(SELECT_TAB_KEY, selectedTab);
        bundle.putString("from", dateFrom);
        bundle.putString("to", dateTo);

        saveSelectTab(selectedTab);

        ViewPagerFragment frag = new ViewPagerFragment();
        frag.setController(this);
        frag.setArguments(bundle);

        activeFragments.add(frag);
        getActivity().addMainFragment(frag, ViewPagerFragment.TAG);
    }

    // Start insert activity
    public void addInsertActivity(String category){
        Intent intent = new Intent(activity.getApplicationContext(), InsertActivity.class);
        intent.putExtra("mode", mode.ordinal());

        intent.putExtra("category", category);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        intent.putExtra("date", date);
        intent.putExtra("hashid", hashid);

        activity.startActivityForResult(intent, MainActivity.NAME);
    }

    // Adds image to actionbar
    private void actionBarImage(){
        // Action bar test
        ActionBar actionBar = getActivity().getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions() | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        //imageView.setImageResource(R.drawable.ic_action_expand);
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
        if(pos != selectedItem)
            initOverview(pos);

        // Handles navigator return
        mDrawerList.setItemChecked(pos, true);
        activity.setTitle(mPlanetTitles[pos]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void initOverview(int pos){
        removeActiveFragments();
        selectedItem = pos;
        switch (pos){
            // Overview
            case 0:
                addOverview();
                break;
            // Incomes
            case 1:
                mode = ViewPagerMode.Incomes;
                addIncomeOverview();
                break;
            // Expenses
            case 2:
                mode = ViewPagerMode.Expenses;
                addExpenseOverview();
                break;
            // Sign out
            case 3:
                signOut();
                return;
        }
        saveSelectItem();
    }

    // Signs outs the user
    public void signOut(){
        removeActiveFragments();
        getActivity().removeFragment(navFragment);

        Intent result = new Intent();
        result.putExtra("signout", true);
        activity.setResult(Activity.RESULT_OK, result);
        activity.finish();
    }

    public int getHashid(){
        return hashid;
    }

    public void refreshPage() {
        //removeActiveFragments();
        initOverview(selectedItem);
    }

    public void startDateActivity(String mode) {
        Intent intent = new Intent(getActivity().getApplicationContext(), DateActivity.class);
        intent.putExtra("mode", mode);
        activity.startActivityForResult(intent, MainActivity.NAME);
    }

    public void setDateTo(String date) {
        dateTo = date;
    }

    public void setDateFrom(String date) {
        dateFrom = date;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }
}
