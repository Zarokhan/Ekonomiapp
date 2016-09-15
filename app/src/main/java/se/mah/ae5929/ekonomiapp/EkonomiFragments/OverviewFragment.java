package se.mah.ae5929.ekonomiapp.EkonomiFragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import se.mah.ae5929.ekonomiapp.LoginFragment;
import se.mah.ae5929.ekonomiapp.R;
import se.mah.ae5929.ekonomiapp.Utility.MyFragmentExtension;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends MyFragmentExtension {
    public static final String FRAGMENT_KEY = "jaskdhfkashdfklashdf";

    private TextView nameTv;

    // Navigation drawer
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    public OverviewFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        initComponents(view);
        return view;
    }

    // Get all the components references from the fragment
    private void initComponents(View view){
        nameTv = (TextView)view.findViewById(R.id.nameTv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get saved name of user
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginFragment.FRAGMENT_KEY, Activity.MODE_PRIVATE);
        String name = sharedPreferences.getString(LoginFragment.FNAME_KEY, "");
        nameTv.setText(name);

        initNavigationDrawer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // Save name on textview
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginFragment.FRAGMENT_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LoginFragment.FNAME_KEY, nameTv.getText().toString());
        editor.apply();
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            controller.navSelectItem(position);
        }
    }

    private void initNavigationDrawer(){
        // Navigation Drawer
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) getView().findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) getView().findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }
}
