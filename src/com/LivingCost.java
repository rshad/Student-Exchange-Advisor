package com;
import com.WebScraping;

import java.awt.Paint;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.crypto.CipherInputStream;
import javax.swing.Painter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.*;
import org.javatuples.*;



public class LivingCost extends WebScraping {
	
	private String API_CODE = "qwycxj8mj2ppny";
	private String MainPageLink = "https://www.numbeo.com";
	private String Country_name = "";
	private String CountryApiPricingLinkString ;
	
	private String currency="";
	private String RecievedCountry = "";
	 
	private String Item1 = "Meal, Inexpensive Restaurant, Restaurants";
	private String Item2 = "Apartment (1 bedroom) in City Centre, Rent Per Month";
	private String Item3 = "One-way Ticket (Local Transport), Transportation";
	private String Item4 = "Gasoline (1 liter), Transportation";
	private String Item5 = "Domestic Beer (0.5 liter draught), Restaurants";
	
	private int ItemsToGet = 0;
	
	private String StringToConvertIntoJsonObject = "";
	
	private URL CountryAPIURL  ;
	private ArrayList<String> Countries = new ArrayList<String>() ;
    private BufferedReader in;
    
    private HashMap <String, Double> Item_Price = new HashMap<String, Double>(); 
  
	public LivingCost(){
		// TODO Auto-generated method stub
	}
	
	
	private String ConvertTheJSONintoString(String url) throws IOException{
		StringToConvertIntoJsonObject = "";
		URL CountryAPIURL= new URL(url);
		StringToConvertIntoJsonObject ="";
		in = new BufferedReader( new InputStreamReader(CountryAPIURL.openStream()));
        String inputLine="" ;
        while ((inputLine = in.readLine()) != null){
        	StringToConvertIntoJsonObject += inputLine;
        }
        return StringToConvertIntoJsonObject;
	}



	private void LoadCountries() throws JSONException, IOException{
		String StringToJSON = this.ConvertTheJSONintoString(MainPageLink+"/api/country_prices?api_key="+API_CODE);
    	JSONObject obj = new JSONObject(StringToJSON);   
    	JSONArray JsonArray = obj.getJSONArray("supported_countries");
        JSONObject json_data=null;
    	if(JsonArray != null) {
            for(int i=0;i<JsonArray.length();i++){   
                    this.Countries.add(JsonArray.getString(i));
            }
    	}

	}
	public void setCountryToCompareItsName(String CountryToCompare){
		RecievedCountry = CountryToCompare;
	}
	//public void setCountry(String country){
		//Country_name = country;
		//this.setCountryAPILinkString();
	//}
		
	public ArrayList<String> GetCountries(){
		return this.Countries;
	}
	private void FinalCountry(){
		boolean WeGotTheCorrectCountry = false;
		int i=0;
		while(!WeGotTheCorrectCountry && i<Countries.size()){
			if(Countries.get(i).contains(RecievedCountry)){
				Country_name = Countries.get(i);
				WeGotTheCorrectCountry = true;
			}
			else{
				i++;
			}
		}
	}
	private void setCountryAPILinkString() throws UnsupportedEncodingException{
		this.FinalCountry();
		CountryApiPricingLinkString = "https://www.numbeo.com/api/country_prices?api_key="+API_CODE+"&country="+java.net.URLEncoder.encode(Country_name,"UTF-8");
	}
	private void GenerateJsonObjectAndArray() throws JSONException, IOException{
		this.setCountryAPILinkString();
		String StringToJSON = this.ConvertTheJSONintoString(CountryApiPricingLinkString);
		JSONObject obj = new JSONObject(StringToJSON);   
    	JSONArray JsonArray = obj.getJSONArray("prices");
        currency = (String) obj.get("currency");
        JSONObject json_data;
        
        
    	if(JsonArray != null) {
    		Item_Price.clear();
            for(int i=0;i<JsonArray.length();i++){
                json_data = JsonArray.getJSONObject(i);
                ItemsToGet = 0;
                if( (
                		((String)json_data.get("item_name")).contains(Item1) ||                	//	(String)json_data.get("item_name") == Item4 ||
                		((String)json_data.get("item_name")).contains(Item2) ||
                		((String)json_data.get("item_name")).contains(Item3) ||
                		((String)json_data.get("item_name")).contains(Item4) ||
                		((String)json_data.get("item_name")).contains(Item5) 
                		) && ItemsToGet < 5 )
                {	
                	
                	this.Item_Price.put(json_data.getString("item_name"),(double)json_data.getInt("average_price"));
                	ItemsToGet++;
                }	
            }
            ItemsToGet=0;
    	}   
    	else{
    		System.out.println("Error");
    	}
	}    
	public String getCurrency(){
		return this.currency;
	}
	public HashMap<String,Double> Result(String Country) throws JSONException, IOException{
		this.LoadCountries();
		this.setCountryToCompareItsName(Country);
		this.GenerateJsonObjectAndArray();
		return this.Item_Price;
		
	}
    //Functions

	void buscar(){
		
	}

}

