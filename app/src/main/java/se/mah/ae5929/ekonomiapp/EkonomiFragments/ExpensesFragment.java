package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends BaseFragment {

    public ExpensesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        return view;
    }
}
