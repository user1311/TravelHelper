package ar.enigmatic.travel_helper_app_a2_v1;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,myListener, FragmentManager.OnBackStackChangedListener{

    private DrawerLayout myDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private List<Countries> list_countries;
    View view;
    private FragmentManager manager;
    private Countries selectedCountry;
    private int selectedCountryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeCountries();
        if(savedInstanceState!=null)
        {
            list_countries=(ArrayList<Countries>) savedInstanceState.getSerializable("countries");
        }


        setUpDrawerLayout();


        manager=getFragmentManager();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void setUpDrawerLayout()
    {
        myDrawerLayout=(DrawerLayout) findViewById(R.id.activity_main_layout);
        mToggle= new ActionBarDrawerToggle(this,myDrawerLayout,R.string.open,R.string.close);
        myDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.select_country) {
            setTitle("Country selection");
            showCountries();
        }


        myDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initializeCountries(){

        Countries Bosnia = new Countries("Bosnia", "Sarajevo", "Hotel Holiday inn", "flag_bosnia","BAM",1.6,250.4,15.2);
        Countries Russia = new Countries("Russia", "Moscow", "Hotel Westin", "flag_russia","RUM",69.7,310.32,30.8);
        Countries Turkey = new Countries("Turkey", "Istanbul", "The Marmara Taksim", "flag_turkey","TRY",0.21,450.2,19.35);
        Countries Egypt = new Countries("Egypt", "Cairo", "The Marmara Taksim", "flag_egypt","EGP",0.046,525.0,32.18);
        Countries Germany = new Countries("Germany", "Berlin", "Hotel Widder", "flag_germany","EUR",1,20.35,50.78);

        this.list_countries= new ArrayList<Countries>();
        this.list_countries.add(Bosnia);
        this.list_countries.add(Russia);
        this.list_countries.add(Turkey);
        this.list_countries.add(Egypt);
        this.list_countries.add(Germany);


    }

    public void showCountries() {
        show_countries fragment_show_countries = new show_countries();
        fragment_show_countries.setCountriesList(this.list_countries);

        FragmentTransaction fragmentTransaction=manager.beginTransaction();

        fragmentTransaction.replace(R.id.activity_main_layout,fragment_show_countries,"fragment_show_countries");
        fragmentTransaction.addToBackStack("showCountries");
        fragmentTransaction.commit();

    }


    @Override
    public void addCountry(Countries country) {

        boolean alreadyAdded=false;
        int position=0;

        for (int i=0;i<list_countries.size();i++) {
            if (country.Country == list_countries.get(i).Country) {
                alreadyAdded = true;
                position=i;
            }

        }

        if(alreadyAdded){
            Toast.makeText(this, "Country already in list, removing...", Toast.LENGTH_SHORT).show();
            this.list_countries.remove(position);
            this.list_countries.add(country);
            showCountries();
        }else {
            list_countries.add(country);
            showCountries();
        }
    }

    @Override
    public void getSelectedCountry(int i)
    {
        country_informations countryInformations=new country_informations();
        countryInformations.getCountryInfos(this.list_countries.get(i));
        selectedCountry=new Countries();
        selectedCountry=this.list_countries.get(i);
        selectedCountryPosition=i;
        FragmentTransaction fragmentTransaction=manager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_sc_frame,countryInformations,"fragment_country_informations");
        fragmentTransaction.replace(R.id.activity_main_layout   ,countryInformations,"fragment_country_informations");
        fragmentTransaction.addToBackStack("getSelectedCountry");
        fragmentTransaction.commit();

    }

    @Override
    public void calculateCosts() {
        calculate_costs calculateCosts=new calculate_costs();
        calculateCosts.setCountry(selectedCountry);

        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.activity_main_layout,calculateCosts,"calculate_costs_fragment");
        transaction.addToBackStack("calculateCosts");
        transaction.commit();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("countries", (Serializable) list_countries);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        list_countries=(ArrayList<Countries>) savedInstanceState.getSerializable("countries");


    }

    @Override
    public void editCountry() {

        edit_country editCountry=new edit_country();
        editCountry.setCountry(selectedCountry);

        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.activity_main_layout,editCountry,"edit_country_fragment");
        transaction.addToBackStack("editCountry");
        transaction.commit();

    }

    public void setCurrency(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.dollar:
                Currency.currency = (double) 1.231;
                Currency.name = "USD";
                break;
            case R.id.euro:
                Currency.currency = 1;
                Currency.name = "EUR";
                break;
            case R.id.nok:
                Currency.currency = (double) 9.587;
                Currency.name = "NOK";
        }

        Toast.makeText(getApplicationContext(), "Currency changed to " + Currency.name, Toast.LENGTH_SHORT).show();

        Fragment fragment = manager.findFragmentById(R.id.container);

        if (fragment != null && fragment instanceof calculate_costs) {

            calculate_costs calculate_costsFragment = (calculate_costs) fragment;

            calculate_costsFragment.setCostsTxtView();
            calculate_costsFragment.calculateCosts();
        }

    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void onBackPressed() {

        if(manager.getBackStackEntryCount()>0)
        {
            manager.popBackStack();
        }
        else{
            super.onBackPressed();
        }


    }
}
