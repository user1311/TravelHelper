package ar.enigmatic.travel_helper_app_a2_v1;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class show_countries extends Fragment {

    private List<Countries> list_of_countries;
    ListView list_view_countries;
    List<String> names_of_countries;
    View v;
    Button btn;
    private FragmentManager manager;
    private add_country fragment_add_country;

    public show_countries() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_show_countries, container, false);
        manager=getFragmentManager();
        setUpListView();


        btn=(Button) v.findViewById(R.id.add_country_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_add_country=new add_country();

                FragmentTransaction transaction=manager.beginTransaction();

                transaction.replace(R.id.fragment_sc_frame,fragment_add_country,"fragment_add_country");
                transaction.commit();


            }
        });



        return v;
    }

    public void setCountriesList(List<Countries> countries){

        this.list_of_countries=countries;
        names_of_countries = new ArrayList<String>();

        for (Countries c:list_of_countries
             ) {
            names_of_countries.add(c.Country);
            Log.e("country_added",c.Country);
        }
    }

    public void setUpListView()
    {

        this.list_of_countries=new ArrayList<Countries>();
        list_view_countries=(ListView) v.findViewById(R.id.listView_countries);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(), R.layout.custom_listview,names_of_countries);
        list_view_countries.setAdapter(arrayAdapter);

        list_view_countries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity().getBaseContext(),names_of_countries.get(i).toString()+" is selected",Toast.LENGTH_LONG).show();
                myListener myListener=(myListener) getActivity();
                myListener.getSelectedCountry(i);


            }
        });

    }

    public void sendCountryInfo(Countries c)
    {
        country_informations countryInformations=new country_informations();

        FragmentTransaction fragmentTransaction=manager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_layout,countryInformations,"fragment_country_informations");
        fragmentTransaction.commit();

    }

}
