package se.mah.ae5929.ekonomiapp.Utility;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import se.mah.ae5929.ekonomiapp.Base.MainController;

/**
 * Created by Zarokhan on 2016-09-13.
 */
public abstract class BaseFragment<MyController extends BaseController> extends Fragment {

    protected MyController controller;
    protected String name;

    public void setController(MyController controller){
        this.controller = controller;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //getActivity().setTitle(name);
    }

    protected abstract void initFragmentComponents(View view);

    public String getName() {return this.name;}
    protected MyController getController(){
        return controller;
    }
}
