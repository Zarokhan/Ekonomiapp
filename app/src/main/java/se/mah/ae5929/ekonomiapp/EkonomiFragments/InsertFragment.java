package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.Base.InsertController;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;
import se.mah.ae5929.ekonomiapp.Utility.ViewPagerMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertFragment extends BaseFragment<InsertController> {

    public static final String TAG = "INSERTFRAG";
    private TextView modeTv;
    private TextView categoryTv;
    private TextView dateTv;
    private TextView titleTv;
    private TextView totalTv;
    private TextView warningTv;
    private EditText titleEt;
    private EditText totalEt;
    private Button submitBn;
    private Button dateBn;

    ViewPagerMode mode;
    String cat;
    String date;
    String title;
    int total;

    public InsertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        initFragmentComponents(view);
        return view;
    }

    @Override
    protected void initFragmentComponents(View view) {
        modeTv = (TextView)view.findViewById(R.id.modeTv);
        categoryTv = (TextView)view.findViewById(R.id.categoryTv);
        dateTv = (TextView)view.findViewById(R.id.dateTv);
        titleTv = (TextView)view.findViewById(R.id.titleTv);
        totalTv = (TextView)view.findViewById(R.id.totalTv);
        warningTv = (TextView)view.findViewById(R.id.warningTv);
        titleEt = (EditText)view.findViewById(R.id.titleEt);
        totalEt = (EditText)view.findViewById(R.id.totalEt);
        submitBn = (Button)view.findViewById(R.id.submitBn);
        dateBn = (Button)view.findViewById(R.id.dateBn);

        initializeInsertFragment();
    }

    private void initializeInsertFragment(){
        Bundle args = getArguments();

        mode = ViewPagerMode.values()[args.getInt("mode")];
        cat = args.getString("category");
        date = args.getString("date");

        Resources res = getActivity().getResources();

        switch (mode){
            case Incomes:
                modeTv.setText(res.getString(R.string.mode) + res.getString(R.string.incomes));
                break;
            case Expenses:
                modeTv.setText(res.getString(R.string.mode) + res.getString(R.string.expenses));
        }
        categoryTv.setText(res.getString(R.string.category) + cat);
        dateBn.setText(date);

        submitBn.setOnClickListener(new SubmitClickListener());
        dateBn.setOnClickListener(new DateCliclListener());
    }

    public void setDateButtonText(String date) {
        dateBn.setText(date);
    }

    private class SubmitClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            title = titleEt.getText().toString();
            date = dateBn.getText().toString();
            try {
                total = Integer.parseInt(totalEt.getText().toString());
            }
            catch (Exception e){
                total = 0;
            }
            controller.submit(mode, cat, date, title, total);
        }
    }

    private class DateCliclListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            controller.selectDate();
        }
    }

    public void setWarningText(String text){
        warningTv.setText(text);
    }
}
