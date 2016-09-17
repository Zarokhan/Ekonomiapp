package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;
import se.mah.ae5929.ekonomiapp.Utility.MyDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends BaseFragment {
    public static final String TAG = "ViewPagerTag";

    CategoryCollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    private ViewPagerMode type;
    private int hashid;

    public enum ViewPagerMode {
        Incomes,
        Expenses
    }

    public ViewPagerFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        initFragmentComponents(view);
        return view;
    }

    @Override
    protected void initFragmentComponents(View view) {
        Resources res = getActivity().getResources();
        Bundle args = this.getArguments();
        type = ViewPagerMode.values()[args.getInt("mode", 0)];
        hashid = args.getInt("hashid");
        switch (type){
            case Incomes:
                name = res.getString(R.string.incomes);
                break;
            case Expenses:
                name = res.getString(R.string.expenses);
                break;
        }

        getActivity().setTitle(name);

        mCollectionPagerAdapter = new CategoryCollectionPagerAdapter(getActivity().getSupportFragmentManager());
        mCollectionPagerAdapter.setType(type);
        mCollectionPagerAdapter.setHashid(hashid);
        mCollectionPagerAdapter.setContext(getActivity().getApplicationContext());

        mViewPager = (ViewPager)view.findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}

class CategoryCollectionPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private ViewPagerFragment.ViewPagerMode mode;
    private int hashid;
    private String[] incomeCats;
    private String[] expenseCats;

    public CategoryCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void setType(ViewPagerFragment.ViewPagerMode mode){
        this.mode = mode;
    }

    public void setHashid(int hashid){
        this.hashid = hashid;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = new ListViewFragment();
        Bundle args = new Bundle();

        MyDB db = MyDB.getInstance(context);

        getCategories();

        args.putInt("mode", mode.ordinal());
        args.putInt("hashid", hashid);

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
            MyDB db = MyDB.getInstance(context);
            incomeCats = db.getIncomeCategories();
            expenseCats = db.getExpenseCategories();
        }
    }
}