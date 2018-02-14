package com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.*;

public class UniversitiesRanking extends WebScraping {
	private String MainPageURL;
	private String SubjectURLExtention;
	private String SubjectCompleteURL="";
    private String SubjectsMainLink ;//= "/world-university-rankings/2017/subject-ranking/";
	private String html_element_name = "a";
	private String class_name ;
    private ArrayList<String> subjects = new ArrayList<String>();
    private String subject_url;
    private String Dynamic_Table_JSON_link ;
    private String StringToConvertIntoJsonObject = "";
    
	private Document MainPageDoc ;//=  Jsoup.connect(RankBySubjectsURL).get();
    private Elements subject_links ;
 
    URL Dynamic_Table_Url;

    BufferedReader in;
    
    //Attributes Arrays
    private HashMap<String, Integer> University_Ranking = new HashMap<String, Integer>();
    private HashMap<String, String> University_Country = new HashMap<String, String>();
    private HashMap<String , String> University_MaleFemale = new HashMap<String,String>();
    
    //attributes 
    String university_name , university_location;
    int university_ranking;

	public UniversitiesRanking(){
		// TODO Auto-generated method stub

	}
    //Functions
	public void setMainPageURL(String url){
		this.MainPageURL = url;
	}
	public String getMainPageURL(){
		return MainPageURL;
	}
	public void setSubjectURLExtention(String url){
		this.SubjectURLExtention = url;
	}
	public void setSubjectCompleteURL(){
		this.SubjectCompleteURL = MainPageURL+SubjectURLExtention;
	}
	public String getSubjectCompleteURL(){
		return SubjectCompleteURL;
	}
	//Load in a Document the content of the source code of the webPage
	public void loadTheContentSourceIntoDocument() throws IOException{
		this.MainPageDoc = Jsoup.connect(SubjectCompleteURL).get();
	}
	public void SetClass_Name(String class_){
		this.class_name = class_;
	}
	public void SelectElementfromDoc(){
		this.subject_links = MainPageDoc.select(html_element_name);
		for(Element link : this.subject_links){
			if(link.attr("class").contains(class_name)){
				this.subjects.add( link.attr("href"));
			}
		}
	}
	public void setSubjectMainLink(String url){
		this.SubjectsMainLink = url;
	}
	public void generateTheChosenSubjectURL(String SubjectToStudy){
        subject_url = (MainPageDoc.select("a."+class_name)).select("["+SubjectsMainLink+SubjectToStudy+"]").toString();
	}
	public void setTheDynamicTableJsonUrl(String url) throws MalformedURLException{
		this.Dynamic_Table_JSON_link = url;
		this.Dynamic_Table_Url = new URL(Dynamic_Table_JSON_link);
	}
       
	public void ConvertTheJSONintoString() throws IOException{
		in = new BufferedReader( new InputStreamReader(Dynamic_Table_Url.openStream()));
        String inputLine="" ;
        while ((inputLine = in.readLine()) != null){
        	StringToConvertIntoJsonObject += inputLine;
        }
	}
	public void GenerateJsonObjectAndArray() throws JSONException{
    	JSONObject obj = new JSONObject(StringToConvertIntoJsonObject);
    	JSONArray JsonArray = obj.getJSONArray("data");
        JSONObject json_data=null;
        JSONObject locations = new JSONObject();
    	if(JsonArray != null) {
            for(int i=0;i<JsonArray.length();i++){
                    json_data = JsonArray.getJSONObject(i);
                    this.University_Ranking.put(json_data.getString("name"),json_data.getInt("rank_order") );
                    this.University_Country.put(json_data.getString("name"),json_data.getString("location") );
            }
    	}
	}
	
	public void insertStringConvert(String AuxStringToConvertIntoJsonObject) throws JSONException{
		StringToConvertIntoJsonObject = AuxStringToConvertIntoJsonObject;
	}
	
	public void realizeParcialWork(String AuxStringToConvertIntoJsonObject)throws IOException, JSONException{
		this.insertStringConvert(AuxStringToConvertIntoJsonObject);
		GenerateJsonObjectAndArray();
	}
	public HashMap<String, Integer> getUniversity_Ranking(){
		return this.University_Ranking;
	}
	public HashMap<String,String> getUniversity_Country(){
		return this.University_Country;
	}
	public HashMap<String, String> getUniversity_MaleFemal(){
		return this.University_MaleFemale;
	}
	public String getConvertIntoJson(){
		return StringToConvertIntoJsonObject;
	}
	public void RealizeTotalWork() throws IOException, JSONException{
		UniversitiesRanking universities = new UniversitiesRanking();
		setMainPageURL("https://www.timeshighereducation.com");
		setSubjectURLExtention("/world-university-rankings/by-subject");
		setSubjectCompleteURL();
		getSubjectCompleteURL();
		loadTheContentSourceIntoDocument();
		SetClass_Name("is-new");
		SelectElementfromDoc();
		setSubjectMainLink("href=/world-university-rankings/2017/subject-ranking/");
		setTheDynamicTableJsonUrl("https://www.timeshighereducation.com//sites//default//files//the_data_rankings//computer_science_rankings_2017_limit0_4516931838992206229b45900bd0edde.json");
		ConvertTheJSONintoString();
		GenerateJsonObjectAndArray();
	}
	@Override
	void buscar() {
		// TODO Auto-generated method stub
		
	}
}