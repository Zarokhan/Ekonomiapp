package se.mah.ae5929.ekonomiapp.EkonomiFragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.mah.ae5929.ekonomiapp.Base.MainController;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;
import se.mah.ae5929.ekonomiapp.Utility.MyCategoryAdapter;
import se.mah.ae5929.ekonomiapp.Utility.ViewPagerMode;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ViewPagerFragment extends BaseFragment<MainController> {

    public static final String TAG = "ViewPagerTag";

    private MyCategoryAdapter mCollectionPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    public ViewPagerFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        initFragmentComponents(view);
        return view;
    }

    @Override
    protected void initFragmentComponents(View view) {
        Resources res = getActivity().getResources();
        Bundle args = this.getArguments();
        ViewPagerMode mode = ViewPagerMode.values()[args.getInt("mode", 0)];
        int hashid = args.getInt("hashid");
        switch (mode){
            case Incomes:
                name = res.getString(R.string.incomes);
                break;
            case Expenses:
                name = res.getString(R.string.expenses);
                break;
        }

        getActivity().setTitle(name);

        mCollectionPagerAdapter = new MyCategoryAdapter(getActivity().getSupportFragmentManager());
        mCollectionPagerAdapter.setMode(mode);
        mCollectionPagerAdapter.setHashid(hashid);
        mCollectionPagerAdapter.setContext(getActivity().getApplicationContext());
        mCollectionPagerAdapter.setController(getController());

        mViewPager = (ViewPager)view.findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionPagerAdapter);

        mViewPager.setCurrentItem(args.getInt(MainController.SELECT_TAB_KEY));

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        controller.saveSelectTab(mViewPager.getCurrentItem());
    }
}

