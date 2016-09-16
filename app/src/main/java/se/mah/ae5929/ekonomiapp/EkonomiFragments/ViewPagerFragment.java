package se.mah.ae5929.ekonomiapp.EkonomiFragments;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends BaseFragment {
    public static final String TAG = "ViewPagerTag";

    CategoryCollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

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
        mCollectionPagerAdapter = new CategoryCollectionPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager)view.findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}

class CategoryCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public CategoryCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = new ListViewFragment();
        Bundle args = new Bundle();
        args.putInt("total_expense", 0);
        args.putInt("mode", 0);
        args.putInt(ListViewFragment.ARG_OBJECT, i + 1);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int i){
        return "" + (i + 1);
    }
}