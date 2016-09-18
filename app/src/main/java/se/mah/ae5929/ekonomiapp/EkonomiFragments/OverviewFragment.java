package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.Base.MainController;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Simple overview of personal economy fragment
 * First fragment to show when user login
 */
public class OverviewFragment extends BaseFragment<MainController> {
    public static final String TAG = "Overview_tag";

    private Resources res;

    private TextView nameTv;
    private TextView incomesTv;
    private TextView expensesTv;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        initFragmentComponents(view);
        return view;
    }

    @Override
    protected void initFragmentComponents(View view) {
        name = "Overview";
        nameTv = (TextView)view.findViewById(R.id.nameTv);
        incomesTv = (TextView)view.findViewById(R.id.incomesTv);
        expensesTv = (TextView)view.findViewById(R.id.expensesTv);

        res = getActivity().getResources();

        // Set name on nameTv
        Bundle args = this.getArguments();
        nameTv.setText(args.getString("fname") + " " + args.getString("lname"));
        setTotalIncomes(args.getInt("total_income"));
        setTotalExpenses(args.getInt("total_expense"));
    }

    public void setTotalIncomes(int amount){
        incomesTv.setText(res.getText(R.string.total_incomes) + " " + amount);
    }

    public void setTotalExpenses(int amount){
        expensesTv.setText(res.getText(R.string.total_expenses) + " " + amount);
    }
}
