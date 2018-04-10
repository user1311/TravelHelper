package ar.enigmatic.travel_helper_app_a2_v1;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class add_country extends Fragment {

    public Countries country;


    public add_country() {
        // Required empty public constructor
    }

    TextInputLayout nameLayout, cityLayout, hotelLayout, airplaneTicketLayout, avgCostsLayout, currencyLayout,currNameLayout,imgURL_Layout;
    EditText name, city, hotel,imgURL, airplaneTicket, avgCosts, currency,currName;
    Button saveBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_country, container, false);
        setViews(view);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate_Double() || !validate_Strings()){
                    getActivity().setTitle("Add country");
                    setUpNewCountry();
                    
                    saveCountry(country);

                }

            }
        });




        return view;
    }

    private void saveCountry(Countries country) {
        myListener Listener=(myListener) getActivity();
        Listener.addCountry(country);

    }

    private void setUpNewCountry()
    {
        if(country==null){
            country=new Countries();
        }

        country.Country = name.getText().toString();
        country.City = city.getText().toString();
        country.Hotel = hotel.getText().toString();
        country.Currency_name = currName.getText().toString();
        country.imgURL = imgURL.getText().toString();
        country.Currency_per_one_euro = Double.parseDouble(currency.getText().toString());
        country.Return_ticket = Double.parseDouble(airplaneTicket.getText().toString());
        country.Average_daily_expanditure = Double.parseDouble(avgCosts.getText().toString());



    }

    private void setViews(View view) {
        name = (EditText) view.findViewById(R.id.nameTxt);
        city = (EditText) view.findViewById(R.id.cityTxt);
        hotel = (EditText) view.findViewById(R.id.hotelTxt);
        airplaneTicket = (EditText) view.findViewById(R.id.airplaneTicketTxt);
        avgCosts = (EditText) view.findViewById(R.id.avgCostsTxt);
        currency = (EditText) view.findViewById(R.id.currencyTxt);
        saveBtn = (Button) view.findViewById(R.id.saveBtn);
        currName=(EditText)view.findViewById(R.id.currName);
        imgURL=(EditText) view.findViewById(R.id.URL);

        imgURL_Layout=(TextInputLayout) view.findViewById(R.id.imgURL_layout);
        nameLayout = (TextInputLayout) view.findViewById(R.id.nameLayout);
        cityLayout = (TextInputLayout) view.findViewById(R.id.cityLayout);
        hotelLayout = (TextInputLayout) view.findViewById(R.id.hotelLayout);
        airplaneTicketLayout = (TextInputLayout) view.findViewById(R.id.airplaneticketLayout);
        avgCostsLayout = (TextInputLayout) view.findViewById(R.id.avgCostsLayout);
        currencyLayout = (TextInputLayout) view.findViewById(R.id.currencyLayout);
        currNameLayout = (TextInputLayout) view.findViewById(R.id.currNameLayout);

    }

    private boolean validate_Double() {

        boolean failedValidation = true;

        if (currency.getText().toString().matches("[0-9.]+"))
            currencyLayout.setErrorEnabled(false);
        else {
            currencyLayout.setError("Currency should not be empty!");
            failedValidation = false;
        }

        if (avgCosts.getText().toString().matches("[0-9.]+"))
            avgCostsLayout.setErrorEnabled(false);
        else {
            avgCostsLayout.setError("Average daily costs should not be empty!");
            failedValidation = false;
        }

        if (airplaneTicket.getText().toString().matches("[0-9.]+"))
            airplaneTicketLayout.setErrorEnabled(false);
        else {
            airplaneTicketLayout.setError("Airplane ticket should not be empty!");
            failedValidation = false;
        }

        return failedValidation;

    }

    private boolean validate_Strings() {

        if (country != null) return true;

        boolean failedValidation = true;

        if (name.getText().toString().matches("[a-zA-Z ]+") && name.getText().toString().length() > 2)
            nameLayout.setErrorEnabled(false);
        else {
            nameLayout.setError("Country name should consists of 2 letters minimum!");
            failedValidation = false;
        }

        if (city.getText().toString().matches("[a-zA-Z ]+") && city.getText().toString().length() > 2)
            cityLayout.setErrorEnabled(false);
        else {
            cityLayout.setError("City name should consists of 2 letters minimum!");
            failedValidation = false;
        }

        if (hotel.getText().toString().matches("[a-zA-Z ]+") && hotel.getText().toString().length() > 2)
            hotelLayout.setErrorEnabled(false);
        else {
            hotelLayout.setError("Country name should consists of 2 letters minimum!");
            failedValidation = false;
        }

        if (imgURL.getText().toString().matches("[a-zA-Z ]+_+[a-zA-Z ]") && imgURL.getText().toString().length() > 2)
            imgURL_Layout.setErrorEnabled(false);
        else {
            currNameLayout.setError("Country image set to default");
            imgURL.setText("flag_bosnia");
            failedValidation = false;
        }


        if (currName.getText().toString().matches("[a-zA-Z ]+") && currName.getText().toString().length() > 2)
            currNameLayout.setErrorEnabled(false);
        else {
            currNameLayout.setError("Currency name should consists of 2 letters minimum!");
            failedValidation = false;
        }

        return failedValidation;

    }

}
