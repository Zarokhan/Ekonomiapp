package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends BaseFragment {

    public static final String ARG_OBJECT = "object";
    public static final String TAG = "ListViewFragment";

    public enum ListViewMode {
        IncomeListView,
        ExpenseListView
    }

    private TextView summaryTv;

    private ListViewMode type;

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

        Bundle args = this.getArguments();
        type = ListViewMode.values()[args.getInt("mode", 0)];

        Resources res = getActivity().getResources();

        switch (type){
            case IncomeListView:
                name = res.getString(R.string.incomes);
                summaryTv.setText(res.getString(R.string.total_incomes) + args.getInt("total_income"));
                break;
            case ExpenseListView:
                name = res.getString(R.string.expenses);
                summaryTv.setText(res.getString(R.string.total_expenses) + args.getInt("total_expense"));
                break;
        }

        summaryTv.setText("" + args.getInt(ARG_OBJECT));
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
