package se.mah.ae5929.ekonomiapp.Base;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {
    public static final String FRAGMENT_KEY = "loginfragment";
    public static final String FNAME_KEY = "firstname";
    private static final String LNAME_KEY = "lastname";
    private static final String REMEMBER_KEY = "dskfhlsafkj";

    private EditText fnameEt;
    private EditText lnameEt;
    private Button loginBn;
    private TextView warningTv;
    private Switch rememberSwitch;

    private String blockCharacterSet = " ";
    private boolean resetRememberMe = false;

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initComponents(view);
        name = "Login";
        return view;
    }

    private void initComponents(View view){
        fnameEt = (EditText)view.findViewById(R.id.nameEt);
        lnameEt = (EditText)view.findViewById(R.id.surnameEt);
        loginBn = (Button)view.findViewById(R.id.loginBn);
        warningTv = (TextView)view.findViewById(R.id.warningTv);
        rememberSwitch = (Switch)view.findViewById(R.id.rememberSwitch);

        // Make input hide at startup
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        loginBn.setOnClickListener(new LoginClickListener());
        // Filter for white spaces
        fnameEt.setFilters(new InputFilter[] {filter});
        lnameEt.setFilters(new InputFilter[] {filter});
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FRAGMENT_KEY, Activity.MODE_PRIVATE);
        String name = sharedPreferences.getString(FNAME_KEY, "");
        String surname = sharedPreferences.getString(LNAME_KEY, "");
        boolean rememberMe = sharedPreferences.getBoolean(REMEMBER_KEY, false);
        fnameEt.setText(name);
        lnameEt.setText(surname);
        rememberSwitch.setChecked(rememberMe);
    }

    @Override
    public void onResume(){
        super.onResume();

        // Check if we remembered me
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FRAGMENT_KEY, Activity.MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean(REMEMBER_KEY, false);
        if(resetRememberMe){
            SharedPreferences test = getActivity().getSharedPreferences(FRAGMENT_KEY, Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = test.edit();
            editor.putBoolean(REMEMBER_KEY, false);
            editor.apply();
            rememberSwitch.setChecked(false);
        }
        else if(rememberMe)
            controller.loginUser(fnameEt.getText().toString(), lnameEt.getText().toString());
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        saveMyInstance();
    }

    private void saveMyInstance(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FRAGMENT_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FNAME_KEY, fnameEt.getText().toString());
        editor.putString(LNAME_KEY, lnameEt.getText().toString());
        editor.putBoolean(REMEMBER_KEY, rememberSwitch.isChecked());
        editor.apply();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    private class LoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            saveMyInstance();
            controller.loginUser(fnameEt.getText().toString(), lnameEt.getText().toString());
        }
    }

    // Displays warning text
    public void setWarningText(String text){
        this.warningTv.setText(text);
    }

    // Sets remember me to false
    public void resetRememberMe(){
        this.resetRememberMe = true;
    }
}
