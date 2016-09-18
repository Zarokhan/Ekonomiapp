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

import java.util.Calendar;

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
    private Button fromBn;
    private Button toBn;

    private ViewPagerMode mode;
    private String category;
    private String dateFrom, dateTo;
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
        fromBn = (Button)view.findViewById(R.id.fromBn);
        toBn = (Button)view.findViewById(R.id.toBn);

        Resources res = getActivity().getResources();
        MyDatabase db = MyDatabase.getInstance(getActivity().getApplicationContext());
        Bundle args = this.getArguments();

        dateFrom = args.getString("from", null);
        dateTo = args.getString("to", null);

        mode = ViewPagerMode.values()[args.getInt("mode", 0)];
        category = args.getString("cat");
        hashid = args.getInt("hashid");

        categoryTv.setText(category);
        if(dateTo != null)
            toBn.setText(dateTo);
        if(dateFrom != null)
            fromBn.setText(dateFrom);

        String dateMax = null;
        String dateMin = null;

        if(dateTo != null && dateFrom != null){
            dateMax = getMaxDate(dateTo, dateFrom);
            dateMin = getMinDate(dateTo, dateFrom);
        }

        switch (mode){
            case Incomes:
                summaryTv.setText(res.getString(R.string.total) + db.getTotalIncomeCategory(category, hashid));

                IncomeObj[] objs = db.getIncomeFromCategory(category, hashid, dateMin, dateMax);
                entryLv.setAdapter(new MyIncomeAdapter(getActivity(), objs));
                break;
            case Expenses:
                summaryTv.setText(res.getString(R.string.total) + db.getTotalExpenseCategory(category, hashid));

                ExpenseObj[] objs2 = db.getExpenseFromCategory(category, hashid, dateMin, dateMax);
                entryLv.setAdapter(new MyExpenseAdapter(getActivity(), objs2));
                break;
        }

        if(category == "All"){
            insertBn.setClickable(false);
            insertBn.setActivated(false);
            insertBn.setAlpha(0.2f);
        }else{
            insertBn.setOnClickListener(new InsertClickListener());
        }

        fromBn.setOnClickListener(new FromBnClickListener());
        toBn.setOnClickListener(new ToBnClickListener());
    }

    private String getMaxDate(String date1, String date2){
        String[] split1 = date1.split("-");
        String[] split2 = date2.split("-");
        int y1 = Integer.parseInt(split1[0]);
        int m1 = Integer.parseInt(split1[1]);
        int d1 = Integer.parseInt(split1[2]);
        int y2 = Integer.parseInt(split2[0]);
        int m2 = Integer.parseInt(split2[1]);
        int d2 = Integer.parseInt(split2[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(y1, m1, d1);
        long time1 = cal.getTimeInMillis();
        cal.set(y2, m2, d2);
        long time2 = cal.getTimeInMillis();

        if(time1 > time2)
            return date1;
        else
            return date2;
    }

    private String getMinDate(String date1, String date2){
        String[] split1 = date1.split("-");
        String[] split2 = date2.split("-");
        int y1 = Integer.parseInt(split1[0]);
        int m1 = Integer.parseInt(split1[1]);
        int d1 = Integer.parseInt(split1[2]);
        int y2 = Integer.parseInt(split2[0]);
        int m2 = Integer.parseInt(split2[1]);
        int d2 = Integer.parseInt(split2[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(y1, m1, d1);
        long time1 = cal.getTimeInMillis();
        cal.set(y2, m2, d2);
        long time2 = cal.getTimeInMillis();

        if(time1 < time2)
            return date1;
        else
            return date2;
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
            controller.addInsertActivity(category);
        }
    }

    private class FromBnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            controller.startDateActivity("from");
        }
    }

    private class ToBnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            controller.startDateActivity("to");
        }
    }
}
