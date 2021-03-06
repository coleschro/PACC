package com.example.cole.pacc.Chem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cole.pacc.Chem.SubFragmentThermoElectro;
import com.example.cole.pacc.R;

import java.util.ArrayList;

/**
 * Created by Cole on 11/11/17.
 */

public class FragmentChem extends Fragment {

    private ListView topicsListView;
    private ArrayList<String> topics;
    private ArrayAdapter<String> adapter;
    private Fragment currentFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.chem_fragment, container, false);

        //wire any widgets -- must use rootView.findViewById
        wireWidgets(rootView);

        //get any other initial set up done
        createTopics();

        adapter =
                new ArrayAdapter<String>(getActivity(), //context
                        R.layout.list_item_topic,   //textView layout for the l
                        topics); //the list to d
        //set the adapter to the listView
        topicsListView.setAdapter(adapter);
        topicsListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) { //int i used to be pos (position in list)
                switch (topics.get(pos)){
                    case "Thermochemistry/Electrochemistry":
                        currentFragment = new SubFragmentThermoElectro();
                        switchToNewScreen();
                        break;
                }
                //Intent
                //Intent i = new Intent(MainActivity.this, StarDescriptionActivity.class);

                //i.putExtra(STAR, stars.get(pos));

                //startActivity(i);
            }
        });

        //return the view that we inflated
        return rootView;
    }

    private void wireWidgets(View rootView) {
        topicsListView = (ListView) rootView.findViewById(R.id.listView_chem);
    }

    private void createTopics(){
        topics = new ArrayList<>();
        topics.add("Equilibrium");
        topics.add("Kinetics");
        topics.add("Gases, Liquids, and Solutions");
        topics.add("Thermochemistry/Electrochemistry");
    }

    private void switchToNewScreen() {
        //tell the fragment manager that if our current fragment isn't null, to replace whatever is there with it
        FragmentManager fm = getFragmentManager();
        if (currentFragment != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, currentFragment)
                    .commit();
        }
    }

}


