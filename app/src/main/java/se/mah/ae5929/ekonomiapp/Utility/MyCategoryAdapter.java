package se.mah.ae5929.ekonomiapp.Utility;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import se.mah.ae5929.ekonomiapp.Base.MainController;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.ListFragment;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.ViewPagerFragment;

/**
 * Created by Zarokhan on 2016-09-17.
 */
public class MyCategoryAdapter extends FragmentStatePagerAdapter {
    private MainController controller;
    private Context context;
    private ViewPagerMode mode;
    private int hashid;
    private String[] incomeCats;
    private String[] expenseCats;

    public MyCategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void setMode(ViewPagerMode mode){
        this.mode = mode;
    }

    public void setHashid(int hashid){
        this.hashid = hashid;
    }

    @Override
    public Fragment getItem(int i) {
        ListFragment frag = new ListFragment();
        frag.setController(controller);
        Bundle args = new Bundle();

        MyDatabase db = MyDatabase.getInstance(context);

        getCategories();

        args.putInt("mode", mode.ordinal());
        args.putInt("hashid", hashid);
        args.putString("from", controller.getDateFrom());
        args.putString("to", controller.getDateTo());

        switch (mode){
            case Incomes:
                args.putString("cat", incomeCats[i]);
                break;
            case Expenses:
                args.putString("cat", expenseCats[i]);
                break;
        }

        frag.setArguments(args);
        return frag;
    }

    @Override
    public int getCount() {
        getCategories();
        switch (mode){
            case Incomes:
                return incomeCats.length;
            case Expenses:
                return expenseCats.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int i){
        getCategories();
        switch (mode){
            case Incomes:
                return incomeCats[i];
            case Expenses:
                return expenseCats[i];
        }
        return "" + (i + 1);
    }

    private void getCategories(){
        if(incomeCats == null || expenseCats == null)
        {
            MyDatabase db = MyDatabase.getInstance(context);
            incomeCats = db.getIncomeCategories();
            expenseCats = db.getExpenseCategories();
        }
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}