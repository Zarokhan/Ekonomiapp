package se.mah.ae5929.ekonomiapp;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import se.mah.ae5929.ekonomiapp.Utility.MyFragmentExtension;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends MyFragmentExtension {
    public static final String FRAGMENT_KEY = "loginfragment";
    public static final String FNAME_KEY = "firstname";
    private static final String LNAME_KEY = "lastname";
    private static final String WARNING_KEY = "dskjfhfd";

    private EditText fnameEt;
    private EditText lnameEt;
    private Button loginBn;
    private TextView warningTv;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initComponents(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FRAGMENT_KEY, Activity.MODE_PRIVATE);
        String name = sharedPreferences.getString(FNAME_KEY, "");
        String surname = sharedPreferences.getString(LNAME_KEY, "");
        String warning = sharedPreferences.getString(WARNING_KEY, "");
        fnameEt.setText(name);
        lnameEt.setText(surname);
        warningTv.setText(warning);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        saveMyStuffPls();
    }

    private void saveMyStuffPls(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FRAGMENT_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FNAME_KEY, fnameEt.getText().toString());
        editor.putString(LNAME_KEY, lnameEt.getText().toString());
        editor.putString(WARNING_KEY, warningTv.getText().toString());
        editor.apply();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    private void initComponents(View view){
        fnameEt = (EditText)view.findViewById(R.id.nameEt);
        lnameEt = (EditText)view.findViewById(R.id.surnameEt);
        loginBn = (Button)view.findViewById(R.id.loginBn);
        warningTv = (TextView)view.findViewById(R.id.warningTv);

        // Make input hide at startup
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        loginBn.setOnClickListener(new LoginClickListener());
    }

    private class LoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Get text
            String fname = fnameEt.getText().toString();
            String lname = lnameEt.getText().toString();

            // Check for exceptions
            Resources res = getActivity().getResources();
            if(fname == null || fname.isEmpty() || lname == null || lname.isEmpty())
            {
                warningTv.setText(res.getText(R.string.no_text_warning));
                return;
            }
            if(fname.contains(" ") || lname.contains(" "))
            {
                warningTv.setText(res.getText(R.string.no_spaces_warning));
                return;
            }

            // Calculate hash id
            // Calculate it here because we dont want to handle "personal information elsewere"
            String hashtext = fname.toLowerCase() + lname.toLowerCase();
            int hashid = hashtext.hashCode();
            //warningTv.setText("" + hashid);
            saveMyStuffPls();
            controller.loginUser(hashid);
        }
    }
}
