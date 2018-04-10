package ar.enigmatic.travel_helper_app_a2_v1;

/**
 * Created by Adnan on 8.3.2018.
 */

public class Countries {

    public String Country;
    public String City;
    public String Hotel;
    public String imgURL;
    public String Currency_name;

    public double Currency_per_one_euro;
    public double Return_ticket;
    public double Average_daily_expanditure;

    public Countries(){}

    public Countries(String c,String ci,String h,String url,String cur_name,double cpe,double rt,double ade){
        this.Country=new String(c);
        this.City=new String(ci);
        this.Hotel=new String(h);
        this.imgURL=new String(url);
        this.Currency_name=new String(cur_name);
        this.Currency_per_one_euro=cpe;
        this.Return_ticket=rt;
        this.Average_daily_expanditure=ade;


    }

    public Countries getCountry(){return this;}

}
