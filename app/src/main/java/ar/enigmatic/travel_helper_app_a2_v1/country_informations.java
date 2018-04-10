package ar.enigmatic.travel_helper_app_a2_v1;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class country_informations extends Fragment {


    Countries country;
    TextView country_name,city_name,hotel_name;
    ImageView flag_image;
    Button calc_costs,edit_country;
    View v;

    public country_informations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Country informations");
        v=inflater.inflate(R.layout.fragment_country_informations, container, false);
        setViews();
        showData();

        calc_costs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener listener=(myListener) getActivity();
                listener.calculateCosts();
            }
        });

        edit_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener listener=(myListener) getActivity();
                listener.editCountry();


            }
        });

        return v;
    }

    public void getCountryInfos(Countries country_recvd)
    {
        if(country==null) {
            country = new Countries();
        }

        country=country_recvd;
    }


    public void setViews()
    {
        country_name=(TextView) v.findViewById(R.id.country_name);
        city_name=(TextView) v.findViewById(R.id.city_name);
        hotel_name=(TextView)v.findViewById(R.id.hotel_name);
        flag_image =(ImageView)v.findViewById(R.id.flag);
        calc_costs=(Button) v.findViewById(R.id.calc_cost_btn);
        edit_country=(Button) v.findViewById(R.id.edit_country_btn);

    }

    public void showData()
    {
        if(country!=null) {
            String pic_uri = "@drawable/" + country.imgURL.toString();
            country_name.setText(country.Country.toString());
            city_name.setText(country.City.toString());
            hotel_name.setText(country.Hotel);

            flag_image.setImageResource(getResources().getIdentifier(pic_uri, null, getActivity().getPackageName()));
            Log.d("data_recv","Data received");
        }
        else
        {
            Toast.makeText(getActivity().getBaseContext(),"Didn't receive anything",Toast.LENGTH_LONG).show();
        }
    }

}
