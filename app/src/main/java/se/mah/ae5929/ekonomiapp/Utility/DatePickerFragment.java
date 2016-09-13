package se.mah.ae5929.ekonomiapp.Utility;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import static android.icu.util.Calendar.getInstance;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    public DatePickerFragment() {
        // Required empty public constructor
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        Calendar rightNow = getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int monthOfYear = rightNow.get(Calendar.MONTH);
        int dayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), 0, this, year, monthOfYear, dayOfMonth);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
