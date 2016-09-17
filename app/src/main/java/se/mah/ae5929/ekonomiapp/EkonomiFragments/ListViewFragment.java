package se.mah.ae5929.ekonomiapp.EkonomiFragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.DBNodes.ExpenseObj;
import se.mah.ae5929.ekonomiapp.DBNodes.IncomeObj;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;
import se.mah.ae5929.ekonomiapp.Utility.MyDB;
import se.mah.ae5929.ekonomiapp.Utility.MyExpenseAdapter;
import se.mah.ae5929.ekonomiapp.Utility.MyIncomeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends BaseFragment {

    public static final String TAG = "ListViewFragment";

    private TextView summaryTv;
    private ListView entryLv;

    private ViewPagerFragment.ViewPagerMode mode;
    private String category;
    private int hashid;

    public ListViewFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        initFragmentComponents(view);
        return view;
    }

    @Override
    protected void initFragmentComponents(View view){
        summaryTv = (TextView)view.findViewById(R.id.summaryTv);
        entryLv = (ListView)view.findViewById(R.id.entryLv);

        Resources res = getActivity().getResources();
        MyDB db = MyDB.getInstance(getActivity().getApplicationContext());
        Bundle args = this.getArguments();

        mode = ViewPagerFragment.ViewPagerMode.values()[args.getInt("mode", 0)];
        category = args.getString("cat");
        hashid = args.getInt("hashid");

        switch (mode){
            case Incomes:
                IncomeObj[] objs = db.getIncomeFromCategory(category, hashid);
                entryLv.setAdapter(new MyIncomeAdapter(getActivity(), objs));
                break;
            case Expenses:
                ExpenseObj[] objs2 = db.getExpenseFromCategory(category, hashid);
                entryLv.setAdapter(new MyExpenseAdapter(getActivity(), objs2));
                break;
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
