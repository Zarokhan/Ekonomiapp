package se.mah.ae5929.ekonomiapp.EkonomiFragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.Base.MainController;
import se.mah.ae5929.ekonomiapp.DBNodes.ExpenseObj;
import se.mah.ae5929.ekonomiapp.DBNodes.IncomeObj;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;
import se.mah.ae5929.ekonomiapp.Utility.MyDatabase;
import se.mah.ae5929.ekonomiapp.Utility.MyExpenseAdapter;
import se.mah.ae5929.ekonomiapp.Utility.MyIncomeAdapter;
import se.mah.ae5929.ekonomiapp.Utility.ViewPagerMode;

/**
 * A simple {@link Fragment} subclass.
 * List all incomes/expenses within a specific category
 */
public class ListFragment extends BaseFragment<MainController> {

    public static final String TAG = "ListFragment";

    private TextView categoryTv;
    private TextView summaryTv;
    private ListView entryLv;
    private Button insertBn;

    private ViewPagerMode mode;
    private String category;
    private int hashid;

    public ListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        initFragmentComponents(view);
        return view;
    }

    @Override
    protected void initFragmentComponents(View view){
        categoryTv = (TextView)view.findViewById(R.id.categoryTv);
        summaryTv = (TextView)view.findViewById(R.id.summaryTv);
        entryLv = (ListView)view.findViewById(R.id.entryLv);
        insertBn = (Button)view.findViewById(R.id.insertBn);

        Resources res = getActivity().getResources();
        MyDatabase db = MyDatabase.getInstance(getActivity().getApplicationContext());
        Bundle args = this.getArguments();

        mode = ViewPagerMode.values()[args.getInt("mode", 0)];
        category = args.getString("cat");
        hashid = args.getInt("hashid");

        categoryTv.setText(category);
        switch (mode){
            case Incomes:
                summaryTv.setText(res.getString(R.string.total) + db.getTotalIncomeCategory(category, hashid));

                IncomeObj[] objs = db.getIncomeFromCategory(category, hashid);
                entryLv.setAdapter(new MyIncomeAdapter(getActivity(), objs));
                break;
            case Expenses:
                summaryTv.setText(res.getString(R.string.total) + db.getTotalExpenseCategory(category, hashid));

                ExpenseObj[] objs2 = db.getExpenseFromCategory(category, hashid);
                entryLv.setAdapter(new MyExpenseAdapter(getActivity(), objs2));
                break;
        }

        insertBn.setOnClickListener(new InsertClickListener());
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

    private class InsertClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            controller.insert();
        }
    }
}
