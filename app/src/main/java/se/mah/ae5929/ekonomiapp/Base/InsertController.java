package se.mah.ae5929.ekonomiapp.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import se.mah.ae5929.ekonomiapp.DBNodes.ExpenseObj;
import se.mah.ae5929.ekonomiapp.DBNodes.IncomeObj;
import se.mah.ae5929.ekonomiapp.EkonomiFragments.InsertFragment;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseController;
import se.mah.ae5929.ekonomiapp.Utility.MyDatabase;
import se.mah.ae5929.ekonomiapp.Utility.ViewPagerMode;

/**
 * Created by Zarokhan on 2016-09-18.
 */
public class InsertController extends BaseController<InsertActivity> {

    private InsertFragment frag;

    private ViewPagerMode mode;
    private String category;
    private String date;
    private int hashid;

    public InsertController(InsertActivity activity){
        super(activity);
        try {
            initializeInsert();
        } catch (EmptyHashidException e) {
            e.printStackTrace();
        }
    }

    private void initializeInsert() throws EmptyHashidException {
        // Get intent data
        Intent intent = activity.getIntent();

        mode = ViewPagerMode.values()[intent.getIntExtra("mode", 0)];
        category = intent.getStringExtra("category");
        date = intent.getStringExtra("date");
        hashid = intent.getIntExtra("hashid", 0);

        if(hashid == 0)
            throw new EmptyHashidException();

        frag = new InsertFragment();
        frag.setArguments(intent.getExtras());
        frag.setController(this);
        activity.addFragment(frag, InsertFragment.TAG);
    }

    public void submit(ViewPagerMode mode, String cat, String date, String title, int total) {
        // Check valid input
        if(title == null || title.isEmpty() || total == 0){
            frag.setWarningText(getActivity().getResources().getString(R.string.no_text_warning));
            return;
        }
        // Insert into db
        IncomeObj incomeObj;
        ExpenseObj expenseObj;
        MyDatabase db = MyDatabase.getInstance(getActivity().getApplicationContext());
        switch (mode){
            case Incomes:
                incomeObj = new IncomeObj(0, cat, date, title, total);
                db.insertIncomeObj(hashid, incomeObj);
                break;
            case Expenses:
                expenseObj = new ExpenseObj(0, cat, date, title, total);
                db.insertExpenseObj(hashid, expenseObj);
                break;
        }
        // Close activity
        getActivity().removeFragment(frag);
        Intent intent = new Intent();
        intent.putExtra("inserted", true);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    public void selectDate() {
        Intent intent = new Intent(getActivity().getApplicationContext(), DateActivity.class);
        intent.putExtra("mode", mode);
        activity.startActivityForResult(intent, InsertActivity.NAME);
    }

    public void setDate(String date) {
        this.date = date;
        frag.setDateButtonText(date);
    }

    private class EmptyHashidException extends Exception {

    }
}
