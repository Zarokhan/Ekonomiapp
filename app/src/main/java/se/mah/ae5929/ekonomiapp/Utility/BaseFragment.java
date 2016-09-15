package se.mah.ae5929.ekonomiapp.Utility;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import se.mah.ae5929.ekonomiapp.Base.Controller;
import se.mah.ae5929.ekonomiapp.Base.LoginFragment;

/**
 * Created by Zarokhan on 2016-09-13.
 */
public class BaseFragment extends Fragment {

    protected Controller controller;
    protected String name;

    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(name);
    }
}
