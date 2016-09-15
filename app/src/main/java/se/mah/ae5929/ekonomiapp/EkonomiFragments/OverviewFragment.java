package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.os.Bundle;
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
public class OverviewFragment extends BaseFragment {
    public static final String TAG = "Overview_tag";

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

    private void initFragmentComponents(View view) {
        name = "Overview";
        nameTv = (TextView)view.findViewById(R.id.nameTv);
        incomesTv = (TextView)view.findViewById(R.id.incomesTv);
        expensesTv = (TextView)view.findViewById(R.id.expensesTv);

        // Set name on nameTv
        Bundle args = this.getArguments();
        nameTv.setText(args.getString("fname") + " " + args.getString("lname"));
    }
}
