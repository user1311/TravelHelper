package ar.enigmatic.travel_helper_app_a2_v1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class calculate_costs extends Fragment {

    private Countries country;

    public void setCountry(Countries c){
        if (country==null)
        {
            country=new Countries();
        }
        country=c;

    }

    public calculate_costs() {
        // Required empty public constructor
    }

    TextView city,hotel;
    EditText daysNumber = null;
    TextView cost = null;
    TextView currency=null;
    Double dailyCost=0.0;
    Double airplaneTicket=0.0;
    int days=0;

    View view;


    View.OnFocusChangeListener focusChangeListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {

            if(b)return;

            String mtText=daysNumber.getText().toString();

            if(mtText.length()>0 && mtText.toString().matches("[0-9]+"))
                daysNumber.setText(mtText);
            else
                daysNumber.setText("0");

            calculateCosts();


        }
    };

    View.OnKeyListener keyListener=new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {

            if((keyEvent.getAction()==KeyEvent.ACTION_DOWN) && (i==KeyEvent.KEYCODE_ENTER)) {
                daysNumber.clearFocus();
                return true;
            }
            return false;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Calculate costs");
        view=inflater.inflate(R.layout.fragment_calculate_costs, container, false);
        initializeViews(view);
        setVariables();
        daysNumber.setOnFocusChangeListener(focusChangeListener);
        daysNumber.setOnKeyListener(keyListener);


        return view;
    }


    public void calculateCosts() {

        days=Integer.parseInt(daysNumber.getText().toString());

        double result=days==0?0:((days*dailyCost)+airplaneTicket)*Currency.currency;

        cost.setText(Double.toString(result)+" "+Currency.name);

    }


    private void setVariables() {

        currency.setText("1 "+ Currency.name+ " =  "+String.format("%.2f",(country.Currency_per_one_euro*Currency.currency))+" "+country.Currency_name);
        dailyCost=country.Average_daily_expanditure;
        airplaneTicket=country.Return_ticket;

        cost.setText("0 "+Currency.name);

        city.setText("CITY: "+country.City);
        hotel.setText("HOTEL: "+country.Hotel)  ;
        days=Integer.parseInt(daysNumber.getText().toString());

    }


    private void initializeViews(View view) {

        hotel=(TextView)view.findViewById(R.id.hotelTxt);
        city=(TextView)view.findViewById(R.id.cityTxt);
        daysNumber = (EditText) view.findViewById(R.id.dayNumber);
        cost = (TextView) view.findViewById(R.id.costsTxt);
        currency=(TextView)view.findViewById(R.id.currencyTxt);

    }

    public void setCostsTxtView(){
        currency.setText("1 "+ Currency.name+ " =  "+String.format("%.2f",(country.Currency_per_one_euro*Currency.currency))+" "+country.Currency_name);
    }



}
