package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.mah.ae5929.ekonomiapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomesFragment extends Fragment {


    public IncomesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incomes, container, false);
    }

}
