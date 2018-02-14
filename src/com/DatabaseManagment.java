package com;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Text;


public class DatabaseManagment {
	final DatastoreService datastore;
	
	public DatabaseManagment(){
		
		datastore = Database.getDatastoreService();
	}
	
	public void insertEntityJSON(String cadena){
			
			Transaction tx = datastore.beginTransaction();
			
			try{
				
				Text texto = new Text(cadena);
				
				Entity entidad = new Entity("InformacionUniversidades");
				
				entidad.setProperty("Enlace", texto);
					
				datastore.put(entidad);
				
				tx.commit();
			}finally{
				if(tx.isActive()){
					tx.rollback();
				}
			}
		
	}
	

	
	public void insertEntity(String university, int ranking, String pais){
		
		Transaction tx = datastore.beginTransaction();
		
		try{
			
			Entity entidad = new Entity("Universities");
			
			entidad.setProperty("University", university);
				
			entidad.setProperty("Ranking", ranking);
	
			entidad.setProperty("Pais", pais);
				
			datastore.put(entidad);
			
			tx.commit();
		}finally{
			if(tx.isActive()){
				tx.rollback();
			}
		}
		
	}
	
	public Entity getEntityUniversity(String university){
		
		Transaction tx = datastore.beginTransaction();
		
			Filter keyFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, university);
			
			Query q = new Query("Universities").setFilter(keyFilter);
			
			PreparedQuery pq = datastore.prepare(q);
		
		return pq.asSingleEntity();
	}
	
public Entity getEntityPage(){
		
		Transaction tx = datastore.beginTransaction();
			
			Query q = new Query("InformacionUniversidades");
			
			PreparedQuery pq = datastore.prepare(q);
		
		return pq.asSingleEntity();
	}
}
