package com;

import org.json.JSONException;
import org.jsoup.*; // JSOUP

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.apphosting.client.datastoreservice.proto.DatastoreService;

import org.json.*;

import org.apache.commons.lang3.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class IseprogramServlet extends HttpServlet {
	
	Turist t1, t2, t3;
	UniversitiesRanking university_obj;
	LivingCost LivingCostObj1 = new LivingCost();
	LivingCost LivingCostObj2= new LivingCost();
	LivingCost LivingCostObj3 = new LivingCost();

	ArrayList<String> imprimir1, imprimir2, imprimir3;
	DatabaseManagment dBM;
	
	public IseprogramServlet(){
		imprimir1 = new ArrayList<String>();
		
		imprimir2 = new ArrayList<String>();
		
		imprimir3 = new ArrayList<String>();
		
		dBM = new DatabaseManagment();
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, JSONException{
		
		
		PrintWriter out = response.getWriter();
		
		
		try{
			
			response.setContentType("text/html;charset=UTF-8");
			
			String University1 = request.getParameter("university1").toString();
			
			String University2 = request.getParameter("university2").toString();
			
			String University3 = request.getParameter("university3").toString(); 
			
			university_obj = new UniversitiesRanking();
			university_obj.RealizeTotalWork();
			
			
			int rank1 = university_obj.getUniversity_Ranking().get(University1);
			int rank2 = university_obj.getUniversity_Ranking().get(University2);
			int rank3 = university_obj.getUniversity_Ranking().get(University3);
			
			String country1 = university_obj.getUniversity_Country().get(University1);
			String country2 = university_obj.getUniversity_Country().get(University2);
			String country3 = university_obj.getUniversity_Country().get(University3);
			
			HashMap<String, Double> FirstCountryPrices = LivingCostObj1.Result(country1); 
			HashMap<String, Double> SecondCountryPrices = LivingCostObj2.Result(country2);
			HashMap<String, Double> ThirdCountryPrices = LivingCostObj3.Result(country3);
			
			/*dBM.insertEntity(University1, rank1, country1);
			
			dBM.insertEntity(University2, rank2, country2);
			
			dBM.insertEntity(University3, rank3, country3);*/
			
			//dBM.insertEntityJSON(university_obj.getConvertIntoJson());
			
			t1 = new Turist(country1);
			
			t1.buscar();
					
			imprimir1 = t1.mostrarSalida();
			
			request.setAttribute("monum1", imprimir1);
			

			t2 = new Turist(country2);
			
			t2.buscar();
					
			imprimir2 = t2.mostrarSalida();
			
			request.setAttribute("monum2", imprimir2);
			

			t3 = new Turist(country3);
			
			t3.buscar();
					
			imprimir3 = t3.mostrarSalida();
			
			request.setAttribute("monum3", imprimir3);
			
			
			request.setAttribute("ranking1", rank1);
			request.setAttribute("univer1",University1);
			
			request.setAttribute("ranking2", rank2);
			request.setAttribute("univer2",University2);
			
			request.setAttribute("ranking3", rank3);
			request.setAttribute("univer3",University3);
			
			request.setAttribute("Country1Prices", FirstCountryPrices);
			request.setAttribute("Country2Prices", SecondCountryPrices);
			request.setAttribute("Country3Prices", ThirdCountryPrices);
			
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/shown.jsp");
			
			
			
			rd.forward(request, response);
			
		}finally{
			out.close();
		}
	}
	
	
	//Viene determinado por el method del index
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		try {
			processRequest(req, resp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Viene determinado por el method del index
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		try {
			processRequest(req, resp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}